package com.blockninja.resourcecontrol.network.fabric;

import com.blockninja.resourcecontrol.ResourceControl;
import com.blockninja.resourcecontrol.network.NetworkHandler;
import com.blockninja.resourcecontrol.network.packets.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Why must fabric be like this
public class NetworkHandlerImpl {

    public static void registerPackets() {
        registerReceivers(ActivatePacket.class);
        registerReceivers(DeactivatePacket.class);
        registerReceivers(LogPacksPacket.class);
        registerReceivers(LogToServerPacket.class);
        registerReceivers(MoveDownPacket.class);
        registerReceivers(MoveUpPacket.class);
        registerReceivers(ReloadAllPacket.class);
    }

    // Absolutely despicable
    private static <T extends RCPacket> void registerReceivers(Class<T> packetClass) {

        try {
            Method m = packetClass.getMethod("decode", FriendlyByteBuf.class);

            ClientPlayNetworking.registerGlobalReceiver(packetToRL(packetClass), (client, handler, buf, responseSender) -> {
                try {
                    Object e = m.invoke(null, buf);
                    if (packetClass.isInstance(e)) {
                        T e1 = (T) e;
                        e1.handleClient();
                    }
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Class "+packetClass+" is being used as a packet, but (self) decode(FriendlyByteBuf) method isn't allowed to be used!");
                }
            });

            ServerPlayNetworking.registerGlobalReceiver(packetToRL(packetClass), (minecraftServer, serverPlayer, serverGamePacketListener, friendlyByteBuf, packetSender) -> {
                try {
                    Object e = m.invoke(null, friendlyByteBuf);
                    if (packetClass.isInstance(e)) {
                        T e1 = (T) e;
                        e1.handleServer();
                    }
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Class "+packetClass+" is being used as a packet, but (self) decode(FriendlyByteBuf) method isn't allowed to be used!");
                }
            });

        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Class "+packetClass+" is being used as a packet, but has no (self) decode(FriendlyByteBuf) method!");
        }


    }

    public static <T extends RCPacket> void sendRCPacket(ServerPlayer to, T packet) {
        try {
            FriendlyByteBuf buffer = PacketByteBufs.create();
            Method m = packet.getClass().getMethod("encode", packet.getClass(), FriendlyByteBuf.class);
            m.invoke(null, packet, buffer);
            ServerPlayNetworking.send(to, packetToRL(packet.getClass()), buffer);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Class "+packet.getClass()+" is being used as a packet, but has no encode(self, FriendlyByteBuf) method!");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e + e.getTargetException().toString());
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Class "+packet.getClass()+" is being used as a packet, but encode(self, FriendlyByteBuf) method isn't allowed to be used!");
        }
    }

    public static <T extends RCPacket> void sendRCPacketToServer(T packet) {
        try {
            FriendlyByteBuf buffer = PacketByteBufs.create();
            Method m = packet.getClass().getMethod("encode", packet.getClass(), FriendlyByteBuf.class);
            m.invoke(null, packet, buffer);
            ClientPlayNetworking.send(packetToRL(packet.getClass()), buffer);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Class "+packet.getClass()+" is being used as a packet, but has no encode(self, FriendlyByteBuf) method!");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Class "+packet.getClass()+" is being used as a packet, but encode(self, FriendlyByteBuf) method isn't allowed to be used!");
        }
    }

    private static ResourceLocation packetToRL(Class<? extends RCPacket> packetClass) {
        return new ResourceLocation(ResourceControl.MOD_ID, packetClass.getSimpleName().toLowerCase());
    }
}
