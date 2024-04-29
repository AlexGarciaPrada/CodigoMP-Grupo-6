package combate2000lasecuela.CosasDeLuchador;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;

public class LycanthropeTest {
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
        Lycanthrope lycanthrope = new Lycanthrope("mazao", type, minions, armor, weapons);
        Gift gift = new Gift();
        gift.setGiftDamage(10);
        lycanthrope.setRage(3);
        assertEquals(2, lycanthrope.SpecialAttack());
    }

    @Test
    public void testAdjustAbility() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        minions.add(minion);
        armor.add(armadura);
        weapons.add(arma);
        Lycanthrope lycanthrope = new Lycanthrope("mazao", type, minions, armor, weapons);
        lycanthrope.setRage(3);
        // Hacer un ajuste con pA < pD
        lycanthrope.adjustAbility(2, 5);
        assertEquals(3, lycanthrope.getRage());
        // Hacer un ajuste con pA >= pD
        lycanthrope.adjustAbility(5, 2);
        assertEquals(3, lycanthrope.getRage());
    }
}
