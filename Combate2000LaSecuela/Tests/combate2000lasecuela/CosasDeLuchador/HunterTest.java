package combate2000lasecuela.CosasDeLuchador;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.LinkedList;

public class HunterTest {
    TFighter type = new TFighter("4;DESGRACIADO;0;0;0;");
    Minion minion= new Ghoul("5; NEFARIUS; GHOUL; 3; 1;");
    Armor armadura = new Armor("6; ARMADURA DE COBRE RARA; 1; 1;");
    Weapon arma = new Weapon("6; HACHA ROMA PEQUEÑA; 1; 1;");
    @Test
    public void testSpecialAttack() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        minions.add(minion);
        armor.add(armadura);
        weapons.add(arma);
        Hunter hunter = new Hunter("Test Hunter", type, minions, armor, weapons);
        Talent talent = new Talent();
        talent.setWillDamage(10);
        hunter.setWill(3);
        assertEquals(5, hunter.SpecialAttack());
    }

    @Test
    public void testAdjustAbility() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        minions.add(minion);
        armor.add(armadura);
        weapons.add(arma);
        Hunter hunter = new Hunter("Test Hunter", type, minions, armor, weapons);
        hunter.setWill(3);
        // Hacer un ajuste con pA < pD
        hunter.adjustAbility(2, 5);
        assertEquals(2, hunter.getWill());
        // Hacer un ajuste con pA >= pD
        hunter.adjustAbility(5, 2);
        assertEquals(2, hunter.getWill());
    }
}
