// ModernFix Reforged addition, 2026-09-25. SPDX-License-Identifier: LGPL-3.0-or-later
package org.embeddedt.modernfix.platform.forge;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.LoadingModList;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.embeddedt.modernfix.api.constants.IntegrationConstants;
import org.embeddedt.modernfix.core.ModernFixMixinPlugin;
import org.embeddedt.modernfix.forge.init.ModernFixForge;
import org.embeddedt.modernfix.platform.ModernFixPlatformHooks;
import org.embeddedt.modernfix.spark.SparkLaunchProfiler;
import org.embeddedt.modernfix.util.CommonModUtil;
import org.objectweb.asm.tree.ClassNode;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Consumer;
public final class ModernFixPlatformHooksImpl implements ModernFixPlatformHooks {
    public boolean isClient() { return FMLLoader.getDist() == Dist.CLIENT; }
    public boolean isDedicatedServer() { return !isClient(); }
    public String getVersionString() { return "0.1.0-alpha.1+mc26.3"; }
    public boolean modPresent(String id) { return LoadingModList.getModFileById(id) != null; }
    public boolean isDevEnv() { return !FMLLoader.isProduction(); }
    public MinecraftServer getCurrentServer() { return ServerLifecycleHooks.getCurrentServer(); }
    public boolean isEarlyLoadingNormally() { return LoadingModList.getErrors().isEmpty(); }
    public boolean isLoadingNormally() { return isEarlyLoadingNormally(); }
    public Path getGameDirectory() { return FMLPaths.GAMEDIR.get(); }
    public void sendPacket(ServerPlayer player, CustomPacketPayload payload) { player.connection.send(new ClientboundCustomPayloadPacket(payload)); }
    public void applyASMTransformers(String mixin, ClassNode target) { }
    public void injectPlatformSpecificHacks() {
        if (ModernFixMixinPlugin.instance.isOptionEnabled("feature.spark_profile_launch.OnForge"))
            CommonModUtil.runWithoutCrash(() -> SparkLaunchProfiler.start("launch"), "Failed to start profiler");
    }
    public void onServerCommandRegister(Consumer<CommandDispatcher<CommandSourceStack>> handler) {
        RegisterCommandsEvent.BUS.addListener(event -> handler.accept(event.getDispatcher()));
    }
    public Multimap<String,String> getCustomModOptions() {
        Multimap<String,String> result = ArrayListMultimap.create();
        for (var mod : LoadingModList.getMods()) {
            mod.getConfigElement(IntegrationConstants.INTEGRATIONS_KEY).ifPresent(raw -> {
                if (raw instanceof Map<?,?> options) options.forEach((key,value) -> {
                    if (key instanceof String k && value instanceof String v) result.put(k,v);
                });
            });
        }
        return result;
    }
    public void onLaunchComplete() {
        if (ModernFixMixinPlugin.instance.isOptionEnabled("feature.spark_profile_launch.OnForge"))
            CommonModUtil.runWithoutCrash(() -> SparkLaunchProfiler.stop("launch"), "Failed to stop profiler");
        ModernFixForge.launchDone = true;
    }
    public String getPlatformName() { return "Forge"; }
}
