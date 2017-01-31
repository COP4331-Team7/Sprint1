package com.team7;

/**
 * A class of static methods accessible by the game engine
 * Used to generate different characteristics and game effects
 */
public class ProbabilityGenerator {
    public static boolean willOccur(double percentageOfEventOccuring){
        return Math.random() < percentageOfEventOccuring;
    }
}
