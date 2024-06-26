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
    Player p1 =  new Player("A","passwordA","A");
    Player p2 =  new Player("B","passwordB","B");
    Player p3 =  new Player("C","passwordC","C");
    Operator o1 = new Operator("a","passworda","a");
    Operator o2 = new Operator("b","passwordb","b");
    Operator o3 = new Operator("c","passwordc","c");
    Fighter f1 = new Lycanthrope("f1",database.getLoader().gettFighterManager().getCollection("TFighterMap").get("1"),database.randomMinions(1,true,false,3),database.randomArmor(1),database.randomWeapons(1));
    Fighter f2 = new Vampire("f2",database.getLoader().gettFighterManager().getCollection("TFighterMap").get("2"),database.randomMinions(1,true,true,3),database.randomArmor(1),database.randomWeapons(1));



    @BeforeEach
    public void setUp(){
        Enviroment enviroment = new Enviroment();
        enviroment.setTesting(true);
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
    database.addFighter(p1,f2);
    assertFalse(p1.getFighter().equals(f2));
    database.eraseFighter(p1);
    database.addFighter(p1,f2);
    assertTrue(p1.getFighter().equals(f2));

    }

    @Test
    void testEraseFighter() {
        database.addFighter(p1,f1);
        assertTrue(p1.getFighter().equals(f1));
        database.eraseFighter(p1);
        assertNull(p1.getFighter());
        database.eraseFighter(p2);
        assertNull(p2.getFighter());
    }


    @Test
    void testDeletePendingChallenge() {
        Challenge challenge = new Challenge(p1,p2,34);
        Challenge challenge2 = new Challenge(p1,p2,35);
        Challenge challenge3= new Challenge(p1,p2,36);
        database.addFighter(p1,f1);
        database.deletePendingChallenge(p1);
        assertFalse(p1.hasPendingChallenges());
        p1.addPendingChallenge(challenge);
        assertTrue(p1.hasPendingChallenges());
        database.deletePendingChallenge(p1);
        assertFalse(p1.hasPendingChallenges());
        p1.addPendingChallenge(challenge2);
        p1.addPendingChallenge(challenge3);
        database.deletePendingChallenge(p1);
        assertTrue(p1.hasPendingChallenges());
        assertTrue(p1.getFighter().getPendingChallenges().getFirstChallenge().equals(challenge3));



    }
    @Test
    void testAddPlayer() {
        assertTrue(database.isAPlayer("A"));
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
        Operator o4 = new Operator("d","password123","d");
        database.addOperator(o4);
        assertTrue(database.getUser("d").equals(o4));
        assertFalse(database.getUser("a").equals(o4));
    }

    @Test
    void testErasePlayer() {
        assertTrue(database.getUser("A").equals(p1));
        database.erasePlayer(p1);
        database.erasePlayer(new Player("algo random","algo random","algo random"));
        assertNull(database.getUser("A"));
        assertFalse(database.isAPlayer("A"));
        database.addPlayer(p1);
        assertTrue(database.isAPlayer("A"));
    }

    @Test
    void testEraseOperator() {
        database.eraseOperator(o1);
        assertNull(database.getUsermanager().getCollection("Operator").get(o1.getNick()));
        database.addOperator(o1);
        database.eraseOperator(new Operator("algo random","algo random","algo random"));

    }
    @Test
    void testIsNickUsed() {
        assertTrue((database.isNickUsed("A"))&& (database.isNickUsed("a")));
        assertFalse(database.isNickUsed("algo random"));
    }

    @Test
    void testIsPasswordCorrect() {
        assertTrue((database.isPasswordCorrect("A","passwordA"))&&(database.isPasswordCorrect("a","passworda")));
        assertFalse(database.isPasswordCorrect("A","passwordb"));

    }

    @Test
    void testGetUser() {
        assertTrue(database.getUser("A").equals(p1));
        assertTrue(database.getUser("a").equals(o1));
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
    String [] text = database.getTFighterText();
    assertTrue(text.length==12);
    assertTrue(text[5].equals("5. DESGRACIADO Esbirros: +0 Armaduras: +0 Armas: +0"));

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
        database.eraseChallenge();
        database.getChallenge();
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
        database.eraseChallenge();

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
    assertTrue(database.getCombatHistory(p1).length==1);
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
        //crear armaduras
        LinkedList<Armor> armors = new LinkedList<>();
        Armor armor = new Armor("8; ARMADURA DE COBRE ÉPICA; 1; 2;");
        armors.add(armor);
        //crear armas
        LinkedList<Weapon> weapons = new LinkedList<>();
        Weapon weapon = new Weapon("9; HACHA ROMA GIGANTE; 1; 2;");
        weapons.add(weapon);
        //crear minions
        LinkedList<Minion> minions = new LinkedList<>();
        Ghoul ghoultest = new Ghoul("2; MOROK; GHOUL; 4; 2;");
        minions.add(ghoultest);
        //crear fighter
        Fighter fighter = new Vampire("dracula", null, minions,armors, weapons);
        database.addFighter(p1,fighter);
        int prevlength = p1.getFighter().getMyArmor().size();
        database.addArmor(o1,p1,12);
        int length = prevlength+1;
        assertTrue(length == p1.getFighter().getMyArmor().size());
        database.deleteArmor(o1,p1,1);
        assertTrue(prevlength==p1.getFighter().getMyArmor().size());

    }

    @Test
    void testDeleteWeapon() {
        //crear armaduras
        LinkedList<Armor> armors = new LinkedList<>();
        Armor armor = new Armor("8; ARMADURA DE COBRE ÉPICA; 1; 2;");
        armors.add(armor);
        //crear armas
        LinkedList<Weapon> weapons = new LinkedList<>();
        Weapon weapon = new Weapon("9; HACHA ROMA GIGANTE; 1; 2;");
        weapons.add(weapon);
        //crear minions
        LinkedList<Minion> minions = new LinkedList<>();
        Ghoul ghoultest = new Ghoul("2; MOROK; GHOUL; 4; 2;");
        minions.add(ghoultest);
        //crear fighter
        Fighter fighter = new Vampire("dracula", null, minions,armors, weapons);
        database.addFighter(p1,fighter);
        int prevlength = p1.getFighter().getMyWeapon().size();
        database.addWeapon(o1,p1,12);
        int length = prevlength+1;
        assertTrue(length == p1.getFighter().getMyWeapon().size());
        database.deleteWeapon(o1,p1,1);
        assertTrue(prevlength==p1.getFighter().getMyWeapon().size());
    }

    @Test
    void testAddWeapon() {
        //crear armaduras
        LinkedList<Armor> armors = new LinkedList<>();
        Armor armor = new Armor("8; ARMADURA DE COBRE ÉPICA; 1; 2;");
        armors.add(armor);
        //crear armas
        LinkedList<Weapon> weapons = new LinkedList<>();
        Weapon weapon = new Weapon("9; HACHA ROMA GIGANTE; 1; 2;");
        weapons.add(weapon);
        //crear minions
        LinkedList<Minion> minions = new LinkedList<>();
        Ghoul ghoultest = new Ghoul("2; MOROK; GHOUL; 4; 2;");
        minions.add(ghoultest);
        //crear fighter
        Fighter fighter = new Vampire("dracula", null, minions,armors, weapons);
        database.addFighter(p1,fighter);
        int prevlength = p1.getFighter().getMyWeapon().size();
        database.addWeapon(o1,p1,28);
        assertTrue(prevlength+1 == p1.getFighter().getMyWeapon().size());
        assertTrue( p1.getFighter().getMyWeapon().get(prevlength).equals(database.getLoader().getItemManager().getCollection("WeaponMap").get("28")));

    }

    @Test
    void testAddArmor() {
        //crear armaduras
        LinkedList<Armor> armors = new LinkedList<>();
        Armor armor = new Armor("8; ARMADURA DE COBRE ÉPICA; 1; 2;");
        armors.add(armor);
        //crear armas
        LinkedList<Weapon> weapons = new LinkedList<>();
        Weapon weapon = new Weapon("9; HACHA ROMA GIGANTE; 1; 2;");
        weapons.add(weapon);
        //crear minions
        LinkedList<Minion> minions = new LinkedList<>();
        Ghoul ghoultest = new Ghoul("2; MOROK; GHOUL; 4; 2;");
        minions.add(ghoultest);
        //crear fighter
        Fighter fighter = new Vampire("dracula", null, minions,armors, weapons);
        database.addFighter(p1,fighter);
        int prevlength = p1.getFighter().getMyArmor().size();
        database.addArmor(o1,p1,28);
        assertTrue(prevlength+1 == p1.getFighter().getMyArmor().size());
        assertTrue( p1.getFighter().getMyArmor().get(prevlength).equals(database.getLoader().getItemManager().getCollection("ArmorMap").get("28")));
    }

    @Test
    void testDeleteMinion() {
        //crear armaduras
        LinkedList<Armor> armors = new LinkedList<>();
        Armor armor = new Armor("8; ARMADURA DE COBRE ÉPICA; 1; 2;");
        armors.add(armor);
        //crear armas
        LinkedList<Weapon> weapons = new LinkedList<>();
        Weapon weapon = new Weapon("9; HACHA ROMA GIGANTE; 1; 2;");
        weapons.add(weapon);
        //crear minions
        LinkedList<Minion> minions = new LinkedList<>();
        Ghoul ghoultest = new Ghoul("2; MOROK; GHOUL; 4; 2;");
        minions.add(ghoultest);
        //crear fighter
        Fighter fighter = new Vampire("dracula", null, minions,armors, weapons);
        database.addFighter(p1,fighter);
        int prevlength = p1.getFighter().getMyMinions().size();
        database.addMinion(o1,p1,34);
        int length = prevlength+1;
        assertTrue(length == p1.getFighter().getMyMinions().size());
        database.deleteMinion(o1,p1,1);
        assertTrue(prevlength==p1.getFighter().getMyMinions().size());
    }

    @Test
    void testAddMinion() {
        //crear armaduras
        LinkedList<Armor> armors = new LinkedList<>();
        Armor armor = new Armor("8; ARMADURA DE COBRE ÉPICA; 1; 2;");
        armors.add(armor);
        //crear armas
        LinkedList<Weapon> weapons = new LinkedList<>();
        Weapon weapon = new Weapon("9; HACHA ROMA GIGANTE; 1; 2;");
        weapons.add(weapon);
        //crear minions
        LinkedList<Minion> minions = new LinkedList<>();
        Ghoul ghoultest = new Ghoul("2; MOROK; GHOUL; 4; 2;");
        minions.add(ghoultest);
        //crear fighter
        Fighter fighter = new Vampire("dracula", null, minions,armors, weapons);
        database.addFighter(p1,fighter);
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
       database.updateGold(f1,20);
       assertTrue(f1.getGold()==120);
       database.updateGold(f1,-20);
        assertTrue(f1.getGold()==100);

    }

    @Test
    void testAddMail() {
    String [] email ={"Hola que ase","Hdwajiowda"};
        String [] email2 ={"1"};
    database.addFighter(p1,f1);
    assertTrue(p1.getFighter().isMailboxEmpty());
    database.addMail(p1,email);
    assertFalse(p1.getFighter().isMailboxEmpty());
    assertTrue(p1.getFighter().getMail().equals(email));
    database.addMail(p1,email2);
    assertTrue(p1.getFighter().getMailbox().get(1).equals(email2));
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
        assertTrue(p1.getFighter().getMail().equals(email3));
        database.eraseMail(p1);
        assertTrue(p1.getFighter().isMailboxEmpty());
        database.eraseMail(p1);
    }

    @Test
    void testReducePendingGold() {
        database.addFighter(p1,f1);
        assertTrue(p1.getFighter().getPendingGold()==100);
        database.reducePendingGold(20,p1);
        assertFalse(p1.getFighter().getPendingGold()==100);
        assertTrue(p1.getFighter().getPendingGold()==80);
        database.reducePendingGold(1000,p1);
        assertFalse(p1.getFighter().getPendingGold()>=0);
    }


}
