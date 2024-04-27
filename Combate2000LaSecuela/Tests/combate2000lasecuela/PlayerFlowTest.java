package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.*;
import combate2000lasecuela.managers.*;
import org.junit.jupiter.api.*;
import java.util.*;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerFlowTest {
    @BeforeAll
    static void testSetUpSerialized() {
        Enviroment enviroment = new Enviroment();
        enviroment.setTesting(true);
        Database database = new Database();
        Player playerTester = new Player("pepe", "00", "pepe");
        database.addPlayer(playerTester);
        Operator operatorTester = new Operator("op", "00", "op");
        database.addOperator(operatorTester);
    }
    @Test
    void testPlayerLoginCreateFighter() {
        String data = "1";     // data is the input that the user will give
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        PlayerFlow.setCreatefighter(true);
        assertEquals(Integer.parseInt(data) == 1, PlayerFlow.isCreatefighter());
    }
    @Test
    void testEraseFighter() {
        String data = "2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        PlayerFlow.setErasefighter(true);
        assertEquals(Integer.parseInt(data) == 2, PlayerFlow.isErasefighter());
    }
    @Test
    void testEquipAdmin() {
        String data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        PlayerFlow.setEquipadmin(true);
        assertEquals(Integer.parseInt(data) == 3, PlayerFlow.isEquipadmin());
    }
    @Test
    void testChallengePlayerLogin() {
        String data = "4";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        PlayerFlow.setChallengeplayer(true);
        assertEquals(Integer.parseInt(data) == 4, PlayerFlow.isChallengeplayer());
    }
    @Test
    void testGoldRegister() {
        String data = "5";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        PlayerFlow.setGoldregister(true);
        assertEquals(Integer.parseInt(data) == 5, PlayerFlow.isGoldregister());
    }
    @Test
    void testRanking() {
        String data = "6";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        PlayerFlow.setRanking(true);
        assertEquals(Integer.parseInt(data) == 6, PlayerFlow.isRanking());
    }
    @Test
    void testFighterState() {
        String data = "7";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        PlayerFlow.setFighterstate(true);
        assertEquals(Integer.parseInt(data) == 7, PlayerFlow.isFighterstate());
    }
    @Test
    void testLogin() {
        String data = "8";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        PlayerFlow.setPlayerlogin(false);
        assertEquals(Integer.parseInt(data) == 8, PlayerFlow.isPlayerlogin());
    }
    @Test
    void testErasePlayer() {
        String data = "9";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        PlayerFlow.setEraseplayer(true);
        assertEquals(Integer.parseInt(data) == 9, PlayerFlow.isEraseplayer());
    }
    @Test
    void testChallengeModeIfAccepted() {
        Player player = new Player("pepe", "00", "pepe");
        Player player2 = new Player("juan", "00", "juan");

        Database database = new Database();

        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();

        Fighter fighter1 = new Hunter("fighter1", type, minionList, armorList, weaponList);
        Fighter fighter2 = new Hunter("fighter2", type, minionList, armorList, weaponList);

        player.setFighter(fighter1);
        player2.setFighter(fighter2);
        Combat combat = player.Fight(player2, 10);

        Challenge challenge = new Challenge(player, player2, 10);
        player.getFighter().getPendingChallenges().addChallenge(challenge);

        database.updateGold(fighter1, -10);
        database.updateGold(fighter2, 10);

        // Verificar los resultados esperados
        assertEquals(combat.getWinner(), fighter2);
        assertEquals(combat.getLoser(), fighter1);
        assertEquals(110, combat.getWinner().getGold());
        assertEquals(90, combat.getLoser().getGold());
    }
    @Test
    void testChallengeModeIfRejected() {
        Player player = new Player("pepe", "00", "pepe");
        Player player2 = new Player("juan", "00", "juan");

        Database database = new Database();

        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();

        Fighter fighter1 = new Hunter("fighter1", type, minionList, armorList, weaponList);
        Fighter fighter2 = new Hunter("fighter2", type, minionList, armorList, weaponList);

        player.setFighter(fighter1);
        player2.setFighter(fighter2);

        Challenge challenge = new Challenge(player, player2, 10);
        challenge.getChallenger().rejectingChallenge(10);
        player2.rejectingChallenge(10);

        assertEquals(challenge.getChallenger().getFighter().getGold(), 99);
    }
    @Test
    void testChallengePlayer() {
        Player player = new Player("pepe", "00", "pepe");
        Player player2 = new Player("juan", "00", "juan");
        Database database = new Database();
        database.addPlayer(player);
        database.addPlayer(player2);

        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();

        Fighter fighter1 = new Hunter("fighter1", type,minionList,armorList,weaponList);
        Player challenged = (Player) database.getUser(player2.getName());

        player.setFighter(fighter1);
        database.reducePendingGold(10,player);
        Challenge challenge = player.challengePlayer(challenged,10);
        database.addChallenge(challenge);

        assertEquals(challenge.getChallenger(), player);
        assertEquals(challenge.getChallenged(), challenged);
        assertEquals(challenge.getGold(), 10);
    }
}