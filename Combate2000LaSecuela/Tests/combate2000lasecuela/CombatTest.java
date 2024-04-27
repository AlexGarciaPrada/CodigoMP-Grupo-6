package combate2000lasecuela;

import combate2000lasecuela.*;
import combate2000lasecuela.CosasDeLuchador.*;
import combate2000lasecuela.managers.Database;
import combate2000lasecuela.managers.Enviroment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CombatTest {
    @BeforeAll
    static void setUp() {
    Enviroment enviroment = new Enviroment();
    enviroment.setTesting(true);
    }

    @Test
    void CombatConstructor(){
        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();

        Fighter fighter1 = new Hunter("fighter1", type,minionList,armorList,weaponList);
        Fighter fighter2 = new Hunter("fighter2", type,minionList,armorList,weaponList );

        Combat combat = new Combat(fighter1, fighter2,10,100, true);

        assertEquals(combat.getWinner(), fighter2);
        assertEquals(combat.getLoser(), fighter1);
        assertEquals(combat.getGoldGained(), 100);
        assertEquals(combat.getRounds(), 10);

    }
    @Test
    void resultIfWinner() {
        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();

        Fighter fighter1 = new Hunter("fighter1", type,minionList,armorList,weaponList);
        Fighter fighter2 = new Hunter("fighter2", type,minionList,armorList,weaponList );
        Combat combat = new Combat(fighter1, fighter2,10,100, true);

        assertEquals(combat.result()[0], "Ha ganado fighter2");
    }
    @Test
    void resultIfLoser() {
        TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
        LinkedList<Minion> minionList = new LinkedList<>();
        LinkedList<Weapon> weaponList = new LinkedList<>();
        LinkedList<Armor> armorList = new LinkedList<>();

        Fighter fighter1 = new Hunter("fighter1", type,minionList,armorList,weaponList);
        Fighter fighter2 = new Hunter("fighter2", type,minionList,armorList,weaponList );
        Combat combat = new Combat(fighter1, fighter2,10,100, false);

        assertEquals(combat.result()[0], "Ha ganado fighter1");
    }
}