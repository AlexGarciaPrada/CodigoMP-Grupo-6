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
        OperatorFlow operatorFlow = new OperatorFlow();
        operatorFlow.setEditfighter(true);
        assertEquals(Integer.parseInt(data)==1,operatorFlow.isEditfighter());
    }

    @Test
    void operatorLoginEditEquipment() {
        String data = "2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow operatorFlow = new OperatorFlow();
        operatorFlow.setEditEquipment(true);
        assertEquals(Integer.parseInt(data)==1,operatorFlow.isEditEquipment());
    }

    @Test
    void operatorLoginVChallenge() {
        String data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow operatorFlow = new OperatorFlow();
        operatorFlow.setVchallenge(true);
        assertEquals(Integer.parseInt(data)==1,operatorFlow.isVchallenge());
    }

    @Test
    void operatorLoginBlock() {
        String data = "4";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow operatorFlow = new OperatorFlow();
        operatorFlow.setBlock(true);
        assertEquals(Integer.parseInt(data)==1,operatorFlow.isBlock());
    }

    @Test
    void operatorLoginUnblock() {
        String data = "5";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow operatorFlow = new OperatorFlow();
        operatorFlow.setUnblock(true);
        assertEquals(Integer.parseInt(data)==1,operatorFlow.isUnblock());
    }

    @Test
    void operatorLogin() {
        String data = "6";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow operatorFlow = new OperatorFlow();
        operatorFlow.setOperatorlogin(true);
        assertEquals(Integer.parseInt(data)==1,operatorFlow.isOperatorlogin());
    }

    @Test
    void operatorLoginEraseOperator() {
        String data = "7";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow operatorFlow = new OperatorFlow();
        operatorFlow.setEraseoperator(true);
        assertEquals(Integer.parseInt(data)==1,operatorFlow.isEraseoperator());
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
