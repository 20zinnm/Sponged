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

import me.meyerzinn.sponged.Sponged;
import org.reflections.Reflections;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.plugin.Plugin;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 * Created by meyerzinn on 2/5/16.
 */
public class ModuleRegistry {

    private static ModuleRegistry ourInstance = new ModuleRegistry();

    public static ModuleRegistry getInstance() {
        return ourInstance;
    }

    private HashMap<String, Module> loadedModules = new HashMap<>();

    private ModuleRegistry() {
    }

    public void registerModule(@Nonnull Plugin plugin, @Nonnull Module module) {
        loadedModules.put(module.name(), module);
        Sponge.getGame().getEventManager().registerListeners(plugin, module);
    }

    public Optional<Module> getModuleByName(String name) {
        return Optional.of(loadedModules.get(name));
    }

    public void registerAllModules(Sponged plugin, String where) {
        Reflections reflections = new Reflections(where);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(SpongedModule.class);
        for (Class<?> clazz : annotated) {
            try {
                Module module = (Module) clazz.newInstance();
                registerModule((Plugin) plugin, module);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}