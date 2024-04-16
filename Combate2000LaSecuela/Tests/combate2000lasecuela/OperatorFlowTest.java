package combate2000lasecuela;

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
    void blockUser() {
        OperatorFlow operatorFlow = new OperatorFlow();
        Database database = new Database();
        Operator operator = new Operator("op","00","op");
        Player auxPlayer = (Player) database.getUser("pepe");
        operator.blockPlayer(auxPlayer);
        assertTrue(auxPlayer.isBlocked());
    }

    @Test
    void unblockUser() {
    }


}
