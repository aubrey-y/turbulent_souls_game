package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.example.dto.weapons.BasicStaff;
import org.example.dto.util.Coordinate;
import org.example.dao.PlayerState;
import org.example.dao.Weapon;
import org.example.enums.Archetype;
import org.example.enums.Difficulty;
import org.example.enums.WeaponType;
import org.example.repository.SaveRepository;
import org.example.services.SaveService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.example.util.ResourcePathUtility.BASIC_STAFF_PLAYER_ATTACK_LEFT;
import static org.example.util.ResourcePathUtility.BASIC_STAFF_PLAYER_ATTACK_RIGHT;
import static org.example.util.ResourcePathUtility.BASIC_STAFF_PLAYER_LEFT;
import static org.example.util.ResourcePathUtility.BASIC_STAFF_PLAYER_RIGHT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class SaveServiceTest {

    private SaveRepository saveRepository;

    private SaveService saveService;

    private static final String USER_EMAIL = "karthik@best.ta";

    private Document finalEncodedPlayerStateDocument;

    @BeforeEach
    public void setUp() {
        this.saveRepository = mock(SaveRepository.class);
        this.saveService = new SaveService()
                .setSaveRepository(this.saveRepository)
                .setObjectMapper(new ObjectMapper());
    }

    @AfterEach
    public void tearDown() {
        this.finalEncodedPlayerStateDocument = null;
    }

    @Test
    public void testFindPlayerStatesEncodesPeriods() {
        //Given
        this.withMockedSaveRepository();
        List<PlayerState> expected = this.getDecodedPlayerStates();

        //When
        List<PlayerState> actual = this.saveService.findPlayerStates(USER_EMAIL);

        //Then
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void testUpsertPlayerSaveStateDecodesPeriods() {
        //Given
        this.withMockedSaveRepository();
        PlayerState playerState = this.getDecodedPlayerState("jimmy");
        Document expected = this.getEncodedPlayerState("jimmy");

        //When
        boolean success = this.saveService.upsertPlayerStateSave(playerState);

        //Then
        assertThat(success, is(true));
        assertThat(this.finalEncodedPlayerStateDocument, is(equalTo(expected)));
    }

    private void withMockedSaveRepository() {
        doReturn(this.getEncodedPlayerStateDocuments())
                .when(this.saveRepository).findManyPlayerStateSaves(any());
        doAnswer(invocationOnMock -> {
            this.finalEncodedPlayerStateDocument = invocationOnMock.getArgument(0);
            return true;
        }).when(this.saveRepository).upsertPlayerStateSave(any(Document.class), any(Bson.class));
    }

    private List<PlayerState> getDecodedPlayerStates() {
        return Lists.newArrayList(
                this.getDecodedPlayerState("jimmy"),
                this.getDecodedPlayerState("nathan"));
    }

    private List<Document> getEncodedPlayerStateDocuments() {
        return Lists.newArrayList(
                this.getEncodedPlayerState("jimmy"),
                this.getEncodedPlayerState("nathan"));
    }

    private Document getEncodedPlayerState(String username) {
        Document encodedWeapon = this.getEncodedWeapon();
        return new Document()
                .append("username", username)
                .append("archetype", "MAGE")
                .append("activeWeapon", encodedWeapon)
                .append("weaponInventory", new Document()
                        .append("src/main/resources/static/images/staff<DOT>png", encodedWeapon))
                .append("generalInventory", new Document())
                .append("difficulty", "MEDIUM")
                .append("goldAmount", 800)
                .append("spawnCoordinates", new Document()
                        .append("x", 400)
                        .append("y", 540))
                .append("spawnOrientation", null)
                .append("health", 100.0)
                .append("healthCapacity", 100.0)
                .append("monstersKilled", Collections.emptyList())
                .append("email", USER_EMAIL)
                .append("lastUpdated", "2021/04/08 18:57:15")
                .append("sessionLength", 0)
                .append("goldSpent", 0);
    }

    private PlayerState getDecodedPlayerState(String username) {
        Weapon decodedWeapon = this.getDecodedWeapon();
        return new PlayerState()
                .setUsername(username)
                .setArchetype(Archetype.MAGE)
                .setActiveWeapon(decodedWeapon)
                .setWeaponInventory(
                        Map.of("src/main/resources/static/images/staff.png", decodedWeapon))
                .setGeneralInventory(Collections.emptyMap())
                .setDifficulty(Difficulty.MEDIUM)
                .setGoldAmount(800)
                .setSpawnCoordinates(new Coordinate().setX(400).setY(540))
                .setSpawnOrientation(null)
                .setHealth(100.0)
                .setHealthCapacity(100.0)
                .setMonstersKilled(Collections.emptySet())
                .setEmail(USER_EMAIL)
                .setLastUpdated("2021/04/08 18:57:15")
                .setSessionLength(0)
                .setGoldSpent(0);
    }

    private Weapon getDecodedWeapon() {
        Weapon weapon = (Weapon) new BasicStaff()
                .setName("Basic Staff")
                .setQuantity(1)
                .setImagePath("src/main/resources/static/images/staff.png")
                .setDescription("A handcrafted stick with magical properties...");
        weapon.setType(WeaponType.STAFF).setAttack(7).setRange(100.0);
        return weapon;
    }

    private Document getEncodedWeapon() {
        return new Document()
                .append("@type", "BASICSTAFF")
                .append("name", "Basic Staff")
                .append("quantity", 1)
                .append("imagePath", "src/main/resources/static/images/staff.png")
                .append("description", "A handcrafted stick with magical properties...")
                .append("type", "STAFF")
                .append("attack", 7)
                .append("range", 100.0)
                .append("price", 200)
                .append("animationLeft", BASIC_STAFF_PLAYER_LEFT)
                .append("animationRight", BASIC_STAFF_PLAYER_RIGHT)
                .append("attackAnimationLeft", BASIC_STAFF_PLAYER_ATTACK_LEFT)
                .append("attackAnimationRight", BASIC_STAFF_PLAYER_ATTACK_RIGHT);
    }
}
