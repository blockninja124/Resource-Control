package com.blockninja.resourcecontrol.network.packets;

import com.blockninja.resourcecontrol.util.ResourceUtils;
import net.minecraft.network.FriendlyByteBuf;

public class MoveUpPacket extends RCPacket {
    public final String packName;
    public final int amount;
    public final boolean reload;

    public MoveUpPacket(String packName, int amount, boolean reload) {
        this.packName = packName;
        this.amount = amount;
        this.reload = reload;
    }

    @SuppressWarnings("unused")
    public static void encode(MoveUpPacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.packName);
        buf.writeInt(msg.amount);
        buf.writeBoolean(msg.reload);
    }

    @SuppressWarnings("unused")
    public static MoveUpPacket decode(FriendlyByteBuf buf) {
        return new MoveUpPacket(buf.readUtf(), buf.readInt(), buf.readBoolean());
    }

    @Override
    public void handleClient() {
        ResourceUtils.movePackUp(packName, amount, reload);
    }
}
