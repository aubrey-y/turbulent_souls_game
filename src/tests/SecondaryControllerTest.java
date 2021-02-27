
import org.example.controllers.SecondaryController;
import org.example.exceptions.InvalidArchetypeException;
import org.example.exceptions.InvalidDifficultyException;
import org.example.exceptions.InvalidNameException;
import org.junit.Test;

public class SecondaryControllerTest {

    @Test (expected = InvalidNameException.class)
    public void testTextField() throws InvalidNameException {
        SecondaryController app = new SecondaryController();
        app.setNameID("");
        app.validatePlayerTest();
    }

    @Test (expected = InvalidDifficultyException.class)
    public void testDifficulty() throws InvalidDifficultyException {
        SecondaryController app = new SecondaryController();
        app.validateDifficultyTest();
    }

    @Test (expected = InvalidArchetypeException.class)
    public void testArchetype() throws InvalidArchetypeException {
        SecondaryController app = new SecondaryController();
        app.validateArchetypeTest();
    }


}
