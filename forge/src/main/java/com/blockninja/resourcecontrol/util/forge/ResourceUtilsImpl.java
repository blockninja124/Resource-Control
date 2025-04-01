package com.blockninja.resourcecontrol.util.forge;

import net.minecraftforge.fml.util.thread.SidedThreadGroups;

public class ResourceUtilsImpl {
    public static boolean isOnServer() {
        return Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER;
    }
}
