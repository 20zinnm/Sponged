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

import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.plugin.Plugin;

/**
 * Created by meyerzinn on 2/5/16.
 */
public abstract class Module implements CommandExecutor {

    public enum Scope {
        PLAYER,
        GLOBAL;
    }

    private Plugin plugin;

    public Module(Plugin plugin) {
        this.plugin = plugin;
    }

    public final Plugin getPlugin() {
        return plugin;
    }

    public abstract Scope scope();

    public abstract String name();

    public abstract CommandSpec subCommandSpec();

    public final String getPermission() {
        return "sponged.module." + name();
    }

}
