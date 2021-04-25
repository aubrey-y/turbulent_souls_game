package org.example;

import org.example.dao.PlayerState;
import org.example.exceptions.PlayerCreationException;
import org.example.services.AppService;
import org.example.services.GoldService;
import org.junit.jupiter.api.BeforeEach;
import javafx.scene.control.Label;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.example.controllers.SecondaryController.SPAWN_COORDINATES;
import static org.example.enums.Archetype.WARRIOR;
import static org.example.enums.Difficulty.HARD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@Disabled
@ExtendWith(ApplicationExtension.class)
public class GoldServiceTest {

    private AppService appService;

    private GoldService goldService;

    private Label goldAmount;

    private final double maximumStartingHealth = 100.0;

    @BeforeEach
    public void setUp() {
        this.appService = mock(AppService.class);
        this.goldAmount = new Label();
        this.goldService = new GoldService(appService, goldAmount);
    }

    @Test
    public void testAdjustGoldAmountPositiveNumberShouldIncreasePlayerGold() {
        //Given
        PlayerState playerState = this.getStandardPlayerState(maximumStartingHealth);
        this.withMockedAppService(playerState);
        int goldChange = 100;
        int expected = playerState.getGoldAmount() + goldChange;

        //When
        this.goldService.adjustGoldAmount(goldChange);
        int actual = playerState.getGoldAmount();

        //Then
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void testAdjustGoldAmountNegativeNumberShouldDecreasePlayerGold() {
        //Given
        PlayerState playerState = this.getStandardPlayerState(maximumStartingHealth);
        this.withMockedAppService(playerState);
        int goldChange = -100;
        int expected = playerState.getGoldAmount() + goldChange;

        //When
        this.goldService.adjustGoldAmount(goldChange);
        int actual = playerState.getGoldAmount();

        //Then
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void testPlayerCanAffordAmountEqual() {
        //Given
        PlayerState playerState = this.getStandardPlayerState(maximumStartingHealth);
        this.withMockedAppService(playerState);
        boolean expected = true;

        //When
        boolean actual = goldService.playerCanAffordAmount(playerState.getGoldAmount());

        //Then
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void testPlayerCanAffordAmountGreater() {
        //Given
        PlayerState playerState = this.getStandardPlayerState(maximumStartingHealth);
        this.withMockedAppService(playerState);
        boolean expected = false;

        //When
        boolean actual = goldService.playerCanAffordAmount(playerState.getGoldAmount() + 500);

        //Then
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void testPlayerCanAffordAmountLess() {
        //Given
        PlayerState playerState = this.getStandardPlayerState(maximumStartingHealth);
        this.withMockedAppService(playerState);
        boolean expected = true;

        //When
        boolean actual = goldService.playerCanAffordAmount(playerState.getGoldAmount() - 200);

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
