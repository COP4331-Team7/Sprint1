package com.team7.objects.items;

public abstract class Item {

    private boolean isPassable;
    private int statInfluence;

    public Item() {
    }

    public boolean isPassable() {
        return isPassable;
    }

    public void setPassable(boolean passable) {
        isPassable = passable;
    }

    public int getStatInfluence() {
        return statInfluence;
    }

    public void setStatInfluence(int statInfluence) {
        this.statInfluence = statInfluence;
    }
}
