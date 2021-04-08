package org.example.services;

import org.example.dto.Potion;
import org.example.dto.Weapon;

import java.util.Map;

import static org.example.util.ScheduleUtility.generateSpeedPotionSchedule;
import static org.example.util.ScheduleUtility.generateStrengthPotionSchedule;

public class ConsumableService {

    private HealthService healthService;
    private PlayerService playerService;
    private AppService appService;

    public ConsumableService(HealthService healthService, PlayerService playerService, AppService appService) {
        this.healthService = healthService;
        this.playerService = playerService;
        this.appService = appService;
    }

    public void consumePotion(Potion potion) {
        switch (potion.getType()) {
        case HEALTH:
            this.healthService.applyHealthModifier(potion.getStatValue());
            break;
        case STRENGTH:
            Map<String, Weapon> weaponInventory = this.appService.getPlayerState().getWeaponInventory();
            for (String weaponKey : weaponInventory.keySet()) {
                Weapon weapon = weaponInventory.get(weaponKey);
                weapon.setAttack(weapon.getAttack() + potion.getStatValue());
                weaponInventory.put(weaponKey, weapon);
            }
            this.appService.getPlayerState().setWeaponInventory(weaponInventory);
            generateStrengthPotionSchedule(appService, potion);
            break;
        case SPEED:
            this.playerService.setMoveSize(playerService.getMoveSize()
                    + playerService.getMoveSize() * potion.getStatValue() / 100);
            generateSpeedPotionSchedule(appService, playerService);
            break;
        default:
            break;
        }
    }
}
