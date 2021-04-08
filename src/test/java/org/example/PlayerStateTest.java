package org.example;

import org.example.dto.BasicMagic;
import org.example.dto.BasicStaff;
import org.example.dto.BasicSword;
import org.example.dto.PlayerState;
import org.example.dto.Weapon;
import org.example.enums.Archetype;
import org.example.enums.Difficulty;
import org.example.exceptions.PlayerCreationException;
import org.junit.jupiter.api.Test;

import static org.example.controllers.SecondaryController.SPAWN_COORDINATES;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerStateTest {

    private final int[] spawnCoordinates = new int[]{400, 540};

    @Test
    public void testGivenEasyMageProducesValidInit() throws PlayerCreationException {
        Archetype mage = Archetype.MAGE;
        Difficulty easy = Difficulty.EASY;
        PlayerState player = new PlayerState("Jeff", mage, easy, SPAWN_COORDINATES);
        int goldAmount = player.getGoldAmount();
        Weapon weapon = player.getActiveWeapon();

        assertThat(goldAmount, is(equalTo(1000)));
        assertThat(weapon, is(instanceOf(BasicStaff.class)));
    }

    @Test
    public void testGivenMedWizardProducesValidInit() throws PlayerCreationException {
        Archetype wizard = Archetype.WIZARD;
        Difficulty medium = Difficulty.MEDIUM;
        PlayerState player = new PlayerState("Jeff", wizard, medium, SPAWN_COORDINATES);
        int goldAmount = player.getGoldAmount();
        Weapon weapon = player.getActiveWeapon();

        assertThat(goldAmount, is(equalTo(800)));
        assertThat(weapon, is(instanceOf(BasicMagic.class)));
    }

    @Test
    public void testGivenHrdWarriorProducesValidInit() throws PlayerCreationException {
        Archetype warrior = Archetype.WARRIOR;
        Difficulty hard = Difficulty.HARD;
        PlayerState player = new PlayerState("Jeff", warrior, hard, SPAWN_COORDINATES);
        int goldAmount = player.getGoldAmount();
        Weapon weapon = player.getActiveWeapon();

        assertThat(goldAmount, is(equalTo(500)));
        assertThat(weapon, is(instanceOf(BasicSword.class)));
    }

    @Test
    public void testGivenEasyWarriorProducesValidInit() throws PlayerCreationException {
        Archetype warrior = Archetype.WARRIOR;
        Difficulty easy = Difficulty.EASY;
        PlayerState player = new PlayerState("Jeff", warrior, easy, SPAWN_COORDINATES);
        int goldAmount = player.getGoldAmount();
        Weapon weapon = player.getActiveWeapon();

        assertThat(goldAmount, is(equalTo(1000)));
        assertThat(weapon, is(instanceOf(BasicSword.class)));
    }
}
