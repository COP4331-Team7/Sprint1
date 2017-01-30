package com.team7.objects.items;

/*
 * OneShotItem is picked up and can affect stats
 * In the first iteration, a OneShotItem is equivalent to finding Elixir Resource
 */

public class OneShotItem extends Item {
	public OneShotItem() {
	    setPassable(true);
	    setStatInfluence(20);
    }
}
