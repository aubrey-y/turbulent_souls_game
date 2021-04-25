package org.example;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import org.example.dao.Monster;
import org.example.dao.Weapon;
import org.example.dto.weapons.BasicSword;
import org.example.services.MonsterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.HashMap;
import java.util.Map;

import static org.example.enums.Direction.LEFT;
import static org.example.enums.MonsterType.WHITE_DRAGON;
import static org.example.services.MonsterService.TILE_SIZE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Disabled
@ExtendWith(ApplicationExtension.class)
public class MonsterServiceTest {

    private Map<String, Monster> monsterMapping;

    private MonsterService monsterService;

    private final int playerX = 500;
    private final int playerY = 500;

    @BeforeEach
    public void setUp() {
        this.monsterMapping = new HashMap<>();
        this.monsterService = new MonsterService().setMonsterMapping(this.monsterMapping);
    }

    @Test
    public void testAttackNearestMonsterGivenNoMonstersReturnsNull() {
        //Given
        Weapon weapon = this.getBasicSword();
        boolean devMode = false;

        //When
        String actual = this.monsterService
                .attackNearestMonster(weapon, this.playerX, this.playerY, devMode);

        //Then
        assertThat(actual, is(equalTo(null)));
    }

    @Test
    public void testAttackNearestMonsterGivenMonsterInRangeReturnsMonsterKey() {
        //Given
        Weapon weapon = this.getBasicSword();
        boolean devMode = false;
        Monster monster = this.getMonsterAtDistance(0, weapon.getAttack(), 0);
        this.monsterMapping.put(monster.getKey(), monster);

        //When
        String actual = this.monsterService
                .attackNearestMonster(weapon, this.playerX, this.playerY, devMode);

        //Then
        assertThat(actual, is(equalTo(monster.getKey())));
    }

    @Test
    public void testAttackNearestMonsterGivenMonsterEqRangeReturnsMonsterKey() {
        //Given
        Weapon weapon = this.getBasicSword();
        boolean devMode = false;
        Monster monster = this.getMonsterAtDistance(
                (int) (weapon.getRange() * TILE_SIZE), weapon.getAttack(), 0);
        this.monsterMapping.put(monster.getKey(), monster);

        //When
        String actual = this.monsterService
                .attackNearestMonster(weapon, this.playerX, this.playerY, devMode);

        //Then
        assertThat(actual, is(equalTo(monster.getKey())));
    }

    @Test
    public void testAttackNearestMonsterGivenMonsterOutOfRangeReturnsNull() {
        //Given
        Weapon weapon = this.getBasicSword();
        boolean devMode = false;
        Monster monster = this.getMonsterAtDistance(
                (int) (weapon.getRange() * TILE_SIZE) + 1, weapon.getAttack(), 0);
        this.monsterMapping.put(monster.getKey(), monster);

        //When
        String actual = this.monsterService
                .attackNearestMonster(weapon, this.playerX, this.playerY, devMode);

        //Then
        assertThat(actual, is(equalTo(null)));
    }

    @Test
    public void testAttackNearestMonsterGivenTwoMonstersInRangeReturnsClosestMonster() {
        //Given
        Weapon weapon = this.getBasicSword();
        boolean devMode = false;
        Monster closerMonster = this.getMonsterAtDistance(
                (int) (weapon.getRange() * TILE_SIZE) - 1, weapon.getAttack(), 0);
        Monster furtherMonster = this.getMonsterAtDistance(
                (int) (weapon.getRange() * TILE_SIZE), weapon.getAttack(), 0);
        this.monsterMapping.put(closerMonster.getKey(), closerMonster);
        this.monsterMapping.put(furtherMonster.getKey(), furtherMonster);

        //When
        String actual = this.monsterService
                .attackNearestMonster(weapon, this.playerX, this.playerY, devMode);

        //Then
        assertThat(actual, is(equalTo(closerMonster.getKey())));
    }

