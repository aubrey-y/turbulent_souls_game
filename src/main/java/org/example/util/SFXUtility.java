package org.example.util;

import javafx.scene.media.AudioClip;
import org.example.enums.RoomType;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SFXUtility {

    public static final AudioClip collectGold = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/gold_sack.wav").toUri().toString());

    public static final AudioClip useConsumable = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/spell2.wav").toUri().toString());

    public static final AudioClip challengeSound = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/theircoming3.wav").toUri().toString());

    public static final AudioClip sword1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/sword1.wav").toUri().toString());

    public static final AudioClip sword2 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/sword2.wav").toUri().toString());

    public static final AudioClip sword3 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/sword3.wav").toUri().toString());

    public static final AudioClip sword4 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/sword4.wav").toUri().toString());

    public static final AudioClip staff1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/swish_2.wav").toUri().toString());

    public static final AudioClip staff2 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/swish_3.wav").toUri().toString());

    public static final AudioClip staff3 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/swish_4.wav").toUri().toString());

    public static final AudioClip magic1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/flaunch.wav").toUri().toString());

    public static final AudioClip magic2 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/iceball.wav").toUri().toString());

    public static final AudioClip magic3 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/rlaunch.wav").toUri().toString());

    public static final AudioClip magic4 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/slimeball.wav").toUri().toString());

    public static final AudioClip openInventory = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/Menu1A.wav").toUri().toString());

    public static final AudioClip closeInventory = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/Menu1B.wav").toUri().toString());

    public static final AudioClip grass1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_1.wav")
                    .toUri().toString());

    public static final AudioClip grass2 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_2.wav")
                    .toUri().toString());

    public static final AudioClip grass3 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_3.wav")
                    .toUri().toString());

    public static final AudioClip grass4 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_4.wav")
                    .toUri().toString());

    public static final AudioClip grass5 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_5.wav")
                    .toUri().toString());

    public static final AudioClip grass6 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_6.wav")
                    .toUri().toString());

    public static final AudioClip grass7 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_7.wav")
                    .toUri().toString());

    public static final AudioClip grass8 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_8.wav")
                    .toUri().toString());

    public static final AudioClip stone1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_1.wav")
                    .toUri().toString());

    public static final AudioClip stone2 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_2.wav")
                    .toUri().toString());

    public static final AudioClip stone3 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_3.wav")
                    .toUri().toString());

    public static final AudioClip stone4 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_4.wav")
                    .toUri().toString());

    public static final AudioClip stone5 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_5.wav")
                    .toUri().toString());

    public static final AudioClip stone6 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_6.wav")
                    .toUri().toString());

    public static final AudioClip stone7 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_7.wav")
                    .toUri().toString());

    public static final AudioClip stone8 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepdirt_8.wav")
                    .toUri().toString());

    public static final AudioClip water1 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepwater_1.wav")
                    .toUri().toString());

    public static final AudioClip water2 = new AudioClip(
            Paths.get("src/main/resources/static/soundeffects/movement/stepwater_2.wav")
                    .toUri().toString());

    public SFXUtility() {

    }

    public static AudioClip getRandomSwordSound() {
        List<AudioClip> swordSounds = Arrays.asList(sword1, sword2, sword3, sword4);
        return swordSounds.get(new Random().nextInt(swordSounds.size()));
    }

    public static AudioClip getRandomStaffSound() {
        List<AudioClip> staffSounds = Arrays.asList(staff1, staff2, staff3);
        return staffSounds.get(new Random().nextInt(staffSounds.size()));
    }

    public static AudioClip getRandomMagicSound() {
        List<AudioClip> magicSounds = Arrays.asList(magic1, magic2, magic3, magic4);
        return magicSounds.get(new Random().nextInt(magicSounds.size()));
    }

    public static AudioClip getRandomMovementSound(RoomType roomType) {
        List<AudioClip> movementSounds;
        switch (roomType) {
            case FOREST1:
            case FOREST2:
            case FOREST_TRADER:
                movementSounds = Arrays.asList(
                    grass1, grass2, grass3, grass4, grass5, grass6, grass7, grass8);
                break;
            case CASTLE1:
            case CASTLE2:
            case CASTLE3:
            case BOSS:
            case CASTLE_TRADER:
                movementSounds = Arrays.asList(
                        stone1, stone2, stone3, stone4, stone5, stone6, stone7, stone8);
                break;
            case GARDEN1:
            case GARDEN2:
            case GARDEN_TRADER:
                movementSounds = Arrays.asList(water1, water2);
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
