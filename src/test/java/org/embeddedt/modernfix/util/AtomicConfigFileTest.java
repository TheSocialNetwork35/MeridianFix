/* ModernFix Reforged addition, 2026-09-25. SPDX-License-Identifier: LGPL-3.0-or-later */
package org.embeddedt.modernfix.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.*;

class AtomicConfigFileTest {
    @TempDir Path directory;

    @Test void failedWritePreservesPreviousConfigurationAndCleansTemporaryFile() throws Exception {
        Path target = directory.resolve("config.properties");
        Files.writeString(target, "previous=true\n");
        assertThrows(IOException.class, () -> AtomicConfigFile.write(target, writer -> {
            writer.write("incomplete=");
            throw new IOException("Simulated disk write failure");
        }));
        assertEquals("previous=true\n", Files.readString(target));
        try (var files = Files.list(directory)) { assertEquals(1, files.count()); }
    }

    @Test void successfulWriteReplacesFileWithPropertiesCompatibleEncoding() throws Exception {
        Path target = directory.resolve("nested/config.properties");
        AtomicConfigFile.write(target, writer -> writer.write("name=café\n"));
        Properties properties = new Properties();
        try (var input = Files.newInputStream(target)) { properties.load(input); }
        assertEquals("café", properties.getProperty("name"));
        AtomicConfigFile.write(target, writer -> writer.write("name=replaced\n"));
        assertEquals("name=replaced\n", Files.readString(target));
    }

    @Test void preservesSymlinkAndUpdatesItsTarget() throws Exception {
        Path real = directory.resolve("real.properties");
        Files.writeString(real, "old=true");
        Path link = directory.resolve("link.properties");
        Files.createSymbolicLink(link, real.getFileName());
        AtomicConfigFile.write(link, writer -> writer.write("new=true"));
        assertTrue(Files.isSymbolicLink(link));
        assertEquals("new=true", Files.readString(real));
    }
}
