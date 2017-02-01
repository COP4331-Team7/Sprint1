package com.team7.objects.items;

/*
 * OneShotItem is picked up and can affect stats
 * In the first iteration, a OneShotItem is equivalent to finding MoneyBag Resource
 */

import com.team7.ProbabilityGenerator;

public class OneShotItem extends Item {
	public OneShotItem() {
	    setPassable(true);
	    setStatInfluence(ProbabilityGenerator.randomInteger(15,30));
    }
}
