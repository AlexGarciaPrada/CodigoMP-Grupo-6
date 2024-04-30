package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private static Player player;
    private static LinkedList<Armor> armors;
    private static LinkedList<Weapon> weapons;
    private static LinkedList<Minion> minions;
    private static Fighter fighter;

    @BeforeEach
    public void setUp() { //para crear la situation ya tu sabe
        //crear player
        player = new Player("pepa", "password123", "pepa");
        //crear armaduras
        armors = new LinkedList<>();
        Armor armor = new Armor("8; ARMADURA DE COBRE ÉPICA; 1; 2;");
        armors.add(armor);
        //crear armas
        weapons = new LinkedList<>();
        Weapon weapon = new Weapon("9; HACHA ROMA GIGANTE; 1; 2;");
        weapons.add(weapon);
        //crear minions
        minions = new LinkedList<>();
        Ghoul ghoultest = new Ghoul("2; MOROK; GHOUL; 4; 2;");
        minions.add(ghoultest);
        //crear fighter
        fighter = new Vampire("dracula", null, minions,armors, weapons);
        //fighter al jugador
        player.setFighter(fighter); //no comprobamos setters siuu

    }

    @Test
    public void playerConstructorTest() {
        Player p = new Player("pepa", "password123", "pepa");
        assertEquals(p.getName(), "pepa");
        assertEquals(p.getPassword(), "password123");
        assertEquals(p.getNick(), "pepa");
    }

    @Test
    public void fightTest() {
        Player challenger = new Player("chall", "password123", "chall");
        challenger.setFighter(fighter);
        Combat result = player.Fight(challenger, 40);
        assertNotNull(result);
    }

    @Test
    public void deleteFighterTest() {
        player.deleteFighter();
        assertNull(player.getFighter());
    }

    @Test
    public void challengePlayerTest() {
        Player challenged = new Player("chall", "password123", "chall");
        Challenge challenge = player.challengePlayer(challenged, 40);
        assertNotNull(challenge);
    }


    @Test
    public void addPendingChallengeTest() {
        Player challenger = new Player("chall", "password123", "chall");
        Challenge challenge = new Challenge(challenger, player, 40);
        player.addPendingChallenge(challenge);
        assertEquals(challenge, player.getFighter().getPendingChallenges().getFirstChallenge());
    }

    @Test
    public void deletePendingChallengesTest() {
        //primero metemos un challenge a la cola
        Player challenger = new Player("chall", "password123", "chall");
        Challenge challenge = new Challenge(challenger, player, 40);
        player.addPendingChallenge(challenge);

        //ahora vemos si lo borra
        player.deletePendingChallenge();
        assertTrue(player.getFighter().getPendingChallenges().isEmpty());
    }

    @Test
    public void hasPendingChallengesTest() {
        Player challenger = new Player("chall", "password123", "chall");
        Challenge challenge = new Challenge(challenger, player, 40);
        player.addPendingChallenge(challenge);
        boolean pc = player.hasPendingChallenges();
        assertTrue(pc);
    }

    @Test
    public void rejectingChallengeTest() {
        int oldGold = player.getFighter().getGold();
        player.rejectingChallenge(40);
        assertTrue(oldGold > player.getFighter().getGold());
    }

}