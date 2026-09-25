/* MeridianFix addition, 2026-09-25. SPDX-License-Identifier: LGPL-3.0-or-later */
package org.embeddedt.modernfix.util;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/** Writes a complete properties file before replacing the previous configuration. */
public final class AtomicConfigFile {
    private AtomicConfigFile() {}

    @FunctionalInterface
    public interface Content {
        void write(Writer writer) throws IOException;
    }

    public static void write(Path destination, Content content) throws IOException {
        Path target = destination.toAbsolutePath();
        Files.createDirectories(target.getParent());
        // Resolve existing symlinks so saving does not replace the link itself.
        if (Files.isSymbolicLink(target)) target = target.toRealPath();
        Path temporary = Files.createTempFile(target.getParent(), ".meridianfix-", ".tmp");
        try {
            // Properties.load(InputStream) uses ISO-8859-1; preserve that contract.
            try (Writer writer = Files.newBufferedWriter(temporary, StandardCharsets.ISO_8859_1)) {
                content.write(writer);
            }
            try {
                Files.move(temporary, target, StandardCopyOption.ATOMIC_MOVE,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException e) {
                // Fail without truncating the previous config on unsupported filesystems.
                throw new IOException("Atomic configuration replacement is not supported: " + target, e);
            }
        } finally {
            Files.deleteIfExists(temporary);
        }
    }
}
