package org.example.dto;

import org.example.enums.Archetype;

import java.util.List;

public class PlayerState {

    private String username;

    private Archetype archetype;

    private Weapon activeWeapon;

    private List<Weapon> weaponInventory;

    private String difficulty;

    private int goldAmount;

    public PlayerState(String username,
                       Archetype archetype,
                       Weapon activeWeapon, String difficulty) {
        this.username = username;
        this.archetype = archetype;
        this.activeWeapon = activeWeapon;
        this.difficulty = difficulty;
    }

    public PlayerState setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getUsername() {
        return username;
    }

    public PlayerState setUsername(String username) {
        this.username = username;
        return this;
    }

    public Archetype getArchetype() {
        return archetype;
    }

    public PlayerState setArchetype(Archetype archetype) {
        this.archetype = archetype;
        return this;
    }

    public Weapon getActiveWeapon() {
        return activeWeapon;
    }

    public PlayerState setActiveWeapon(Weapon activeWeapon) {
        this.activeWeapon = activeWeapon;
        return this;
    }

    public List<Weapon> getWeaponInventory() {
        return weaponInventory;
    }

    public PlayerState setWeaponInventory(List<Weapon> weaponInventory) {
        this.weaponInventory = weaponInventory;
        return this;
    }
}
