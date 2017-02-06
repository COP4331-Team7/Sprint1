package com.team7.objects;

import java.util.ArrayList;

public class CommandQueue {

    private ArrayList<Command> commands;

    public CommandQueue() {
        commands = new ArrayList<Command>()
    }
    
    private void addCommand(Command command){
        commands.add(command);
    }

}

