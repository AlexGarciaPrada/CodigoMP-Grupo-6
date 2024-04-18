package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.*;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private static Player player;
    private static LinkedList<Armor> armors;
    private static LinkedList<Weapon> weapons;
    private static LinkedList<Minion> minions;
    private static Fighter fighter;

    @Before
    public void setUp() { //para crear la situation ya tu sabe
        //crear player
        player = new Player("pepa", "pass", "pepa");
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
    public void fight() {
        Player challenger = new Player("chall", "pass", "chall");
        challenger.setFighter(fighter);
        Combat result = player.Fight(challenger, 40);
        assertNotNull(result);
    }

    @Test
    public void deleteFighter() {
        player.deleteFighter();
        assertNull(player.getFighter());
    }


}