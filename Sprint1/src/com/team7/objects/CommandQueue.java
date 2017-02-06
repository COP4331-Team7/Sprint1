package com.team7.objects;

import java.util.ArrayList;

public class CommandQueue {

    // IMPORTANT: The 0th index in this list is the one to be run next, the highest index has the last to be run
    private ArrayList<Command> commands;

    public CommandQueue() {
        commands = new ArrayList<Command>();
    }
    
    private void addCommand(Command command){
        commands.add(command);
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }
}

