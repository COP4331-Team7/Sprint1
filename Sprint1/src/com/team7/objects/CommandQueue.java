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

    public void raiseCommmand(String commandString) {
        for (int i=0; i<commands.size(); i++) {
                if (commandString.equals(commands.get(i).getCommandString())) {
                    if (i==0) {
                        System.out.println("Can't Raise command any higher");
                        return;
                    }
                    Command temp = commands.get(i);
                    commands.set(i, commands.get(i-1));
                    commands.set(i-1, temp);
                    return;
            }
        }
    }

    public void lowerCommand(String commandString) {
        int size = commands.size();
        for (int i=0; i<size; i++) {
            if (commandString.equals(commands.get(i).getCommandString())) {
                System.out.println("Command found at index " + i);
                if (i == commands.size()-1) {
                    System.out.println("Can't Lower any further");
                    return;
                }
                Command temp = commands.get(i);
                commands.set(i, commands.get(i + 1));
                commands.set(i + 1, temp);
                return;
            }
        }
    }

    public void removeCommand(String commandString) {
        for (int i=0; i<commands.size(); i++) {
            if (commandString.equals(commands.get(i).getCommandString())) {
                commands.remove(i);
            }
        }
    }
}

