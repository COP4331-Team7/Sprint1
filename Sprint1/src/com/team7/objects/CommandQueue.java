package com.team7.objects;

import java.util.ArrayList;

public class CommandQueue {

    // IMPORTANT: The 0th index in this list is the one to be run next, the highest index has the last to be run
    private ArrayList<CommandObject> commands;

    public CommandQueue() {
        commands = new ArrayList<CommandObject>();
    }
    
    public void addCommand(CommandObject command){
        commands.add(command);
    }

    public ArrayList<CommandObject> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<CommandObject> commands) {
        this.commands = commands;
    }
}

