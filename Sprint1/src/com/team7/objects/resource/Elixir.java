package com.team7.objects.resource;

import com.team7.ProbabilityGenerator;

/*
 * Elixir will affect Health stat by between +20 and +40
 */
public class Elixir extends Resource {
    public Elixir(){
        setStatInfluence(ProbabilityGenerator.randomInteger(20, 40));
    }
}
