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

    // OPERATORLOGIN TESTS

    @Test
    void operatorLoginEditFighter() {
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setEditfighter(true);
        assertEquals(Integer.parseInt(data)==1,OperatorFlow.isEditfighter());
    }

    @Test
    void operatorLoginEditEquipment() {
        String data = "2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setEditEquipment(true);
        assertEquals(Integer.parseInt(data)==1,OperatorFlow.isEditEquipment());
    }

    @Test
    void operatorLoginVChallenge() {
        String data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setVchallenge(true);
        assertEquals(Integer.parseInt(data)==1,OperatorFlow.isVchallenge());
    }

    @Test
    void operatorLoginBlock() {
        String data = "4";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setBlock(true);
        assertEquals(Integer.parseInt(data)==1, OperatorFlow.isBlock());
    }

    @Test
    void operatorLoginUnblock() {
        String data = "5";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setUnblock(true);
        assertEquals(Integer.parseInt(data)==1,OperatorFlow.isUnblock());
    }

    @Test
    void operatorLogin() {
        String data = "6";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setOperatorlogin(true);
        assertEquals(Integer.parseInt(data)==1,OperatorFlow.isOperatorlogin());
    }

    @Test
    void operatorLoginEraseOperator() {
        String data = "7";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setEraseoperator(true);
        assertEquals(Integer.parseInt(data)==1,OperatorFlow.isEraseoperator());
    }

    @Test
    void blockUser() {
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
