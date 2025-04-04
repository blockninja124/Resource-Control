package com.blockninja.resourcecontrol.network.packets;

import com.blockninja.resourcecontrol.ResourceControl;
import net.minecraft.network.FriendlyByteBuf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogToServerPacket extends RCPacket {
    private static final Logger LOGGER = LogManager.getLogger(ResourceControl.MOD_ID);

    public final String message;
    public final int level;

    /**
     * Level is int between 0-2.
     * <p>0 = info</p>
     * <p>1 = warn</p>
     * <p>2 = error</p>
     * @param message
     * @param level
     */
    public LogToServerPacket(String message, int level) {
        this.message = message;
        this.level = level;
    }

    @SuppressWarnings("unused")
    public static void encode(LogToServerPacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.message);
        buf.writeInt(msg.level);
    }

    @SuppressWarnings("unused")
    public static LogToServerPacket decode(FriendlyByteBuf buf) {
        return new LogToServerPacket(buf.readUtf(), buf.readInt());
    }

    @Override
    public void handleServer() {
        switch (level) {
            case 0:
                LOGGER.info(message);
                break;
            case 1:
                LOGGER.warn(message);
                break;
            case 2:
                LOGGER.error(message);
                break;
            default:
                LOGGER.info(message);
                LOGGER.warn("LogToServerPacket was given an invalid log level. Expected between 0-2, was given {}", level);
                break;
        }
    }
}
