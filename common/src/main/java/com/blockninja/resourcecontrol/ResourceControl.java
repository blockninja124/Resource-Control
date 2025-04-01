package com.blockninja.resourcecontrol;

import com.blockninja.resourcecontrol.network.NetworkHandler;

public final class ResourceControl {
    public static final String MOD_ID = "resourcecontrol";

    public static void init() {
        NetworkHandler.registerPackets();
        // Write common init code here.
    }
}
