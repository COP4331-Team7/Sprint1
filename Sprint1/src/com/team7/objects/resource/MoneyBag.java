package com.team7.objects.resource;

import com.team7.ProbabilityGenerator;

/*
 * MoneyBag will affect Money stat by between +20 and +40
 */
public class MoneyBag extends Resource {
    public MoneyBag(){
        setStatInfluence(ProbabilityGenerator.randomInteger(20, 40));
        setDiscovered(false);
    }
}
