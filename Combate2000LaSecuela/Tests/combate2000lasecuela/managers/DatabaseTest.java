package combate2000lasecuela.managers;

import combate2000lasecuela.CosasDeLuchador.*;
import combate2000lasecuela.Operator;
import combate2000lasecuela.Player;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    Database database = new Database();
    Player p1 =  new Player("A","A","A");
    Player p2 =  new Player("B","B","B");
    Player p3 =  new Player("C","C","C");
    Operator o1 = new Operator("1","1","1");
    Operator o2 = new Operator("2","2","2");
    Operator o3 = new Operator("3","3","3");

    @Test
    void testRandomMinion(){
        LinkedList<Minion> resultado = database.randomMinions(0,true,false,0);
        // es imposible buscar igualdades por motivos más que obvios
        //para ver que funciona basta con ver que no hay nulos
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        Object esclavo = resultado.get(0);
        assertTrue(esclavo instanceof Minion);
    }
    @Test
    void testRandomArmor(){
        LinkedList<Armor> resultado = database.randomArmor(0);
        // es imposible buscar igualdades por motivos más que obvios
        //para ver que funciona basta con ver que no hay nulos
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        Object armor = resultado.get(0);
        assertTrue(armor instanceof Armor);
    }
    @Test
    void testRandomWeapon(){
        LinkedList<Weapon> resultado = database.randomWeapons(0);
        // es imposible buscar igualdades por motivos más que obvios
        //para ver que funciona basta con ver que no hay nulos
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        Object weapon = resultado.get(0);
        assertTrue(weapon instanceof Weapon);
    }

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
        database.addPlayer(p1);
        database.addOperator(o1);
        database.addPlayer(p2);
        database.addOperator(o2);
        assertTrue(database.getUser("A").equals(p1));
        assertTrue(database.getUser("1").equals(o1));
        assertTrue(database.getUser("2").equals(o2));
        assertTrue(database.getUser("B").equals(p2));
        assertNull(database.getUser("Holaquetal"));
    }

    @Test
    void testGetRanking() {
    }

    @Test
    void testAddVictories() {
    }

    @Test
    void testGetTFighter() {
    }

    @Test
    void testRandomWeapons() {
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
