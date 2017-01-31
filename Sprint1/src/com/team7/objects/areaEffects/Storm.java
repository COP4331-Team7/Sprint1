package com.team7.objects.areaEffects;

import com.team7.ProbabilityGenerator;
import com.team7.objects.areaEffects.AreaEffect;

/*
 * Storm will affect Health stat by between -30 and -10
 */
public class Storm extends AreaEffect {
    public Storm() {
        setHealthEffect(ProbabilityGenerator.randomInteger(-30, -10));
    }
}
