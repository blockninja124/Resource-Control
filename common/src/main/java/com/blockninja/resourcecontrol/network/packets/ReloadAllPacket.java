package com.blockninja.resourcecontrol.network.packets;

import com.blockninja.resourcecontrol.ResourceControl;
import com.blockninja.resourcecontrol.util.ResourceUtils;
import net.minecraft.network.FriendlyByteBuf;

public class ReloadAllPacket extends RCPacket {

    @SuppressWarnings("unused")
    public static void encode(ReloadAllPacket msg, FriendlyByteBuf buf) {}

    @SuppressWarnings("unused")
    public static ReloadAllPacket decode(FriendlyByteBuf buf) {
        return new ReloadAllPacket();
    }

    @Override
    public void handleClient() {
        ResourceUtils.reloadAll();
    }
}
