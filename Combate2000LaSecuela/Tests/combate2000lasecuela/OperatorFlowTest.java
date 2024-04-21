package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.*;
import combate2000lasecuela.OperatorFlow;
import combate2000lasecuela.Player;
import combate2000lasecuela.managers.*;
import org.junit.jupiter.api.*;
import java.util.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
public class OperatorFlowTest {

    @BeforeAll
    static void testSetUpSerialized() {
        Enviroment enviroment = new Enviroment();
        enviroment.setTesting(true);
        Database database = new Database();
        Player playerTester = new Player("pepe","00","pepe");
        database.addPlayer(playerTester);
        Operator operatorTester = new Operator("op","00","op");
        database.addOperator(operatorTester);
    }


    @Test
    void testOperatorMachine(){
    }

    // OPERATORLOGIN TESTS

    @Test
    void testOperatorLoginEditFighter() {
        String data = "1";     // data is the input that the user will give
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setEditfighter(true);
        assertEquals(Integer.parseInt(data)==1,OperatorFlow.isEditfighter()); // 1 is the option to edit fighter
    }

    @Test
    void testOperatorLoginEditEquipment() {
        String data = "2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setEditEquipment(true);
        assertEquals(Integer.parseInt(data)==2,OperatorFlow.isEditEquipment());
    }

    @Test
    void testOperatorLoginVChallenge() {
        String data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setVchallenge(true);
        assertEquals(Integer.parseInt(data)==3,OperatorFlow.isVchallenge());
    }

    @Test
    void testOperatorLoginBlock() {
        String data = "4";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setBlock(true);
        assertEquals(Integer.parseInt(data)==4, OperatorFlow.isBlock());
    }

    @Test
    void testOperatorLoginUnblock() {
        String data = "5";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setUnblock(true);
        assertEquals(Integer.parseInt(data)==5,OperatorFlow.isUnblock());
    }

    @Test
    void testOperatorLogin() {
        String data = "6";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setOperatorlogin(true);
        assertEquals(Integer.parseInt(data)==6,OperatorFlow.isOperatorlogin());
    }

    @Test
    void testOperatorLoginEraseOperator() {
        String data = "7";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        OperatorFlow.setEraseoperator(true);
        assertEquals(Integer.parseInt(data)==7,OperatorFlow.isEraseoperator());
    }

    @Test
    void testBlockUser() {
        Database database = new Database();
        Operator operator = new Operator("op","00","op");
        Player auxPlayer = (Player) database.getUser("pepe");
        operator.blockPlayer(auxPlayer);
        assertTrue(auxPlayer.isBlocked());
    }

    @Test
    void testUnblockUser() {
        Database database = new Database();
        Operator operator = new Operator("op","00","op");
        Player auxPlayer = (Player) database.getUser("pepe");
        operator.unblockPlayer(auxPlayer);
        assertFalse(auxPlayer.isBlocked());
    }

    @Test
    void testEraseOperator() {
        Database database = new Database();
        Operator operator = new Operator("op","00","op");
        database.eraseOperator(operator);
        assertNull(database.getUser("op"));
    }


    @Test
    void testValidateChallenge() {
        Database database = new Database();
        Operator operator = new Operator("op","00","op");
        Player auxPlayerChallenged = (Player) database.getUser("juan");
        Challenge challenge = new Challenge((Player) database.getUser("pepe"),auxPlayerChallenged,100);
        database.getChallenge();
        database.getChallenge().getChallenged().addPendingChallenge(challenge);
        database.eraseChallenge();
        ChallengeManager challengeManager = new ChallengeManager();
        //assertEquals(challengeManager.
    }

    @Test
    void testNotValidateChallenge() {
        Database database = new Database();
        Operator operator = new Operator("op","00","op");
        Player auxPlayer = (Player) database.getUser("pepe");

    }

    @Test
    public void testEditWeaponDelete() {
        Database database = new Database();
        Operator operator = new Operator("op","00","op");
        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        Minion minion= new Ghoul("5; NEFARIUS; GHOUL; 3; 1;");
        Armor armadura = new Armor("6; ARMADURA DE COBRE RARA; 1; 1;");
        Weapon arma = new Weapon("6; HACHA ROMA PEQUEÑA; 1; 1;");
        minions.add(minion);
        armor.add(armadura);
        weapons.add(arma);
        Weapon weapon1 = new Weapon("1; HACHA ROMA PEQUEÑA; 1; 1;");
        weapons.add(weapon1);

        Player auxPlayer = (Player) database.getUser("pepe");
        Fighter fighter1 = new Hunter("prueba", type,minions,armor,weapons);
        database.addFighter(auxPlayer,fighter1);


        assertTrue(database.deleteWeapon(operator,auxPlayer,1));
    }

}
