package combate2000lasecuela.managers;

import combate2000lasecuela.Player;
import combate2000lasecuela.managers.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DatabaseTest {

    @Test
    void isNickUsedTest() {
        Database database = new Database();
        assertFalse(database.isNickUsed("juah"));
    }
}
