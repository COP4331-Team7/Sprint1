package com.team7.objects.resource;

import com.team7.ProbabilityGenerator;

/*
 * Hieroglyphic Books will affect Research stat by between +20 and +40
 */
public class HieroglyphicBooks extends Resource {
    public HieroglyphicBooks() {
        setStatInfluence(ProbabilityGenerator.randomInteger(20, 40));
        setDiscovered(false);
    }
}
