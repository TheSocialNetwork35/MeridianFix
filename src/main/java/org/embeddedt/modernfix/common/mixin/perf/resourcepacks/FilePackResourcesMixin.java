// ModernFix Reforged modification, 2026-09-25: version-specific ZIP access and failure cache.
// ModernFix Reforged modifications, 2026-09-25: cache index failure until pack close.
package org.embeddedt.modernfix.common.mixin.perf.resourcepacks;

import net.minecraft.server.packs.FilePackResources;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import org.embeddedt.modernfix.ModernFix;
import org.embeddedt.modernfix.annotation.FeatureLevel;
import org.embeddedt.modernfix.annotation.RequiresFeatureLevel;
import org.embeddedt.modernfix.resources.ZipPackIndex;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.util.Set;
import java.util.zip.ZipFile;

@Mixin(FilePackResources.class)
@RequiresFeatureLevel(FeatureLevel.BETA)
public class FilePackResourcesMixin {
    @Shadow
    @Final
    private FilePackResources.SharedZipFileAccess zipFileAccess;

    @Shadow
    @Final
    private String prefix;

    @Unique
    @Nullable
    private volatile ZipPackIndex mf$packIndex;

    @Unique
    private volatile boolean mf$indexFailed;

    @Unique
    @Nullable
    private ZipPackIndex mf$getOrCreateIndex() {
        if (mf$indexFailed) return null;
        var index = mf$packIndex;
        if (index == null) {
            synchronized (this) {
                index = mf$packIndex;
                if (index == null && !mf$indexFailed) {
                    // Ensure the ZipFile is open first; if it fails, getOrCreateZipFile returns null.
                    var access = ((SharedZipFileAccessAccessor)this.zipFileAccess);
                    if (access.mfix$getOrCreateZipFile() == null) {
                        return null;
                    }
                    try {
                        mf$packIndex = index = new ZipPackIndex(access.mfix$getFile().toPath(), this.prefix);
                    } catch (IOException e) {
                        mf$indexFailed = true;
                        ModernFix.LOGGER.error("Failed to build zip index for {}", access.mfix$getFile(), e);
                    }
                }
            }
        }
        return index;
    }

    /**
     * @author embeddedt
     * @reason use the index instead of scanning the whole zip
     */
    @Inject(method = "getNamespaces", at = @At("HEAD"), cancellable = true)
    private void mf$getNamespaces(PackType type, CallbackInfoReturnable<Set<String>> cir) {
        ZipPackIndex index = mf$getOrCreateIndex();
        if (index != null) {
            cir.setReturnValue(index.getNamespaces(type));
        }
    }

    /**
     * @author embeddedt
     * @reason use the index instead of scanning the whole zip
     */
    @Inject(method = "listResources", at = @At("HEAD"), cancellable = true)
    private void mf$listResources(PackType packType, String namespace, String path,
                                   PackResources.ResourceOutput resourceOutput, CallbackInfo ci) {
        ZipFile zf = ((SharedZipFileAccessAccessor)this.zipFileAccess).mfix$getOrCreateZipFile();
        ZipPackIndex index = mf$getOrCreateIndex();
        if (index != null && zf != null) {
            index.listResources(packType, namespace, path, zf, resourceOutput);
            ci.cancel();
        }
    }

    /**
     * Drop the index when the pack is closed so it can be rebuilt cleanly if the
     * pack is ever re-opened.
     */
    @Inject(method = "close", at = @At("HEAD"))
    private void mf$invalidateIndex(CallbackInfo ci) {
        synchronized (this) {
            mf$packIndex = null;
            mf$indexFailed = false;
        }
    }
}
