package com.blockninja.resourcecontrol.network.packets;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public abstract class RCPacket {

    public RCPacket() {}

    public void handleClient() {};

    public void handleServer() {};
}
