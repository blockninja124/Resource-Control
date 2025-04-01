package com.blockninja.resourcecontrol.network.forge;

import com.blockninja.resourcecontrol.ResourceControl;
import com.blockninja.resourcecontrol.network.NetworkHandler;
import com.blockninja.resourcecontrol.network.packets.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class NetworkHandlerImpl {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            NetworkHandler.networkRL,
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void registerPackets() {
        int id = 0;

        INSTANCE.registerMessage(id++,
                ReloadAllPacket.class,
                ReloadAllPacket::encode,
                ReloadAllPacket::decode,
                NetworkHandlerImpl::handle
        );

        INSTANCE.registerMessage(id++,
                MoveUpPacket.class,
                MoveUpPacket::encode,
                MoveUpPacket::decode,
                NetworkHandlerImpl::handle
        );

        INSTANCE.registerMessage(id++,
                MoveDownPacket.class,
                MoveDownPacket::encode,
                MoveDownPacket::decode,
                NetworkHandlerImpl::handle
        );

        INSTANCE.registerMessage(id++,
                ActivatePacket.class,
                ActivatePacket::encode,
                ActivatePacket::decode,
                NetworkHandlerImpl::handle
        );

        INSTANCE.registerMessage(id++,
                DeactivatePacket.class,
                DeactivatePacket::encode,
                DeactivatePacket::decode,
                NetworkHandlerImpl::handle
        );

        INSTANCE.registerMessage(id++,
                LogPacksPacket.class,
                LogPacksPacket::encode,
                LogPacksPacket::decode,
                NetworkHandlerImpl::handle
        );

        INSTANCE.registerMessage(id++,
                LogToServerPacket.class,
                LogToServerPacket::encode,
                LogToServerPacket::decode,
                NetworkHandlerImpl::handle
        );
    }

    private static <T extends RCPacket> void handle(T msg, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            if (ctx.get().getSender() == null) {
                // Client
                msg.handleClient();
            } else {
                // Server
                msg.handleServer();
            }
        });

        ctx.get().setPacketHandled(true);

    }

    public static <T extends RCPacket> void sendRCPacket(ServerPlayer to, T packet) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> to), packet);
    }

    public static <T extends RCPacket> void sendRCPacketToServer(T packet) {
        INSTANCE.sendToServer(packet);
    }
}
