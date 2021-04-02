package org.example;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.example.dto.PlayerState;
import org.example.exceptions.PlayerCreationException;
import org.example.services.AppService;
import org.example.services.HealthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.example.enums.Archetype.WARRIOR;
import static org.example.enums.Difficulty.HARD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@Disabled
@ExtendWith(ApplicationExtension.class)
public class HealthServiceTest {

    private AppService appService;

    private HealthService healthService;

    private ProgressBar healthBar;

    private Label healthText;

    private final double maximumStartingHealth = 100.0;

    private PlayerState finalPlayerState;

    @BeforeEach
    public void setUp() {
        this.appService = mock(AppService.class);
        this.healthBar = new ProgressBar();
        this.healthText = new Label();
        this.healthService = new HealthService(this.appService)
                .setHealthBar(this.healthBar)
                .setHealthText(this.healthText);
    }

    @Test
    public void testApplyHealthModifierReducesHealth() {
        //Given
        this.withMockedAppService(this.maximumStartingHealth);
        double healthDiff = -1.0;

        //When
        boolean playerAlive = this.healthService.applyHealthModifier(healthDiff);

        //Then
        assertThat(playerAlive, is(true));
        assertThat(this.finalPlayerState.getHealth(),
                is(equalTo(this.maximumStartingHealth + healthDiff)));
    }

    @Test
    public void testApplyHealthModifierFloorsHealth() {
        //Given
        this.withMockedAppService(this.maximumStartingHealth);
        double healthDiff = -101.0;

        //When
        boolean playerAlive = this.healthService.applyHealthModifier(healthDiff);

        //Then
        assertThat(playerAlive, is(false));
        assertThat(this.finalPlayerState.getHealth(),
                is(equalTo(0.0)));
    }

    @Test
    public void testApplyHealthModifierIncreasesHealth() {
        //Given
        this.withMockedAppService(this.maximumStartingHealth/2);
        double healthDiff = 1.0;

        //When
        boolean playerAlive = this.healthService.applyHealthModifier(healthDiff);

        //Then
        assertThat(playerAlive, is(true));
        assertThat(this.finalPlayerState.getHealth(),
                is(equalTo(this.maximumStartingHealth/2 + healthDiff)));
    }

    @Test
    public void testApplyHealthModifierCeilsHealth() {
        //Given
        this.withMockedAppService(this.maximumStartingHealth);
        double healthDiff = 1.0;

        //When
        boolean playerAlive = this.healthService.applyHealthModifier(healthDiff);

        //Then
        assertThat(playerAlive, is(true));
        assertThat(this.finalPlayerState.getHealth(),
                is(equalTo(this.maximumStartingHealth)));
    }

    private void withMockedAppService(double startingHealth) {
        doReturn(this.getStandardPlayerState(startingHealth)).when(this.appService).getPlayerState();
        doAnswer(invocationOnMock -> {
            this.finalPlayerState = invocationOnMock.getArgument(0);
            return null;
        }).when(this.appService).setPlayerState(any(PlayerState.class));
    }

    private PlayerState getStandardPlayerState(double startingHealth) {
        PlayerState playerState;
        try {
            playerState = new PlayerState("Jimmy", WARRIOR, HARD, new int[]{100, 100})
                    .setHealth(startingHealth);
        } catch (PlayerCreationException e) {
            throw new RuntimeException(e);
        }
        return playerState;
    }
}
