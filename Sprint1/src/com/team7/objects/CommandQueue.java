package com.team7.objects;

import java.util.Queue;

public class CommandQueue {
    private Queue<String> commands;
    
    private void addCommand(String command){
        commands.add(command);
    }

    private String getNextCommand(){
        return commands.remove();
    }
}
