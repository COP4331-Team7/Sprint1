package com.team7.objects.areaEffects;

import com.team7.objects.areaEffects.AreaEffect;

/*
 * Volcanic Vent affects Health stat by causing instant death
 */
public class VolcanicVent extends AreaEffect {
    public VolcanicVent() {
        setHealthEffect(0);
        setInstantDeath(true);
    }
}
