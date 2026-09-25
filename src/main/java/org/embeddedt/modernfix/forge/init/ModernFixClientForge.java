// ModernFix Reforged addition, 2026-09-25. SPDX-License-Identifier: LGPL-3.0-or-later
package org.embeddedt.modernfix.forge.init;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import org.embeddedt.modernfix.ModernFixClient;
import org.embeddedt.modernfix.screen.ModernFixConfigScreen;
final class ModernFixClientForge {
    static void register(FMLJavaModLoadingContext context) {
        var client = new ModernFixClient();
        context.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
            () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, parent) -> new ModernFixConfigScreen(parent)));
        ServerStartedEvent.BUS.addListener(event -> client.onServerStarted(event.getServer()));
        RecipesUpdatedEvent.BUS.addListener(event -> client.onRecipesUpdated());
        TagsUpdatedEvent.BUS.addListener(event -> client.onTagsUpdated());
        TickEvent.RenderTickEvent.Post.BUS.addListener(event -> client.onRenderTickEnd());
    }
}
