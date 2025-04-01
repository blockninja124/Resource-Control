package com.blockninja.resourcecontrol.network.packets;

import com.blockninja.resourcecontrol.util.ResourceUtils;
import net.minecraft.network.FriendlyByteBuf;

public class MoveDownPacket extends RCPacket {
    public final String packName;
    public final int amount;
    public final boolean reload;

    public MoveDownPacket(String packName, int amount, boolean reload) {
        this.packName = packName;
        this.amount = amount;
        this.reload = reload;
    }

    @SuppressWarnings("unused")
    public static void encode(MoveDownPacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.packName);
        buf.writeInt(msg.amount);
        buf.writeBoolean(msg.reload);
    }

    @SuppressWarnings("unused")
    public static MoveDownPacket decode(FriendlyByteBuf buf) {
        return new MoveDownPacket(buf.readUtf(), buf.readInt(), buf.readBoolean());
    }

    @Override
    public void handleClient() {
        ResourceUtils.movePackDown(packName, amount, reload);
    }
}
