package com.team7.objects.areaEffects;

import com.team7.ProbabilityGenerator;
import com.team7.objects.areaEffects.AreaEffect;

/*
 * Elixir Shower will affect Health stat between +20 to +30
 */
public class ElixirShower extends AreaEffect {
    public ElixirShower() {
        setHealthEffect(ProbabilityGenerator.randomInteger(20,30));
        setInstantDeath(false);
    }
}
