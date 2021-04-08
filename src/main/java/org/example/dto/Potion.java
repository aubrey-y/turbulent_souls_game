package org.example.dto;

import org.example.enums.PotionType;

public class Potion extends Item {

    public Potion() { }

    protected PotionType type;

    protected String statLabel;

    protected int statValue;

    public String getStatLabel() {
        return statLabel;
    }

    public void setStatLabel(String statLabel) {
        this.statLabel = statLabel;
    }

    public int getStatValue() {
        return statValue;
    }

    public void setStatValue(int statValue) {
        this.statValue = statValue;
    }

    public PotionType getType() {
        return type;
    }

    public void setType(PotionType type) {
        this.type = type;
    }
}
