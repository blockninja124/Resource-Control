package com.blockninja.resourcecontrol.network.packets;

import com.blockninja.resourcecontrol.util.ResourceUtils;
import net.minecraft.network.FriendlyByteBuf;

public class DeactivatePacket extends RCPacket {
    public final String packName;
    public final boolean reload;

    public DeactivatePacket(String packName, boolean reload) {
        this.packName = packName;
        this.reload = reload;
    }

    @SuppressWarnings("unused")
    public static void encode(DeactivatePacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.packName);
        buf.writeBoolean(msg.reload);
    }

    @SuppressWarnings("unused")
    public static DeactivatePacket decode(FriendlyByteBuf buf) {
        return new DeactivatePacket(buf.readUtf(), buf.readBoolean());
    }

    @Override
    public void handleClient() {
        ResourceUtils.deactivatePack(packName, reload);
    }
}
