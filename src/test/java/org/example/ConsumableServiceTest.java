package org.example;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.example.dto.BasicHealthPotion;
import org.example.dto.BasicSpeedPotion;
import org.example.dto.BasicStrengthPotion;
import org.example.dto.PlayerState;
import org.example.dto.Potion;
import org.example.dto.Weapon;
import org.example.exceptions.PlayerCreationException;
import org.example.services.AppService;
import org.example.services.ConsumableService;
import org.example.services.HealthService;
import org.example.services.InventoryService;
import org.example.services.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.example.controllers.SecondaryController.SPAWN_COORDINATES;
import static org.example.enums.Archetype.WARRIOR;
import static org.example.enums.Difficulty.HARD;
import static org.example.services.PlayerService.DEFAULT_MOVE_SIZE;
import static org.example.util.ResourcePathUtility.BASIC_SWORD_PATH;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@Disabled
@ExtendWith(ApplicationExtension.class)
public class ConsumableServiceTest {

    private AppService appService;

    private HealthService healthService;

    private ProgressBar healthBar;

    private Label healthText;

    private final double maximumStartingHealth = 100.0;

    private PlayerState finalPlayerState;

    private InventoryService inventoryService;

    private ConsumableService consumableService;

    private PlayerService playerService;

    @BeforeEach
    public void setUp() {
        this.playerService = spy(PlayerService.class);
        this.appService = mock(AppService.class);
        this.healthBar = new ProgressBar();
        this.healthText = new Label();
        this.healthService = new HealthService(this.appService)
                .setHealthBar(this.healthBar)
                .setHealthText(this.healthText);
        this.inventoryService = new InventoryService(this.appService);
        this.consumableService = new ConsumableService(this.healthService,
                this.playerService, this.appService);
    }

    @Test
    public void testApplyHealthPotion() {
        //Given
        double healthDiff = -10.0;
        PlayerState playerState = this.getStandardPlayerState(
                this.maximumStartingHealth + healthDiff);
        this.withMockedAppService(playerState);
        Potion healthPotion = new BasicHealthPotion();

        //When
        this.consumableService.consumePotion(healthPotion);

        //Then
        assertThat(playerState.getHealth(),
                is(equalTo(this.maximumStartingHealth + healthDiff + healthPotion.getStatValue())));
    }

    @Test
    public void testApplyHealthPotionWithFullHealthDoesNotGoOverCapacity() {
        //Given
        PlayerState playerState = this.getStandardPlayerState(this.maximumStartingHealth);
        this.withMockedAppService(playerState);
        Potion healthPotion = new BasicHealthPotion();

        //When
        this.consumableService.consumePotion(healthPotion);

        //Then
        assertThat(playerState.getHealth(),
                is(equalTo(this.maximumStartingHealth)));
    }

    @Test
    public void testApplySpeedPotion() {
        //Given
        PlayerState playerState = this.getStandardPlayerState(this.maximumStartingHealth);
        this.withMockedAppService(playerState);

        //When
        Potion speedPotion = new BasicSpeedPotion();
        this.consumableService.consumePotion(speedPotion);

        //Then
        assertThat(this.playerService.getMoveSize(),
                is(equalTo(DEFAULT_MOVE_SIZE + DEFAULT_MOVE_SIZE
                        * speedPotion.getStatValue() / 100)));

    }

    @Test
    public void testApplyStrengthPotion() {
        //Given
        PlayerState playerState = this.getStandardPlayerState(this.maximumStartingHealth);
        this.withMockedAppService(playerState);
        Potion strengthPotion = new BasicStrengthPotion();
        int expected = ((Weapon) playerState.getWeaponInventory().get(BASIC_SWORD_PATH)).getAttack()
                + strengthPotion.getStatValue();

        //When
        this.consumableService.consumePotion(strengthPotion);
        int actual = ((Weapon) playerState.getWeaponInventory().get(BASIC_SWORD_PATH)).getAttack();

        //Then
        assertThat(actual, is(equalTo(expected)));
    }

    private void withMockedAppService(PlayerState playerState) {
        doReturn(playerState)
                .when(this.appService).getPlayerState();
    }

    private PlayerState getStandardPlayerState(double startingHealth) {
        PlayerState playerState;
        try {
            playerState = new PlayerState("Jimmy", WARRIOR, HARD, SPAWN_COORDINATES)
                    .setHealth(startingHealth);
        } catch (PlayerCreationException e) {
            throw new RuntimeException(e);
        }
        return playerState;
    }
}
