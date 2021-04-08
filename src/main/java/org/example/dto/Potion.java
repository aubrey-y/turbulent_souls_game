package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.example.enums.PotionType;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BasicHealthPotion.class, name = "HEALTH"),
        @JsonSubTypes.Type(value = BasicSpeedPotion.class, name = "SPEED"),
        @JsonSubTypes.Type(value = BasicStrengthPotion.class, name = "STRENGTH")
})
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
