package org.example;

import org.example.dto.BasicBow;
import org.example.dto.BasicStaff;
import org.example.dto.BasicSword;
import org.example.dto.PlayerState;
import org.example.dto.Weapon;
import org.example.enums.Archetype;
import org.example.enums.Difficulty;
import org.example.exceptions.PlayerCreationException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerStateTest {

    @Test
    public void testGivenEasyMageProducesValidInit() throws PlayerCreationException {
        Archetype mage = Archetype.MAGE;
        Difficulty easy = Difficulty.EASY;
        PlayerState player = new PlayerState("Jeff", mage, easy);
        int goldAmount = player.getGoldAmount();
        Weapon weapon = player.getActiveWeapon();

        assertThat(goldAmount, is(equalTo(1000)));
        assertThat(weapon, is(instanceOf(BasicStaff.class)));
    }

    @Test
    public void testGivenMedArcherProducesValidInit() throws PlayerCreationException {
        Archetype archer = Archetype.ARCHER;
        Difficulty medium = Difficulty.MEDIUM;
        PlayerState player = new PlayerState("Jeff", archer, medium);
        int goldAmount = player.getGoldAmount();
        Weapon weapon = player.getActiveWeapon();

        assertThat(goldAmount, is(equalTo(800)));
        assertThat(weapon, is(instanceOf(BasicBow.class)));
    }

    @Test
    public void testGivenHrdWarriorProducesValidInit() throws PlayerCreationException {
        Archetype warrior = Archetype.WARRIOR;
        Difficulty hard = Difficulty.HARD;
        PlayerState player = new PlayerState("Jeff", warrior, hard);
        int goldAmount = player.getGoldAmount();
        Weapon weapon = player.getActiveWeapon();

        assertThat(goldAmount, is(equalTo(500)));
        assertThat(weapon, is(instanceOf(BasicSword.class)));
    }

    @Test
    public void testGivenEasyWarrior2ProducesValidInit() throws PlayerCreationException {
        Archetype warrior = Archetype.WARRIOR;
        Difficulty easy = Difficulty.EASY;
        PlayerState player = new PlayerState("Jeff", warrior, easy);
        int goldAmount = player.getGoldAmount();
        Weapon weapon = player.getActiveWeapon();

        assertThat(goldAmount, is(equalTo(1000)));
        assertThat(weapon, is(instanceOf(BasicSword.class)));
    }
}
