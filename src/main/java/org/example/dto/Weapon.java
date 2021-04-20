package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.example.enums.WeaponType;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
    @JsonSubTypes.Type(value = BasicSword.class, name = "BASICSWORD"),
    @JsonSubTypes.Type(value = BasicStaff.class, name = "BASICSTAFF"),
    @JsonSubTypes.Type(value = BasicMagic.class, name = "BASICMAGIC"),
    @JsonSubTypes.Type(value = AdvancedSword.class, name = "ADVANCEDSWORD"),
    @JsonSubTypes.Type(value = AdvancedStaff.class, name = "ADVANCEDSTAFF"),
    @JsonSubTypes.Type(value = AdvancedMagic.class, name = "ADVANCEDMAGIC"),
    @JsonSubTypes.Type(value = ExpertSword.class, name = "EXPERTSWORD"),
    @JsonSubTypes.Type(value = ExpertStaff.class, name = "EXPERTSTAFF"),
    @JsonSubTypes.Type(value = ExpertMagic.class, name = "EXPERTMAGIC"),
    @JsonSubTypes.Type(value = MasterSword.class, name = "MASTERSWORD"),
    @JsonSubTypes.Type(value = MasterStaff.class, name = "MASTERSTAFF"),
    @JsonSubTypes.Type(value = MasterMagic.class, name = "MASTERMAGIC")
})
public abstract class Weapon extends Item {

    protected WeaponType type;

    protected int attack;

    protected double range;

    protected String animationLeft;

    protected String animationRight;

    protected String attackAnimationLeft;

    protected String attackAnimationRight;

    public Weapon() {

    }

    public WeaponType getType() {
        return type;
    }

    public Weapon setType(WeaponType type) {
        this.type = type;
        return this;
    }

    public int getAttack() {
        return attack;
    }

    public Weapon setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public double getRange() {
        return range;
    }

    public Weapon setRange(double range) {
        this.range = range;
        return this;
    }

    public String getAnimationLeft() {
        return animationLeft;
    }

    public Weapon setAnimationLeft(String animationLeft) {
        this.animationLeft = animationLeft;
        return this;
    }

    public String getAnimationRight() {
        return animationRight;
    }

    public Weapon setAnimationRight(String animationRight) {
        this.animationRight = animationRight;
        return this;
    }

    public String getAttackAnimationLeft() {
        return attackAnimationLeft;
    }

    public Weapon setAttackAnimationLeft(String attackAnimationLeft) {
        this.attackAnimationLeft = attackAnimationLeft;
        return this;
    }

    public String getAttackAnimationRight() {
        return attackAnimationRight;
    }

    public Weapon setAttackAnimationRight(String attackAnimationRight) {
        this.attackAnimationRight = attackAnimationRight;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.type,
                this.attack,
                this.range,
                this.animationLeft,
                this.animationRight,
                this.attackAnimationLeft,
                this.attackAnimationRight
        );
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Weapon)) {
            return false;
        }
        Weapon weapon = (Weapon) o;
        return this.type == weapon.type
                && this.attack == weapon.attack
                && this.range == weapon.range
                && ((this.animationLeft == null && weapon.animationLeft == null)
                        || (this.animationLeft != null
                        && this.animationLeft.equals(weapon.animationLeft)))
                && ((this.animationRight == null && weapon.animationRight == null)
                        || (this.animationRight != null
                        && this.animationRight.equals(weapon.animationRight)))
                && ((this.attackAnimationLeft == null && weapon.attackAnimationLeft == null)
                        || (this.attackAnimationLeft != null
                        && this.attackAnimationLeft.equals(weapon.attackAnimationLeft)))
                && ((this.attackAnimationRight == null && weapon.attackAnimationRight == null)
                        || (this.attackAnimationRight != null
                        && this.attackAnimationRight.equals(weapon.attackAnimationRight)));
    }
}
