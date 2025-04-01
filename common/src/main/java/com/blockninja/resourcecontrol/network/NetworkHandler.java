package com.blockninja.resourcecontrol.network;

import com.blockninja.resourcecontrol.ResourceControl;
import com.blockninja.resourcecontrol.network.packets.RCPacket;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class NetworkHandler {

    public static final ResourceLocation networkRL = new ResourceLocation(ResourceControl.MOD_ID, "network");

    @ExpectPlatform
    public static void registerPackets() {

    }

    @ExpectPlatform
    public static <T extends RCPacket> void sendRCPacket(ServerPlayer to, T packet) {
        throw new AssertionError("not platformed");
    }

    @ExpectPlatform
    public static <T extends RCPacket> void sendRCPacketToServer(T packet) {
        throw new AssertionError("not platformed");
    }
}

