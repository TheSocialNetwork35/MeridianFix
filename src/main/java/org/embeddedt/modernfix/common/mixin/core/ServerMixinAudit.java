// ModernFix Reforged addition, 2026-09-25. SPDX-License-Identifier: LGPL-3.0-or-later
package org.embeddedt.modernfix.common.mixin.core;

import net.minecraft.server.Main;
import net.minecraft.server.Bootstrap;
import org.embeddedt.modernfix.platform.ModernFixPlatformHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Opt-in transformation audit only: never opens a world or starts a game server. */
@Mixin(Main.class)
public class ServerMixinAudit {
    @Inject(method = "main", at = @At("HEAD"))
    private static void reforged$auditOnly(String[] args, CallbackInfo ci) {
        if (Boolean.getBoolean("modernfix.auditAndExit")
                && ModernFixPlatformHooks.INSTANCE.isDedicatedServer()) {
            net.minecraft.SharedConstants.tryDetectVersion();
            Bootstrap.bootStrap();
            Bootstrap.validate();
            net.minecraftforge.server.loading.ServerModLoader.load();
            MixinEnvironment.getCurrentEnvironment().audit();
            com.mojang.logging.LogUtils.getLogger().info("REFORGED_SERVER_MIXIN_AUDIT_PASSED (no world/server started)");
            System.exit(0);
        }
    }
}
