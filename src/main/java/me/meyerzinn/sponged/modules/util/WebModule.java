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

package me.meyerzinn.sponged.modules.util;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.util.concurrent.TimeUnit;

/**
 * Created by meyerzinn on 2/5/16.
 */
@SpongedModule
public class WebModule extends Module {

    public WebModule(Plugin plugin) {
        super(plugin);
    }

    @Override public Scope scope() {
        return Scope.PLAYER;
    }

    @Override public String name() {
        return "web";
    }

    @Override public CommandSpec subCommandSpec() {
        return CommandSpec.builder()
                .arguments(GenericArguments.player(Text.of("player")), GenericArguments.integer(Text.of("duration")))
                .permission(getPermission())
                .description(Text.of("Trap a player in cobwebs!"))
                .executor(this)
                .build();
    }

    @Override public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Sponge.getScheduler().createSyncExecutor(getPlugin()).schedule(() -> {
                }
                , 1000L, TimeUnit.MILLISECONDS);
        return CommandResult.success();
    }


}
