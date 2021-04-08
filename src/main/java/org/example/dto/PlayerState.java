package org.example.dto;

import org.example.enums.Archetype;
import org.example.enums.Difficulty;
import org.example.enums.Direction;
import org.example.exceptions.PlayerCreationException;

import java.util.HashMap;
import java.util.Map;

public class PlayerState {

    private String username;

    private Archetype archetype;

    private Weapon activeWeapon;

    private Map<String, Item> weaponInventory = new HashMap<>();

    private Map<String, Item> generalInventory = new HashMap<>();

    private Difficulty difficulty;

    private int goldAmount;

    private Coordinate spawnCoordinates;

    private Direction spawnOrientation;

    private double health;

    private double healthCapacity;

    private String email;

    private String lastUpdated;

    public PlayerState() {

    }

    public PlayerState(PlayerState playerState) {
        this.username = playerState.username;
        this.archetype = playerState.archetype;
        this.activeWeapon = playerState.activeWeapon;
        for (String key : playerState.weaponInventory.keySet()) {
            this.weaponInventory.put(key, playerState.weaponInventory.get(key));
        }
        for (String key : playerState.generalInventory.keySet()) {
            this.generalInventory.put(key, playerState.generalInventory.get(key));
        }
        this.difficulty = playerState.difficulty;
        this.goldAmount = playerState.goldAmount;
        this.spawnCoordinates = playerState.spawnCoordinates;
        this.spawnOrientation = playerState.spawnOrientation;
        this.health = playerState.health;
        this.healthCapacity = playerState.healthCapacity;
        this.email = playerState.email;
        this.lastUpdated = playerState.lastUpdated;
    }

    public PlayerState(String username,
                       Archetype archetype,
                       Difficulty difficulty,
                       Coordinate spawnCoordinates) throws PlayerCreationException {
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
        case WIZARD:
            this.activeWeapon = new BasicMagic();
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
        this.weaponInventory.put(this.activeWeapon.getImagePath(), this.activeWeapon);
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

    public Map<String, Item> getWeaponInventory() {
        return weaponInventory;
    }

    public PlayerState setWeaponInventory(Map<String, Item> weaponInventory) {
        this.weaponInventory = weaponInventory;
        return this;
    }

    public Map<String, Item> getGeneralInventory() {
        return generalInventory;
    }

    public PlayerState setGeneralInventory(Map<String, Item> generalInventory) {
        this.generalInventory = generalInventory;
        return this;
    }

    public int getGoldAmount() {
        return goldAmount;
    }

    public PlayerState setGoldAmount(int goldAmount) {
        this.goldAmount = goldAmount;
        return this;
    }

    public Coordinate getSpawnCoordinates() {
        return spawnCoordinates;
    }

    public PlayerState setSpawnCoordinates(Coordinate spawnCoordinates) {
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

    public String getEmail() {
        return email;
    }

    public PlayerState setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public PlayerState setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }
}
