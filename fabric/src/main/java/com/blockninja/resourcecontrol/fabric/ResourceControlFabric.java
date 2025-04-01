package com.blockninja.resourcecontrol.fabric;

import com.blockninja.resourcecontrol.ResourceControl;
import com.blockninja.resourcecontrol.commands.CommandRegister;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public final class ResourceControlFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            CommandRegister.registerCommands(dispatcher);
        });


        // Run our common setup.
        ResourceControl.init();
    }
}
