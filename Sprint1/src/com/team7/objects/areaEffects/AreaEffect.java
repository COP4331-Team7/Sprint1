package com.team7.objects.areaEffects;

public abstract class AreaEffect {
    private boolean isInstantDeath;
    private int healthEffect;// -100 to 100

    public AreaEffect() {
    }

    public boolean isInstantDeath() {
        return isInstantDeath;
    }

    public void setInstantDeath(boolean instantDeath) {
        isInstantDeath = instantDeath;
    }

    public int getHealthEffect() {
        return healthEffect;
    }

    public void setHealthEffect(int healthEffect) {
        this.healthEffect = healthEffect;
    }
}
