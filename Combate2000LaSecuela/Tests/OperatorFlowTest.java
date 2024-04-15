import combate2000lasecuela.OperatorFlow;
import combate2000lasecuela.Player;
import combate2000lasecuela.managers.Database;
import combate2000lasecuela.managers.UserManager;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
public class OperatorFlowTest {

    @BeforeAll
    static void setUpSerialized() {
        Database database = new Database();
        Player playerTester = new Player("pepe","00","pepe");
        database.addPlayer(playerTester);

    }



    @Test
    void operatorMachine(){

    }
    @Test
    void operatorLogin() {

    }
    @Test
    void blockUserNotFound() {
        Database database = new Database();
        Player player = new Player("pepe","00","pepe");

        assertFalse(database.isNickUsed("pepe"));
    }
    @Test
    void unblockUser() {
    }


}
