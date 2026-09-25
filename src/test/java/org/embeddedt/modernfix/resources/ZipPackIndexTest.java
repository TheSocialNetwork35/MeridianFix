/* MeridianFix addition, 2026-09-25. SPDX-License-Identifier: LGPL-3.0-or-later */
package org.embeddedt.modernfix.resources;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import net.minecraft.server.packs.PackType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import static org.junit.jupiter.api.Assertions.*;

class ZipPackIndexTest {
    @TempDir Path directory;

    private Path zip(String... names) throws Exception {
        Path path = directory.resolve("pack.zip");
        try (var zip = new ZipOutputStream(Files.newOutputStream(path))) {
            for (String name : names) {
                zip.putNextEntry(new ZipEntry(name));
                zip.write(new byte[]{42});
                zip.closeEntry();
            }
        }
        return path;
    }

    @Test void validIndexKeepsNamespacesAndResourceLookup() throws Exception {
        var index = new ZipPackIndex(zip("assets/example/textures/a.png", "data/example/recipe/a.json"));
        assertEquals(Set.of("example"), index.getNamespaces(PackType.CLIENT_RESOURCES));
        assertTrue(index.hasResource("assets", "example", "textures", "a.png"));
        assertFalse(index.hasResource());
        assertFalse(index.hasResource("assets", "example", "textures", "missing.png"));
    }

    @Test void malformedArchiveFallsBackInsteadOfAppearingEmpty() throws Exception {
        Path path = directory.resolve("broken.zip");
        Files.write(path, new byte[32]);
        assertThrows(IOException.class, () -> new ZipPackIndex(path));
    }

    @Test void truncatedDirectoryIsRejected() throws Exception {
        Path path = zip("assets/example/test.txt");
        byte[] bytes = Files.readAllBytes(path);
        for (int i = 0; i < bytes.length - 3; i++) {
            if (bytes[i] == 0x50 && bytes[i + 1] == 0x4b && bytes[i + 2] == 1 && bytes[i + 3] == 2) {
                bytes[i] = 0;
                break;
            }
        }
        Files.write(path, bytes);
        assertThrows(IOException.class, () -> new ZipPackIndex(path));
    }

    @Test void nonCanonicalZipNamesDoNotAliasValidResources() throws Exception {
        var index = new ZipPackIndex(zip("assets//example/test.txt", "/assets/example/other.txt"));
        assertTrue(index.getNamespaces(PackType.CLIENT_RESOURCES).isEmpty());
    }

    @Test void deepArchiveDoesNotOverflowTheStack() throws Exception {
        var index = new ZipPackIndex(zip("assets/example/" + "x/".repeat(6000) + "test.txt"));
        assertEquals(Set.of("example"), index.getNamespaces(PackType.CLIENT_RESOURCES));
    }

    @Test void emptyZipIsValid() throws Exception {
        var index = new ZipPackIndex(zip());
        assertTrue(index.getNamespaces(PackType.CLIENT_RESOURCES).isEmpty());
    }
    @Test void overlayIndexDoesNotExposeBaseOrOtherOverlayResources() throws Exception {
        Path path = zip("assets/base/base.txt", "overlay/assets/example/overlay.txt",
                "overlay2/assets/other/other.txt");
        var index = new ZipPackIndex(path, "overlay");
        assertEquals(Set.of("example"), index.getNamespaces(PackType.CLIENT_RESOURCES));
        assertTrue(index.hasResource("assets", "example", "overlay.txt"));
        var resources = new java.util.HashMap<net.minecraft.resources.Identifier,
                net.minecraft.server.packs.resources.IoSupplier<java.io.InputStream>>();
        try (var archive = new java.util.zip.ZipFile(path.toFile())) {
            index.listResources(PackType.CLIENT_RESOURCES, "example", "", archive, resources::put);
            assertEquals(1, resources.size());
            try (var input = resources.values().iterator().next().get()) { assertEquals(42, input.read()); }
        }
    }
}
