// ModernFix Reforged addition, 2026-09-25. SPDX-License-Identifier: LGPL-3.0-or-later
package org.embeddedt.modernfix.forge.init;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import org.embeddedt.modernfix.ModernFix;
@Mod(ModernFix.MODID)
public final class ModernFixForge {
    public static boolean launchDone;
    public static boolean registryEventsFired;
    public ModernFixForge(FMLJavaModLoadingContext context) {
        var common = new ModernFix();
        ServerStartedEvent.BUS.addListener(event -> common.onServerStarted());
        ServerStoppedEvent.BUS.addListener(event -> common.onServerDead(event.getServer()));
        FMLCommonSetupEvent.getBus(context.getModBusGroup()).addListener(event -> {
            registryEventsFired = true;
            event.enqueueWork(ModernFix::runAuditIfRequested);
        });
        if (FMLEnvironment.dist == Dist.CLIENT) ModernFixClientForge.register(context);
    }
}
