package org.example.util;

import javafx.scene.media.AudioClip;
import org.example.enums.RoomType;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SFXUtility {

    public static final AudioClip COLLECT_GOLD = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/gold_sack.wav").toUri().toString());

    public static final AudioClip USE_CONSUMABLE = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/spell2.wav").toUri().toString());

    public static final AudioClip CHALLENGE_SOUND = new AudioClip(
            Paths.get(
                    "src/main/resources/static/soundeffects/theircoming3.wav").toUri().toString());

    public static final AudioClip SWORD_1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/sword1.wav").toUri().toString());

    public static final AudioClip SWORD_2 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/sword2.wav").toUri().toString());

    public static final AudioClip SWORD_3 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/sword3.wav").toUri().toString());

    public static final AudioClip SWORD_4 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/sword4.wav").toUri().toString());

    public static final AudioClip STAFF_1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/swish_2.wav").toUri().toString());

    public static final AudioClip STAFF_2 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/swish_3.wav").toUri().toString());

    public static final AudioClip STAFF_3 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/swish_4.wav").toUri().toString());

    public static final AudioClip MAGIC_1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/flaunch.wav").toUri().toString());

    public static final AudioClip MAGIC_2 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/iceball.wav").toUri().toString());

    public static final AudioClip MAGIC_3 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/rlaunch.wav").toUri().toString());

    public static final AudioClip MAGIC_4 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/slimeball.wav").toUri().toString());

    public static final AudioClip OPEN_DIALOG = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/Menu1A.wav").toUri().toString());

    public static final AudioClip CLOSE_DIALOG = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/Menu1B.wav").toUri().toString());

    public static final AudioClip GRASS_1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_1.wav")
                    .toUri().toString());

    public static final AudioClip GRASS_2 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_2.wav")
                    .toUri().toString());

    public static final AudioClip GRASS_3 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_3.wav")
                    .toUri().toString());

    public static final AudioClip GRASS_4 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_4.wav")
                    .toUri().toString());

    public static final AudioClip GRASS_5 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_5.wav")
                    .toUri().toString());

    public static final AudioClip GRASS_6 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_6.wav")
                    .toUri().toString());

    public static final AudioClip GRASS_7 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_7.wav")
                    .toUri().toString());

    public static final AudioClip GRASS_8 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_8.wav")
                    .toUri().toString());

    public static final AudioClip STONE_1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepstone_1.wav")
                    .toUri().toString());

    public static final AudioClip STONE_2 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepstone_2.wav")
                    .toUri().toString());

    public static final AudioClip STONE_3 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepstone_3.wav")
                    .toUri().toString());

    public static final AudioClip STONE_4 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepstone_4.wav")
                    .toUri().toString());

    public static final AudioClip STONE_5 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepstone_5.wav")
                    .toUri().toString());

    public static final AudioClip STONE_6 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepstone_6.wav")
                    .toUri().toString());

    public static final AudioClip STONE_7 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepstone_7.wav")
                    .toUri().toString());

    public static final AudioClip STONE_8 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepstone_8.wav")
                    .toUri().toString());

    public static final AudioClip WATER_1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepwater_1.wav")
                    .toUri().toString());

    public static final AudioClip WATER_2 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepwater_2.wav")
                    .toUri().toString());

    public static final AudioClip WATER_3 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepwater_3.wav")
                    .toUri().toString());

    public static final AudioClip WATER_4 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepwater_4.wav")
                    .toUri().toString());

    public static final AudioClip WATER_5 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepwater_5.wav")
                    .toUri().toString());

    public static final AudioClip WATER_6 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepwater_6.wav")
                    .toUri().toString());

    public static final AudioClip WATER_7 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepwater_7.wav")
                    .toUri().toString());

    public static final AudioClip WATER_8 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepwater_8.wav")
                    .toUri().toString());

    public static final AudioClip EQUIP_1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/equip_1.wav")
                    .toUri().toString());

    public static final AudioClip PURCHASE_1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/purchase_1.wav")
                    .toUri().toString());

    public SFXUtility() {

    }

    public static AudioClip getRandomSwordSound() {
        List<AudioClip> swordSounds = Arrays.asList(SWORD_1, SWORD_2, SWORD_3, SWORD_4);
        return swordSounds.get(new Random().nextInt(swordSounds.size()));
    }

    public static AudioClip getRandomStaffSound() {
        List<AudioClip> staffSounds = Arrays.asList(STAFF_1, STAFF_2, STAFF_3);
        return staffSounds.get(new Random().nextInt(staffSounds.size()));
    }

    public static AudioClip getRandomMagicSound() {
        List<AudioClip> magicSounds = Arrays.asList(MAGIC_1, MAGIC_2, MAGIC_3, MAGIC_4);
        return magicSounds.get(new Random().nextInt(magicSounds.size()));
    }

    public static AudioClip getRandomMovementSound(RoomType roomType) {
        List<AudioClip> movementSounds;
        switch (roomType) {
        case FOREST1:
        case FOREST2:
        case FOREST_TRADER:
            movementSounds = Arrays.asList(
                    GRASS_1, GRASS_2, GRASS_3, GRASS_4, GRASS_5, GRASS_6, GRASS_7, GRASS_8);
            break;
        case CASTLE1:
        case CASTLE2:
        case CASTLE3:
        case BOSS:
        case CASTLE_TRADER:
            movementSounds = Arrays.asList(
                    STONE_1, STONE_2, STONE_3, STONE_4, STONE_5, STONE_6, STONE_7, STONE_8);
            break;
        case GARDEN1:
        case GARDEN2:
        case GARDEN_TRADER:
            movementSounds = Arrays.asList(WATER_1, WATER_2, WATER_3, WATER_4, WATER_5,
                    WATER_6, WATER_7, WATER_8);
            break;
        default:
            movementSounds = null;
        }

        if (movementSounds == null) {
            return null;
        }
        return movementSounds.get(new Random().nextInt(movementSounds.size()));
    }
}
