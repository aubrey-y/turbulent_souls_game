
import javafx.stage.Stage;
import org.example.App;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;

public class SecondaryControllerTestFX extends ApplicationTest {
    @Override
    public void start(Stage stage) throws IOException {
        App app = new App();
        app.start(stage);
    }

    @Test
    public void testPlay() {
        clickOn("Start");
        verifyThat("Your story begins...", NodeMatchers.isNotNull());
    }


}