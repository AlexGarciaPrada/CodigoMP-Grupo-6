package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.*;
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
        Player playerTester = new Player("pepe","password123","pepe");
        database.addPlayer(playerTester);
        Operator operatorTester = new Operator("op","password123","op");
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
        Operator operator = new Operator("op","password123","op");
        Player auxPlayer = (Player) database.getUser("pepe");
        operator.blockPlayer(auxPlayer);
        assertTrue(auxPlayer.isBlocked());
    }

    @Test
    void testUnblockUser() {
        Database database = new Database();
        Operator operator = new Operator("op","password123","op");
        Player auxPlayer = (Player) database.getUser("pepe");
        operator.unblockPlayer(auxPlayer);
        assertFalse(auxPlayer.isBlocked());
    }

    @Test
    void testEraseOperator() {
        Database database = new Database();
        Operator operator = new Operator("op","password123","op");
        database.eraseOperator(operator);
        assertNull(database.getUser("op"));
    }

    @Test
    void testValidateChallenge() {
        Database database = new Database();
        Operator operator = new Operator("op","password123","op");
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
        Operator operator = new Operator("op","password123","op");
        Player auxPlayer = (Player) database.getUser("pepe");

    }

    @Test
    public void testEditWeaponDelete() {
        Database database = new Database();
        Operator operator = new Operator("op","password123","op");
        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();
        Minion minion= new Ghoul("5; NEFARIUS; GHOUL; 3; 1;");
        Armor armor = new Armor("6; ARMADURA DE COBRE RARA; 1; 1;");
        Weapon weapon = new Weapon("6; HACHA ROMA PEQUEÑA; 1; 1;");

        minionList.add(minion);
        armorList.add(armor);
        weaponList.add(weapon);

        Player auxPlayer = (Player) database.getUser("pepe");
        Fighter fighter1 = new Hunter("prueba", type,minionList,armorList,weaponList);
        database.addFighter(auxPlayer,fighter1);

        assertTrue(database.deleteWeapon(operator,auxPlayer,1));
    }

    @Test
    public void testEditWeaponAdd() {
        Database database = new Database();
        Operator operator = new Operator("op","password123","op");
        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();
        Minion minion= new Ghoul("5; NEFARIUS; GHOUL; 3; 1;");
        Armor armor = new Armor("6; ARMADURA DE COBRE RARA; 1; 1;");
        Weapon weapon = new Weapon("6; HACHA ROMA PEQUEÑA; 1; 1;");

        minionList.add(minion);
        armorList.add(armor);
        weaponList.add(weapon);

        Player auxPlayer = (Player) database.getUser("pepe");
        Fighter fighter1 = new Hunter("prueba", type,minionList,armorList,weaponList);
        database.addFighter(auxPlayer,fighter1);
        operator.addWeapon(auxPlayer,weapon);

        assertTrue(database.addWeapon(operator,auxPlayer,1));
    }

    @Test
    void testEditArmorDelete() {
        Database database = new Database();
        Operator operator = new Operator("op","password123","op");
        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();
        Minion minion= new Ghoul("5; NEFARIUS; GHOUL; 3; 1;");
        Armor armor = new Armor("6; ARMADURA DE COBRE RARA; 1; 1;");
        Weapon weapon = new Weapon("6; HACHA ROMA PEQUEÑA; 1; 1;");

        minionList.add(minion);
        armorList.add(armor);
        weaponList.add(weapon);

        Player auxPlayer = (Player) database.getUser("pepe");
        Fighter fighter1 = new Hunter("prueba", type,minionList,armorList,weaponList);
        database.addFighter(auxPlayer,fighter1);

        assertTrue(database.deleteArmor(operator,auxPlayer,1));
    }

    @Test
    void testEditArmorAdd() {
        Database database = new Database();
        Operator operator = new Operator("op","password123","op");
        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();
        Minion minion= new Ghoul("5; NEFARIUS; GHOUL; 3; 1;");
        Armor armor = new Armor("6; ARMADURA DE COBRE RARA; 1; 1;");
        Weapon weapon = new Weapon("6; HACHA ROMA PEQUEÑA; 1; 1;");

        minionList.add(minion);
        armorList.add(armor);
        weaponList.add(weapon);

        Player auxPlayer = (Player) database.getUser("pepe");
        Fighter fighter1 = new Hunter("prueba", type,minionList,armorList,weaponList);
        database.addFighter(auxPlayer,fighter1);
        operator.addArmor(auxPlayer,armor);

        assertTrue(database.addArmor(operator,auxPlayer,1));
    }

    @Test
    void testEditMinionDelete() {
        Database database = new Database();
        Operator operator = new Operator("op","password123","op");
        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();
        Minion minion= new Ghoul("5; NEFARIUS; GHOUL; 3; 1;");
        Armor armor = new Armor("6; ARMADURA DE COBRE RARA; 1; 1;");
        Weapon weapon = new Weapon("6; HACHA ROMA PEQUEÑA; 1; 1;");

        minionList.add(minion);
        armorList.add(armor);
        weaponList.add(weapon);

        Player auxPlayer = (Player) database.getUser("pepe");
        Fighter fighter1 = new Hunter("prueba", type,minionList,armorList,weaponList);
        database.addFighter(auxPlayer,fighter1);

        assertTrue(database.deleteMinion(operator,auxPlayer,1));
    }

    @Test
    void testEditMinionAdd() {
        Database database = new Database();
        Operator operator = new Operator("op","password123","op");
        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();
        Minion minion= new Ghoul("5; NEFARIUS; GHOUL; 3; 1;");
        Armor armor = new Armor("6; ARMADURA DE COBRE RARA; 1; 1;");
        Weapon weapon = new Weapon("6; HACHA ROMA PEQUEÑA; 1; 1;");

        minionList.add(minion);
        armorList.add(armor);
        weaponList.add(weapon);

        Player auxPlayer = (Player) database.getUser("pepe");
        Fighter fighter1 = new Hunter("prueba", type,minionList,armorList,weaponList);
        database.addFighter(auxPlayer,fighter1);
        operator.addMinion(auxPlayer,minion);

        assertTrue(database.addMinion(operator,auxPlayer,1));
    }

    @Test
    void testEditFighterName() {
        Database database = new Database();
        Operator operator = new Operator("op","password123","op");
        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();
        Minion minion= new Ghoul("5; NEFARIUS; GHOUL; 3; 1;");
        Armor armor = new Armor("6; ARMADURA DE COBRE RARA; 1; 1;");
        Weapon weapon = new Weapon("6; HACHA ROMA PEQUEÑA; 1; 1;");

        minionList.add(minion);
        armorList.add(armor);
        weaponList.add(weapon);

        Player auxPlayer = (Player) database.getUser("pepe");
        Fighter fighter1 = new Hunter("prueba", type,minionList,armorList,weaponList);
        database.addFighter(auxPlayer,fighter1);

        database.changeFighterName(auxPlayer,"nombreCambiado");

        assertEquals(fighter1.getName(),"nombreCambiado");
    }

    @Test
    void testEditFighterRace() {
        Database database = new Database();
        Operator operator = new Operator("op","password123","op");
        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();
        Minion minion= new Ghoul("5; NEFARIUS; GHOUL; 3; 1;");
        Armor armor = new Armor("6; ARMADURA DE COBRE RARA; 1; 1;");
        Weapon weapon = new Weapon("6; HACHA ROMA PEQUEÑA; 1; 1;");

        minionList.add(minion);
        armorList.add(armor);
        weaponList.add(weapon);

        Player auxPlayer = (Player) database.getUser("pepe");
        Fighter fighter1 = new Hunter("prueba", type,minionList,armorList,weaponList);
        database.addFighter(auxPlayer,fighter1);

        database.changeFighterRace(auxPlayer,1);

        assertTrue(fighter1 instanceof Hunter);
    }  //TODO

    @Test
    void testEditFighterType() {
        Database database = new Database();
        Operator operator = new Operator("op","password123","op");
        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();
        Minion minion= new Ghoul("5; NEFARIUS; GHOUL; 3; 1;");
        Armor armor = new Armor("6; ARMADURA DE COBRE RARA; 1; 1;");
        Weapon weapon = new Weapon("6; HACHA ROMA PEQUEÑA; 1; 1;");

        minionList.add(minion);
        armorList.add(armor);
        weaponList.add(weapon);

        Player auxPlayer = (Player) database.getUser("pepe");
        Fighter fighter1 = new Hunter("prueba", type,minionList,armorList,weaponList);
        database.addFighter(auxPlayer,fighter1);
        TFighter typeTest = new TFighter("9;PREDEFINIDO;1;1;1");

        database.changeFighterType(auxPlayer,typeTest);

        assertEquals(fighter1.getType(), typeTest);

    }
}
