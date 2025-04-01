package com.blockninja.resourcecontrol.network.packets;

import com.blockninja.resourcecontrol.util.ResourceUtils;
import net.minecraft.network.FriendlyByteBuf;

public class LogPacksPacket extends RCPacket {

    @SuppressWarnings("unused")
    public static void encode(LogPacksPacket msg, FriendlyByteBuf buf) {}

    @SuppressWarnings("unused")
    public static LogPacksPacket decode(FriendlyByteBuf buf) {
        return new LogPacksPacket();
    }

    @Override
    public void handleClient() {
        ResourceUtils.logPacks(ResourceUtils.getPackRepo());
    }
}
