/*
 * Copyright (c) 2016 meyerzinn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.meyerzinn.sponged;

import com.google.inject.Inject;
import me.meyerzinn.sponged.modules.util.ModuleRegistry;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by meyerzinn on 2/5/16.
 */
@Plugin(id = "sponged", name = "Sponged", version = "1.0-SNAPSHOT")
public class Sponged {

    private static Sponged instance;

    @Inject
    private Logger logger;

    @Inject
    private Game game;

    @Inject
    @DefaultConfig(sharedRoot = false)
    private Path defaultConfig;

    @Inject
    @DefaultConfig(sharedRoot = false)
    private ConfigurationLoader<CommentedConfigurationNode> loader;

    private ConfigurationNode rootNode;

    public static Sponged getInstance() {
        return instance;
    }

    public ConfigurationNode getRootNode() {
        return rootNode;
    }


    @Listener
    public void onInit(GameInitializationEvent event) {
        try {

            rootNode = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Listener
    public void onGameStarting(GameStartingServerEvent event) {
        ModuleRegistry.getInstance().registerAllModules(this, "me.meyerzinn.sponged.modules");
        CommandSpec.Builder commandSpec = CommandSpec.builder().description(Text.of("The base command for Sponged.")).arguments(GenericArguments
                .string(Text.of("module")));
        Sponge.getCommandManager().register(this, commandSpec.build(), "sponged");
    }

}
