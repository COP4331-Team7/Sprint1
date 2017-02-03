package com.team7.objects.resource;

public abstract class Resource {

    private int statInfluence;  // Player Stats gets updated automatically
    private boolean isDiscovered;   //whether a Resource level has been discovered by an Explorer

    public Resource() {
    }

    public int getStatInfluence() {
        return statInfluence;
    }

    public void setStatInfluence(int statInfluence) {
        this.statInfluence = statInfluence;
    }

    public boolean isDiscovered() {
        return isDiscovered;
    }

    public void setDiscovered(boolean discovered) {
        isDiscovered = discovered;
    }
}
