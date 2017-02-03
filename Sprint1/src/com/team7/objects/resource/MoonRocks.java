package com.team7.objects.resource;

import com.team7.ProbabilityGenerator;

/*
 * MoonRocks will affect Building stat by between +20 and +40
 */
public class MoonRocks extends Resource {
    public MoonRocks() {
        setStatInfluence(ProbabilityGenerator.randomInteger(20, 40));
        setDiscovered(false);
    }
}
