package combate2000lasecuela.managers;

import combate2000lasecuela.*;
import combate2000lasecuela.CosasDeLuchador.*;
import combate2000lasecuela.Constants;
import combate2000lasecuela.managers.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    Database database = new Database();
    Player p1 =  new Player("A","A","A");
    Player p2 =  new Player("B","B","B");
    Player p3 =  new Player("C","C","C");
    Operator o1 = new Operator("a","a","a");
    Operator o2 = new Operator("b","b","b");
    Operator o3 = new Operator("c","c","c");
    Fighter f1 = new Lycanthrope("f1",database.getLoader().gettFighterManager().getCollection("TFighterMap").get("1"),database.randomMinions(1,true,false,3),database.randomArmor(1),database.randomWeapons(1));
    Fighter f2 = new Vampire("f2",null,null,database.randomArmor(1),database.randomWeapons(1));
    Fighter f3 = new Hunter("f3ç",null,null,database.randomArmor(1),database.randomWeapons(1));
@BeforeEach
    public void setUp(){

    database.addPlayer(p1);
    database.addPlayer(p2);
    database.addPlayer(p3);
    database.addOperator(o1);
    database.addOperator(o2);
    database.addOperator(o3);

    }


    @Test
    void testAddFighter() {
    database.addFighter(p1,f1);
    assertTrue(p1.getFighter().equals(f1));
    assertFalse(p1.getFighter().equals(f2));
    assertNotNull(p1.getFighter());

    }

    @Test
    void testEraseFighter() {
        database.addFighter(p1,f1);
        assertTrue(p1.getFighter().equals(f1));
        database.eraseFighter(p1);
        assertNull(p1.getFighter());
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
        assertTrue(database.isAPlayer("A"));
        assertFalse(database.isAPlayer("Un nombre cualquiera"));
        assertTrue(database.isAPlayer("B"));
        assertFalse(database.isAPlayer("a"));
    }

    @Test
    void testIsAPlayer() {
     assertTrue(database.isAPlayer("A"));
     assertFalse(database.isAPlayer("a"));
     assertFalse(database.isAPlayer("Un nombre cualquiera"));
    }

    @Test
    void testAddOperator() {
        Operator o4 = new Operator("d","d","d");
        database.addOperator(o4);
        assertTrue(database.getUser("d").equals(o4));
        assertFalse(database.getUser("a").equals(o4));
    }

    @Test
    void testErasePlayer() {
        assertTrue(database.getUser("A").equals(p1));
        database.erasePlayer(p1);
        assertNull(database.getUser("A"));
        assertFalse(database.isAPlayer("A"));
        database.addPlayer(p1);
        assertTrue(database.isAPlayer("A"));
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
        assertTrue(database.getUser("A").equals(p1));
        assertTrue(database.getUser("a").equals(o1));
        assertTrue(database.getUser("b").equals(o2));
        assertTrue(database.getUser("B").equals(p2));
        assertNull(database.getUser("Holaquetal"));
    }

    @Test
    void testGetRanking() {
        assertTrue(database.getRanking().size()==3);
        assertTrue(database.getRanking().get(0).endsWith("0"));
        p1.setVictories(10);
        p2.setVictories(9);
        p3.setVictories(8);
        assertTrue(database.getRanking().get(0).endsWith("10"));
        assertTrue(database.getRanking().get(1).endsWith("9"));
        assertTrue(database.getRanking().get(2).endsWith("8"));
        p1.setVictories(0);
        p2.setVictories(0);
        p3.setVictories(0);

    }

    @Test
    void testAddVictories() {
    assertTrue(p1.getVictories()==0);
    database.addVictories(p1);
    assertTrue(p1.getVictories()==1);
        database.addVictories(p1);
        database.addVictories(p1);
        database.addVictories(p1);
    assertTrue(p1.getVictories()==4);

    }

 
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
    void testGetTFighterText() {
    }

    @Test
    void testManagerToListTFighter() {
    }

    @Test
    void testGetChallenge() throws InterruptedException {
        database.addFighter(p1,f1);
        database.addFighter(p2,f2);
        Challenge challenge1 =new Challenge(p1,p2,23);
        Challenge challenge2 = new Challenge(p1,p2,26);
        database.addChallenge(challenge1);
        Thread.sleep(1*1000);
        assertTrue(database.getChallenge().equals(challenge1));
        database.addChallenge(challenge2);
        assertFalse(database.getChallenge().equals(challenge2));
        database.eraseChallenge();
        assertTrue(database.getChallenge().equals(challenge2));
    }

    @Test
    void testEraseChallenge() throws InterruptedException {
        database.addFighter(p1,f1);
        database.addFighter(p2,f2);
        database.addChallenge(new Challenge(p1,p2,23));
        Thread.sleep(1*1000);
        database.addChallenge(new Challenge(p1,p2,26));
        Thread.sleep(1*1000);
        database.addChallenge(new Challenge(p1,p2,25));
        Thread.sleep(1*1000);
        database.addChallenge(new Challenge(p1,p2,24));
        database.eraseChallenge();
        assertTrue(database.getChallengeManager().getCollection("ChallengeMap").size()==3);
        database.eraseChallenge();
        assertTrue(database.getChallengeManager().getCollection("ChallengeMap").size()==2);
        database.eraseChallenge();
        assertTrue(database.getChallengeManager().getCollection("ChallengeMap").size()==1);
        database.eraseChallenge();
        assertTrue(database.getChallengeManager().getCollection("ChallengeMap").size()==0);

    }

    @Test
    void testAddChallenge() throws InterruptedException {
    database.addFighter(p1,f1);
    database.addFighter(p2,f2);
    assertTrue(database.isEmptyChallengeManager());
    database.addChallenge(new Challenge(p1,p2,23));
        Thread.sleep(1*1000);
    assertFalse(database.isEmptyChallengeManager());
        database.addChallenge(new Challenge(p1,p2,26));
        Thread.sleep(1*1000);
        database.addChallenge(new Challenge(p1,p2,25));
        Thread.sleep(1*1000);
        database.addChallenge(new Challenge(p1,p2,24));
        ///Como la clave es la fecha hayq ue dar un segundo entre cada challenge
    assertTrue(database.getChallengeManager().getCollection("ChallengeMap").size()==4);
    }

    @Test
    void testIsEmptyChallengeManager() {
    database.addFighter(p1,f1);
    database.addFighter(p2,f2);
    assertTrue(database.isEmptyChallengeManager());
    database.addChallenge(new Challenge(p1,p2,23));
    assertFalse(database.isEmptyChallengeManager());
    }


    @Test
    void testGetCombatHistory() {
    database.addFighter(p1,f1);
    assertNull(database.getCombatHistory(p1));
    database.addFighter(p2,f2);
    database.addCombat(p1.Fight(p2,0));
    assertNotNull(database.getCombatHistory(p1));
    }



    @Test
    void testChangeFighterName() {
    database.addFighter(p1,f1);
    assertTrue(p1.getFighter().getName().equals("f1"));
    database.changeFighterName(p1,"Alex");
    assertTrue(p1.getFighter().getName().equals("Alex"));

    }

    @Test
    void testChangeFighterRace() {
    database.addFighter(p1,f1);
    assertTrue(p1.getFighter() instanceof Lycanthrope);
    database.changeFighterRace(p1,1);
    assertTrue(p1.getFighter() instanceof Vampire);
    database.changeFighterRace(p1,3);
    assertTrue(p1.getFighter() instanceof Hunter);
    database.changeFighterRace(p1,2);
    assertTrue(p1.getFighter() instanceof Lycanthrope);

    }

    @Test
    void testChangeFighterType() {
    database.addFighter(p1,f1);
    assertTrue(f1.getType().equals(database.getLoader().gettFighterManager().getCollection("TFighterMap").get("1")));
    database.changeFighterType(p1,database.getLoader().gettFighterManager().getCollection("TFighterMap").get("2"));
    assertTrue(f1.getType().equals(database.getLoader().gettFighterManager().getCollection("TFighterMap").get("2")));
    database.changeFighterType(p1,database.getLoader().gettFighterManager().getCollection("TFighterMap").get("3"));
    assertTrue(f1.getType().equals(database.getLoader().gettFighterManager().getCollection("TFighterMap").get("3")));
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
    database.addFighter(p1,f1);
    int prevlength = p1.getFighter().getMyArmor().size();
    database.addArmor(o1,p1,17);
    assertTrue(prevlength+1 == p1.getFighter().getMyArmor().size());
    assertTrue( p1.getFighter().getMyArmor().get(prevlength).equals(database.getLoader().getItemManager().getCollection("ArmorMap").get("17")));
    }

    @Test
    void testDeleteMinion() {
        database.addFighter(p1,f1);
        int prevlength = p1.getFighter().getMyMinions().size();
        database.addMinion(o1,p1,34);
        int length = prevlength+1;
        assertTrue(length == p1.getFighter().getMyMinions().size());
        LinkedList<Minion> allList = p1.getFighter().getAllMinionsList(p1.getFighter().getMyMinions());
        int i = 0;
        for (Minion minion: allList) {
            if (minion.getId().equals("34")) {
                database.deleteMinion(o1,p1,i);
            }
            i++;
        }
        assertTrue(prevlength==p1.getFighter().getMyMinions().size());
        //assertFalse( p1.getFighter().getMyMinions().get(prevlength-1).equals(database.getLoader().getMinionManager().getCollection("MinionMap").get("34")));

    }

    @Test
    void testAddMinion() {
    database.addFighter(p1,f1);
    int prevlength = p1.getFighter().getMyMinions().size();
    database.addMinion(o1,p1,34);
    assertTrue(prevlength+1 == p1.getFighter().getMyMinions().size());
    assertTrue( p1.getFighter().getMyMinions().get(prevlength).equals(database.getLoader().getMinionManager().getCollection("MinionMap").get("34")));
    }

    @Test
    void testGenerateMinionText() {
        assertTrue(database.generateMinionText().length==100);

    }

    @Test
    void testGenerateWeaponText() {
        assertTrue(database.generateWeaponText().length==28);
    }

    @Test
    void testGenerateArmorText() {
    assertTrue(database.generateArmorText().length == 28);
    }

    @Test
    void testEquipWeapon1() {
        Weapon weapon = new Weapon("15; ESTOQUE; 2; 1;");
        database.addFighter(p1,f1);
        assertNotNull(p1.getFighter().getArma1());
        database.equipWeapon1(p1,weapon);
        assertTrue(p1.getFighter().getArma1().equals(weapon));
    }

    @Test
    void testEquipWeapon2() {
    Weapon weapon = new Weapon("15; ESTOQUE; 2; 1;");
    database.addFighter(p1,f1);
    assertNull(p1.getFighter().getArma2());
    database.equipWeapon2(p1,weapon);
    assertTrue(p1.getFighter().getArma2().equals(weapon));
    }

    @Test
    void testEquipArmor() {
    database.addFighter(p1,f1);
    Armor armor = new Armor("10; ARMADURA DE COBRE LEGENDARIA; 1; 3;");
    assertNotNull(p1.getFighter().getArmor());
    database.equipArmor(p1,armor);
    assertTrue(p1.getFighter().getArmor().equals(armor));
    }

    @Test
    void testUpdateGold() {

    }

    @Test
    void testAddMail() {
    String [] email ={"Hola que ase","Hdwajiowda"};
        String [] email2 ={"1"};
        String [] email3 ={"2"};
    database.addFighter(p1,f1);
    assertTrue(p1.getFighter().isMailboxEmpty());
    database.addMail(p1,email);
    assertFalse(p1.getFighter().isMailboxEmpty());
    assertTrue(p1.getFighter().getMail().equals(email));
    database.addMail(p1,email2);
    database.addMail(p1,email3);
    assertTrue(p1.getFighter().getMail().equals(email));
    }

    @Test
    void testAddCombat() {
        Combat combat = new Combat(f1,f2,15,34,true);
        assertTrue(database.getCombatregister().getCollection("CombatMap").isEmpty());
        database.addCombat(combat);
        assertFalse(database.getCombatregister().getCollection("CombatMap").isEmpty());
        assertTrue(database.getCombatregister().getCollection("CombatMap").containsValue(combat));
    }

    @Test
    void testEraseMail() {
        String [] email ={"Hola que ase","Hdwajiowda"};
        String [] email2 ={"1"};
        String [] email3 ={"2"};
        database.addFighter(p1,f1);
        assertTrue(p1.getFighter().isMailboxEmpty());
        database.addMail(p1,email);
        assertFalse(p1.getFighter().isMailboxEmpty());
        database.eraseMail(p1);
        assertTrue(p1.getFighter().isMailboxEmpty());
        database.addMail(p1,email2);
        database.addMail(p1,email3);
        database.eraseMail(p1);
        assertTrue(p1.getFighter().getMail().equals(email2));

    }

    @Test
    void testReducePendingGold() {
        database.addFighter(p1,f1);
        assertTrue(p1.getFighter().getPendingGold()==100);
        database.reducePendingGold(20,p1);
        assertFalse(p1.getFighter().getPendingGold()==100);
        assertTrue(p1.getFighter().getPendingGold()==80);
    }

    @Test
    void testSetDemonMax() {
    }

    @Test
    void testGetDemonMax() {
    }

}
