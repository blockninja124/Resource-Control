package com.blockninja.resourcecontrol.forge;

import com.blockninja.resourcecontrol.ResourceControl;
import com.blockninja.resourcecontrol.commands.RC;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(ResourceControl.MOD_ID)
public final class ResourceControlForge {
    public ResourceControlForge() {

        MinecraftForge.EVENT_BUS.register(this.getClass());

        // Run our common setup.
        ResourceControl.init();
    }

    @SubscribeEvent
    public static void onRegisterCommands(final RegisterCommandsEvent event) {
        RC.register(event.getDispatcher());
    }
}
