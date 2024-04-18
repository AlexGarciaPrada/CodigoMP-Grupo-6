package combate2000lasecuela.managers;

import combate2000lasecuela.Constants;
import combate2000lasecuela.CosasDeLuchador.Fighter;
import combate2000lasecuela.CosasDeLuchador.Lycanthrope;
import combate2000lasecuela.Operator;
import combate2000lasecuela.Constants;
import combate2000lasecuela.Player;
import combate2000lasecuela.managers.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    Database database = new Database();
    @Test
    void testAddFighter() {
        database.getUsermanager().loadElement("Prueba"); //Esto está hecho para que cargue un archivo que no existe
        Player player = new Player("1","1","1");
        Fighter fighter = new Lycanthrope("prueba",null,null,database.randomArmor(1),database.randomWeapons(1));
        database.addFighter(player,fighter);
        assertTrue(player.getFighter().equals(fighter));
    }

    @Test
    void testEraseFighter() {
        Player player = new Player("1","1","1");
        Fighter fighter = new Lycanthrope("prueba",null,null,database.randomArmor(1),database.randomWeapons(1));
        database.addFighter(player,fighter);
        database.eraseFighter(player);
        assertNull(player.getFighter());
    }

    @Test
    void testUpdateUsers() {

    }

    @Test
    void testUpdateChallenges() {

    }

    @Test
    void testUpdateCombats() {
    }

    @Test
    void testAddPendingChallenge() {
    }

    @Test
    void testAddPlayer() {
        Database database = new Database();
        Player player = new Player("1","1","1");
        database.addPlayer(player);
        assertTrue(database.isAPlayer("1"));
    }

    @Test
    void testIsAPlayer() {
        Database database = new Database();
        Player player = new Player("2","2","2");
        database.addPlayer(player);
        Operator operator = new Operator("da","bifsebfes","diuediefes");
        assertTrue((database.isAPlayer(player.getNick()) &&(!(database.isAPlayer(operator.getNick())))));
    }

    @Test
    void testAddOperator() {
        Operator operator = new Operator("da","bifsebfes","diuediefes");
        database.addOperator(operator);
        assertTrue(database.getUser(operator.getNick()) instanceof Operator);
    }

    @Test
    void testErasePlayer() {
        Player player = new Player("2","2","2");
        database.addPlayer(player);
        String nick = player.getNick();
        database.erasePlayer(player);
        assertFalse(database.isAPlayer(nick));
    }

    @Test
    void testEraseOperator() {
        Operator operator = new Operator("da","bifsebfes","diuediefes");
        database.addOperator(operator);
        String nick = operator.getNick();
        database.eraseOperator(operator);
        assertNull(database.getUser(nick));
    }
    @Test
    void testIsNickUsed() {
        database.addPlayer(new Player("3","3","3"));
        database.addOperator(new Operator("4","4","4"));
        assertTrue((database.isNickUsed("3"))&& (database.isNickUsed("4")));
    }

    @Test
    void testIsPasswordCorrect() {
        database.addPlayer(new Player("3","3","3"));
        database.addOperator(new Operator("4","4","4"));
        assertTrue((!(database.isPasswordCorrect("3","5")))&&(database.isPasswordCorrect("4","4")));

    }

    @Test
    void testGetUser() {
    }

    @Test
    void testGetRanking() {
    }

    @Test
    void testAddVictories() {
    }

    @Test
    void testRandomMinions() {
        
    }

    @Test
    void testRandomMinionDemon() {
    }

    @Test
    void testGetTFighter() {
    }

    @Test
    void testRandomWeapons() {
    }

    @Test
    void testRandomArmor() {
    }

    @Test
    void testGetTFighterText() {
    }

    @Test
    void testManagerToListTFighter() {
    }

    @Test
    void testGetStrengths() {
    }

    @Test
    void testGetWeaknesses() {
    }

    @Test
    void testGetChallenge() {
    }

    @Test
    void testEraseChallenge() {
    }

    @Test
    void testAddChallenge() {
    }

    @Test
    void testIsEmptyChallengeManager() {
    }

    @Test
    void testGenerateCombatHistoryText() {
    }

    @Test
    void testCombatHistoryModifyer() {
    }

    @Test
    void testGetCombatHistory() {
    }

    @Test
    void testIsCombatRegisterEmpty() {
    }

    @Test
    void testChangeFighterName() {
    }

    @Test
    void testChangeFighterRace() {
    }

    @Test
    void testChangeFighterType() {
    }

    @Test
    void testDeleteArmor() {
    }

    @Test
    void testDeleteWeapon() {
    }

    @Test
    void testAddWeapon() {
    }

    @Test
    void testAddArmor() {
    }

    @Test
    void testDeleteMinion() {
    }

    @Test
    void testAddMinion() {
    }

    @Test
    void testAddMinionText() {
    }

    @Test
    void testGenerateMinionText() {
    }

    @Test
    void testGenerateWeaponText() {
    }

    @Test
    void testGenerateArmorText() {
    }

    @Test
    void testEquipWeapon1() {
    }

    @Test
    void testEquipWeapon2() {
    }

    @Test
    void testEquipArmor() {
    }

    @Test
    void testUpdateGold() {
    }

    @Test
    void testAddMail() {
    }

    @Test
    void testAddCombat() {
    }

    @Test
    void testEraseMail() {
    }

    @Test
    void testReducePendingGold() {
    }

    @Test
    void testSetDemonMax() {
    }

    @Test
    void testGetDemonMax() {
    }

}
