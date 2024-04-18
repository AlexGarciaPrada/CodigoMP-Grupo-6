package combate2000lasecuela.managers;

import combate2000lasecuela.CosasDeLuchador.Fighter;
import combate2000lasecuela.CosasDeLuchador.Lycanthrope;
import combate2000lasecuela.Player;
import combate2000lasecuela.managers.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {


    @Test
    void testAddFighter() {
        Database database = new Database();
        database.getUsermanager().loadElement("Prueba"); //Esto está hecho para que cargue un archivo que no existe
        Player player = new Player("1","1","1");
        Fighter fighter = new Lycanthrope("prueba",null,null,database.randomArmor(1),database.randomWeapons(1));
        database.addFighter(player,fighter);
        assertTrue(player.getFighter().equals(fighter));
    }

    @Test
    void testEraseFighter() {
        Database database = new Database();
        database.getUsermanager().loadElement("Prueba"); //Esto está hecho para que cargue un archivo que no existe
        Player player = new Player("1","1","1");
        Fighter fighter = new Lycanthrope("prueba",null,null,database.randomArmor(1),database.randomWeapons(1));
        database.addFighter(player,fighter);
        database.eraseFighter(player);
        assertNull(player.getFighter());
    }

    @Test
    void updateUsers() {
        /*Database database = new Database();
        UserManager userManager = new UserManager();
        Player player = new Player("1","1","1");
        Fighter fighter = new Lycanthrope("prueba",null,null,database.randomArmor(1),database.randomWeapons(1));
        database.addPlayer(player);*/


    }

    @Test
    void updateChallenges() {
    }

    @Test
    void updateCombats() {
    }

    @Test
    void addPendingChallenge() {
    }

    @Test
    void testAddPlayer() {
        Database database = new Database();
        database.getUsermanager().loadElement("Prueba");
        Player player = new Player("1","1","1");
        database.addPlayer(player);
        assertTrue(database.isAPlayer("1"));
    }

    @Test
    void isAPlayer() {
    }

    @Test
    void addOperator() {
    }

    @Test
    void erasePlayer() {
    }

    @Test
    void eraseOperator() {
    }

    @Test
    void isNickUsed() {
    }

    @Test
    void isPasswordCorrect() {
    }

    @Test
    void getUser() {
    }

    @Test
    void getRanking() {
    }

    @Test
    void addVictories() {
    }

    @Test
    void randomMinions() {
    }

    @Test
    void randomMinionDemon() {
    }

    @Test
    void getTFighter() {
    }

    @Test
    void randomWeapons() {
    }

    @Test
    void randomArmor() {
    }

    @Test
    void getTFighterText() {
    }

    @Test
    void managerToListTFighter() {
    }

    @Test
    void getStrengths() {
    }

    @Test
    void getWeaknesses() {
    }

    @Test
    void getChallenge() {
    }

    @Test
    void eraseChallenge() {
    }

    @Test
    void addChallenge() {
    }

    @Test
    void isEmptyChallengeManager() {
    }

    @Test
    void generateCombatHistoryText() {
    }

    @Test
    void combatHistoryModifyer() {
    }

    @Test
    void getCombatHistory() {
    }

    @Test
    void isCombatRegisterEmpty() {
    }

    @Test
    void changeFighterName() {
    }

    @Test
    void changeFighterRace() {
    }

    @Test
    void changeFighterType() {
    }

    @Test
    void deleteArmor() {
    }

    @Test
    void deleteWeapon() {
    }

    @Test
    void addWeapon() {
    }

    @Test
    void addArmor() {
    }

    @Test
    void deleteMinion() {
    }

    @Test
    void addMinion() {
    }

    @Test
    void addMinionText() {
    }

    @Test
    void generateMinionText() {
    }

    @Test
    void generateWeaponText() {
    }

    @Test
    void generateArmorText() {
    }

    @Test
    void equipWeapon1() {
    }

    @Test
    void equipWeapon2() {
    }

    @Test
    void equipArmor() {
    }

    @Test
    void updateGold() {
    }

    @Test
    void addMail() {
    }

    @Test
    void addCombat() {
    }

    @Test
    void eraseMail() {
    }

    @Test
    void reducePendingGold() {
    }

    @Test
    void setDemonMax() {
    }

    @Test
    void getDemonMax() {
    }

}