    @Test
    public void testAttackNearestMonsterGivenDevModeAndMinorHealthIncreaseReturnsMonsterKey() {
        //Given
        Weapon weapon = this.getBasicSword();
        boolean devMode = true;
        Monster monster = this.getMonsterAtDistance(
                (int) (weapon.getRange() * TILE_SIZE), weapon.getAttack() + 1, 0);
        this.monsterMapping.put(monster.getKey(), monster);

        //When
        String actual = this.monsterService
                .attackNearestMonster(weapon, this.playerX, this.playerY, devMode);

        //Then
        assertThat(actual, is(equalTo(monster.getKey())));
    }

    @Test
    public void testAttackNearestMonsterGivenDevModeAndMajorHealthIncreaseReturnsMonsterKey() {
        //Given
        Weapon weapon = this.getBasicSword();
        boolean devMode = true;
        Monster monster = this.getMonsterAtDistance(
                (int) (weapon.getRange() * TILE_SIZE), weapon.getAttack() * 100, 0);
        this.monsterMapping.put(monster.getKey(), monster);

        //When
        String actual = this.monsterService
                .attackNearestMonster(weapon, this.playerX, this.playerY, devMode);

        //Then
        assertThat(actual, is(equalTo(monster.getKey())));
    }

    @Test
    public void testAttackNearestMonsterGivenDevModeAndUnrealisticHealthIncreaseReturnsNull() {
        //Given
        Weapon weapon = this.getBasicSword();
        boolean devMode = true;
        Monster monster = this.getMonsterAtDistance(
                (int) (weapon.getRange() * TILE_SIZE), weapon.getAttack() * 100 + 1, 0);
        this.monsterMapping.put(monster.getKey(), monster);

        //When
        String actual = this.monsterService
                .attackNearestMonster(weapon, this.playerX, this.playerY, devMode);

        //Then
        assertThat(actual, is(equalTo(null)));
    }

    @Test
    public void testPlayerIsInRangeOfMonsterGivenInRangeReturnsTrue() {
        //Given
        Monster monster = this.getMonsterAtDistance(
                0, this.getBasicSword().getAttack(), (int) this.getBasicSword().getRange());
        this.monsterMapping.put(monster.getKey(), monster);

        //When
        boolean actual = this.monsterService
                .playerIsInRangeOfMonster(monster.getKey(), this.playerX, this.playerY);

        //Then
        assertThat(actual, is(true));
    }

    @Test
    public void testPlayerIsInRangeOfMonsterGivenEqRangeReturnsTrue() {
        //Given
        Monster monster = this.getMonsterAtDistance((int) this.getBasicSword().getRange(),
                this.getBasicSword().getAttack(), (int) this.getBasicSword().getRange());
        this.monsterMapping.put(monster.getKey(), monster);

        //When
        boolean actual = this.monsterService
                .playerIsInRangeOfMonster(monster.getKey(), this.playerX, this.playerY);

        //Then
        assertThat(actual, is(true));
    }

    @Test
    public void testPlayerIsInRangeOfMonsterGivenOutOfRangeReturnsFalse() {
        //Given
        Monster monster = this.getMonsterAtDistance(
                (int) this.getBasicSword().getRange(), this.getBasicSword().getAttack(), 0);
        this.monsterMapping.put(monster.getKey(), monster);

        //When
        boolean actual = this.monsterService
                .playerIsInRangeOfMonster(monster.getKey(), this.playerX, this.playerY);

        //Then
        assertThat(actual, is(false));
    }

    private Weapon getBasicSword() {
        return new BasicSword();
    }

    private Monster getMonsterAtDistance(int distance, int health, int range) {
        return new Monster()
                .setHealth(health)
                .setHealthCapacity(health)
                .setRange(range)
                .setAttack(0)
                .setAccuracy(0)
                .setKillReward(0)
                .setMonsterType(WHITE_DRAGON)
                .setImageView(this.getImageView(this.playerX + distance, this.playerY))
                .setHealthBar(new ProgressBar())
                .setHealthText(new Label())
                .setOrientation(LEFT)
                .setDeathAnimationLeft(null)
                .setDeathAnimationRight(null)
                .setKey("monster");
    }

    private ImageView getImageView(int x, int y) {
        ImageView imageView = new ImageView();
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        return imageView;
    }
}
