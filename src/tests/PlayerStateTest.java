import org.assertj.core.internal.Diff;
import org.example.dto.BasicBow;
import org.example.dto.BasicStaff;
import org.example.dto.BasicSword;
import org.example.dto.PlayerState;
import org.example.dto.Weapon;
import org.example.enums.Archetype;
import org.example.enums.Difficulty;
import org.example.enums.WeaponType;
import org.example.exceptions.PlayerCreationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerStateTest {

    @Test
    public void easyMageTest() throws PlayerCreationException {
        Archetype mage = Archetype.MAGE;
        Difficulty easy = Difficulty.EASY;
        PlayerState player = new PlayerState("Jeff", mage, easy);
        int goldAmount = player.getGoldAmount();
        Weapon weapon = player.getActiveWeapon();

        assertEquals(1000, goldAmount);
        assertEquals(true, weapon instanceof BasicStaff);

    }

    @Test
    public void mediumArcherTest() throws PlayerCreationException {
        Archetype archer = Archetype.ARCHER;
        Difficulty medium = Difficulty.MEDIUM;
        PlayerState player = new PlayerState("Jeff", archer, medium);
        int goldAmount = player.getGoldAmount();
        Weapon weapon = player.getActiveWeapon();
        assertEquals(800, goldAmount);
        assertEquals(true, weapon instanceof BasicBow);
    }

    @Test
    public void hardWarriorTest() throws PlayerCreationException {
        Archetype WARRIOR = Archetype.WARRIOR;
        Difficulty hard = Difficulty.HARD;
        PlayerState player = new PlayerState("Jeff", WARRIOR, hard);
        int goldAmount = player.getGoldAmount();
        Weapon weapon = player.getActiveWeapon();
        assertEquals(500, goldAmount);
        assertEquals(true, weapon instanceof BasicSword);
    }


}
