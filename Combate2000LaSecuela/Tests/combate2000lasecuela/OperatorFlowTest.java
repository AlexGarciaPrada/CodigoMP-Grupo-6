package combate2000lasecuela;

import combate2000lasecuela.OperatorFlow;
import combate2000lasecuela.Player;
import combate2000lasecuela.managers.Database;
import combate2000lasecuela.managers.UserManager;
import combate2000lasecuela.screen.MessageManager;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
public class OperatorFlowTest {

    @BeforeAll
    static void setUpSerialized() {
        Database database = new Database();
        Player playerTester = new Player("pepe","00","pepe");
        database.addPlayer(playerTester);
        Operator operatorTester = new Operator("op","00","op");
        database.addOperator(operatorTester);
    }

    @Test
    void operatorMachine(){
    }

    @Test
    void operatorLoginEditFighter() {
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow operatorFlow = new OperatorFlow();
        //MessageManager messageManager = new MessageManager();
        //operatorFlow.set
        assertEquals(Integer.parseInt(data)==1, operatorFlow.isEditfighter());
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
