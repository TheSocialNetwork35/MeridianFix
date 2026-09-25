// MeridianFix port, 2026-09-25: overlay ownership moved to Gui.
package org.embeddedt.modernfix.common.mixin.feature.measure_time;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Overlay;
import org.embeddedt.modernfix.ModernFixClient;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Minecraft.class)
@ClientOnlyMixin
public class MinecraftMixin {
    // TODO re-add datapack reload time measurement

    @Inject(method = "tick", at = @At("HEAD"))
    private void onClientTick(CallbackInfo ci) {
        if(((Minecraft)(Object)this).gui.overlay() == null && ModernFixClient.INSTANCE != null) {
            ModernFixClient.INSTANCE.onGameLaunchFinish();
        }
    }

    @Inject(method = "doWorldLoad", at = @At("HEAD"))
    private void recordWorldLoadStart(CallbackInfo ci) {
        ModernFixClient.worldLoadStartTime = System.nanoTime();
    }
}
