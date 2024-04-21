package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class OperatorTest {

    private static Player player;
    private static Operator operator;
    private static LinkedList<Armor> armors;
    private static LinkedList<Weapon> weapons;
    private static LinkedList<Minion> minions;
    private static Fighter fighter;

    @BeforeEach
    public void setUp() { //para crear la situation ya tu sabe
        //crear operador
        operator = new Operator("op", "pass", "op");
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
        player.setFighter(fighter);

    }
    @Test
    public void addMinion() {
        Minion min = new Demon("3; LILITH; DEMONIO; Pacto de Sangre; 2;");
        operator.addMinion(player, min);
        assertEquals(2, player.getFighter().getMyMinions().size());
    }

    @Test
    public void deleteMinion() {
        operator.deleteMinion(player, 1);
        assertEquals(0, player.getFighter().getMyMinions().size());
    }

    @Test
    public void addArmor() {
        Armor arm = new Armor("14; ARMADURA DE HIERRO COMÚN; 2; 0;");
        operator.addArmor(player, arm);
        assertEquals(2, player.getFighter().getMyArmor().size());
    }

    @Test
    public void deleteArmor() {
        operator.deleteArmor(player, 1);
        assertEquals(0, player.getFighter().getMyArmor().size());
    }

    @Test
    public void addWeapon() {
        Weapon weap = new Weapon("14; ESTOQUE; 2; 1;");
        operator.addWeapon(player, weap);
        assertEquals(2, player.getFighter().getMyWeapon().size());
    }

    @Test
    public void deleteWeapon() {
        operator.deleteWeapon(player, 1);
        assertEquals(0, player.getFighter().getMyWeapon().size());
    }


}