package com.team7.objects;

import java.util.ArrayList;

public class CommandQueue {

    // IMPORTANT: The last index in this list is the one to be run next
    private ArrayList<Command> commands;

    public CommandQueue() {
        commands = new ArrayList<Command>();
    }
    
    private void addCommand(Command command){
        commands.add(command);
    }

}

