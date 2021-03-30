package org.example.dto;

import org.example.enums.Archetype;
import org.example.enums.Difficulty;
import org.example.enums.Direction;
import org.example.exceptions.PlayerCreationException;

import java.util.List;

public class PlayerState {

    private String username;

    private Archetype archetype;

    private Weapon activeWeapon;

    private List<Weapon> weaponInventory;

    private Difficulty difficulty;

    private int goldAmount;

    private int[] spawnCoordinates;

    private Direction spawnOrientation;

    private double health;

    private double healthCapacity;

    public PlayerState(String username,
                       Archetype archetype,
                       Difficulty difficulty,
                       int[] spawnCoordinates) throws PlayerCreationException {
        this.username = username;
        this.archetype = archetype;
        this.difficulty = difficulty;
        this.spawnCoordinates = spawnCoordinates;
        this.health = 100.0;
        this.healthCapacity = 100.0;
        assignDefaultWeaponForClass();
        assignDefaultGoldForDifficulty();
    }

    private void assignDefaultWeaponForClass() throws PlayerCreationException {
        switch (this.archetype) {
        case ARCHER:
            this.activeWeapon = new BasicBow();
            break;
        case MAGE:
            this.activeWeapon = new BasicStaff();
            break;
        case WARRIOR:
            this.activeWeapon = new BasicSword();
            break;
        default:
            throw new PlayerCreationException("Invalid archetype passed "
            + "for player weapon assignment");
        }
    }

    private void assignDefaultGoldForDifficulty() throws PlayerCreationException {
        switch (this.difficulty) {
        case EASY:
            this.goldAmount = 1000;
            break;
        case MEDIUM:
            this.goldAmount = 800;
            break;
        case HARD:
            this.goldAmount = 500;
            break;
        default:
            throw new PlayerCreationException("Invalid difficulty "
          + "passed for player gold assignment");
        }
    }

    public PlayerState setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Difficulty getDifficulty() {
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

    public int getGoldAmount() {
        return goldAmount;
    }

    public PlayerState setGoldAmount(int goldAmount) {
        this.goldAmount = goldAmount;
        return this;
    }

    public int[] getSpawnCoordinates() {
        return spawnCoordinates;
    }

    public PlayerState setSpawnCoordinates(int[] spawnCoordinates) {
        this.spawnCoordinates = spawnCoordinates;
        return this;
    }

    public Direction getSpawnOrientation() {
        return spawnOrientation;
    }

    public PlayerState setSpawnOrientation(Direction spawnOrientation) {
        this.spawnOrientation = spawnOrientation;
        return this;
    }

    public double getHealth() {
        return health;
    }

    public PlayerState setHealth(double health) {
        this.health = health;
        return this;
    }

    public double getHealthCapacity() {
        return healthCapacity;
    }

    public PlayerState setHealthCapacity(double healthCapacity) {
        this.healthCapacity = healthCapacity;
        return this;
    }
}
