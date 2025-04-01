package com.blockninja.resourcecontrol.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;

public class CommandRegister {
    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        RC.register(dispatcher);
    }
}
