package org.example;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.example.dao.Item;
import org.example.services.AppService;
import org.example.services.GoldService;
import org.example.services.TraderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

@Disabled
@ExtendWith(ApplicationExtension.class)
public class TraderServiceTest {

    private AppService appService;

    private GoldService goldService;

    private Map<String, Item> traderInventory;

    private VBox traderVBox;

    private Label traderPreviewTitle;

    private Label traderPreviewStat;

    private Label traderPreviewDescription;

    private Rectangle traderPreviewBackground;

    private ImageView traderPreviewImage;

    private ImageView traderBackground;

    private TraderService traderService;

    @BeforeEach
    public void setUp() {
        this.appService = mock(AppService.class);
        this.goldService = mock(GoldService.class);
        this.traderInventory = new HashMap<>();
        this.traderVBox = new VBox();
        this.traderPreviewTitle = new Label();
        this.traderPreviewStat = new Label();
        this.traderPreviewDescription = new Label();
        this.traderPreviewBackground = new Rectangle();
        this.traderPreviewImage = new ImageView();
        this.traderBackground = new ImageView();
        this.traderService = new TraderService()
                .setAppService(this.appService)
                .setGoldService(this.goldService)
                .setTraderInventory(this.traderInventory)
                .setTraderVBox(this.traderVBox)
                .setTraderPreviewTitle(this.traderPreviewTitle)
                .setTraderPreviewStat(this.traderPreviewStat)
                .setTraderPreviewDescription(this.traderPreviewDescription)
                .setTraderPreviewBackground(this.traderPreviewBackground)
                .setTraderPreviewImage(this.traderPreviewImage)
                .setTraderBackground(this.traderBackground);
    }

    @Test
    public void testToggleTraderOpenGivenTraderClosedExposesTraderPreviewTitle() {
        //When
        this.traderService.toggleTraderOpen();

        //Then
        assertThat(this.traderPreviewTitle.isVisible(), is(true));
    }

    @Test
    public void testToggleTraderOpenGivenTraderClosedExposesTraderPreviewStat() {
        //When
        this.traderService.toggleTraderOpen();

        //Then
        assertThat(this.traderPreviewStat.isVisible(), is(true));
    }

    @Test
    public void testToggleTraderOpenGivenTraderClosedExposesTraderPreviewDescription() {
        //When
        this.traderService.toggleTraderOpen();

        //Then
        assertThat(this.traderPreviewDescription.isVisible(), is(true));
    }

    @Test
    public void testToggleTraderOpenGivenTraderClosedExposesTraderPreviewBackground() {
        //When
        this.traderService.toggleTraderOpen();

        //Then
        assertThat(this.traderPreviewBackground.isVisible(), is(true));
    }

    @Test
    public void testToggleTraderOpenGivenTraderClosedExposesTraderPreviewImage() {
        //When
        this.traderService.toggleTraderOpen();

        //Then
        assertThat(this.traderPreviewImage.isVisible(), is(true));
    }

    @Test
    public void testToggleTraderOpenGivenTraderClosedExposesTraderBackground() {
        //When
        this.traderService.toggleTraderOpen();

        //Then
        assertThat(this.traderBackground.isVisible(), is(true));
    }

    @Test
    public void testToggleTraderOpenGivenTraderOpenHidesTraderPreviewTitle() {
        //Given
        this.traderService.setTraderOpen(true);
        this.traderPreviewTitle.setVisible(true);

        //When
        this.traderService.toggleTraderOpen();

        //Then
        assertThat(this.traderPreviewTitle.isVisible(), is(false));
    }

    @Test
    public void testToggleTraderOpenGivenTraderOpenHidesTraderPreviewStat() {
        //Given
        this.traderService.setTraderOpen(true);
        this.traderPreviewStat.setVisible(true);

        //When
        this.traderService.toggleTraderOpen();

        //Then
        assertThat(this.traderPreviewStat.isVisible(), is(false));
    }

    @Test
    public void testToggleTraderOpenGivenTraderOpenHidesTraderPreviewDescription() {
        //Given
        this.traderService.setTraderOpen(true);
        this.traderPreviewDescription.setVisible(true);

        //When
        this.traderService.toggleTraderOpen();

        //Then
        assertThat(this.traderPreviewDescription.isVisible(), is(false));
    }

    @Test
    public void testToggleTraderOpenGivenTraderOpenHidesTraderPreviewBackground() {
        //Given
        this.traderService.setTraderOpen(true);
        this.traderPreviewBackground.setVisible(true);

        //When
        this.traderService.toggleTraderOpen();

        //Then
        assertThat(this.traderPreviewBackground.isVisible(), is(false));
    }

    @Test
    public void testToggleTraderOpenGivenTraderOpenHidesTraderPreviewImage() {
        //Given
        this.traderService.setTraderOpen(true);
        this.traderPreviewImage.setVisible(true);

        //When
        this.traderService.toggleTraderOpen();

        //Then
        assertThat(this.traderPreviewImage.isVisible(), is(false));
    }

    @Test
    public void testToggleTraderOpenGivenTraderOpenHidesTraderBackground() {
        //Given
        this.traderService.setTraderOpen(true);
        this.traderBackground.setVisible(true);

        //When
        this.traderService.toggleTraderOpen();

        //Then
        assertThat(this.traderBackground.isVisible(), is(false));
    }
}
