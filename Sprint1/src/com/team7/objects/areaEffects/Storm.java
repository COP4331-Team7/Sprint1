package com.team7.objects.areaEffects;

import com.team7.objects.areaEffects.AreaEffect;

/*
 * Storm will affect Health stat by -x
 */
public class Storm extends AreaEffect {
    public Storm() {
        setHealthEffect(-20);
    }
}
