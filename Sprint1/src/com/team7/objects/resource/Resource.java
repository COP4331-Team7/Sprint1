package com.team7.objects.resource;

public abstract class Resource {
    private long id;
    private int statInfluence;  // Player Stats gets updated automatically

    public Resource() {
    }

    public int getStatInfluence() {
        return statInfluence;
    }

    public void setStatInfluence(int statInfluence) {
        this.statInfluence = statInfluence;
    }
}
