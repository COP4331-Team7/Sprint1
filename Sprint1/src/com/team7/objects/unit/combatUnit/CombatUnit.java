package com.team7.objects.unit.combatUnit;

import com.team7.objects.Army;
import com.team7.objects.unit.Unit;

public abstract class CombatUnit extends Unit {

    private Army army;

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }
}
