package com.blockninja.resourcecontrol.network.packets;

import com.blockninja.resourcecontrol.util.ResourceUtils;
import net.minecraft.network.FriendlyByteBuf;

public class ActivatePacket extends RCPacket {
    public final String packName;
    public final boolean reload;

    public ActivatePacket(String packName, boolean reload) {
        this.packName = packName;
        this.reload = reload;
    }

    @SuppressWarnings("unused")
    public static void encode(ActivatePacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.packName);
        buf.writeBoolean(msg.reload);
    }

    @SuppressWarnings("unused")
    public static ActivatePacket decode(FriendlyByteBuf buf) {
        return new ActivatePacket(buf.readUtf(), buf.readBoolean());
    }

    @Override
    public void handleClient() {
        ResourceUtils.activatePack(packName, reload);
    }
}
