package combate2000lasecuela.CosasDeLuchador;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.LinkedList;

public class VampireTest {
    TFighter type = new TFighter("4;DESGRACIADO;0;0;0;");
    Minion minion= new Ghoul("5; NEFARIUS; GHOUL; 3; 1;");
    Armor armadura = new Armor("6; ARMADURA DE COBRE RARA; 1; 1;");
    Weapon arma = new Weapon("6; HACHA ROMA PEQUEÑA; 1; 1;");
    @Test
    public void testIncreaseBlood() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        minions.add(minion);
        armor.add(armadura);
        weapons.add(arma);
        Vampire vampire = new Vampire("vampirin", type, minions, armor, weapons);
        assertEquals(1, vampire.increaseBlood());
        assertEquals(2, vampire.increaseBlood());
    }

    @Test
    public void testReduceBlood() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        minions.add(minion);
        armor.add(armadura);
        weapons.add(arma);
        Vampire vampire = new Vampire("vampirin", type, minions, armor, weapons);

        // Establecer puntos de sangre y reducirlos
        vampire.setPuntosSangre(5);
        assertEquals(3, vampire.reduceBlood(2));
    }

    @Test
    public void testSpecialAttack() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        minions.add(minion);
        armor.add(armadura);
        weapons.add(arma);
        Vampire vampire = new Vampire("Test Vampire", type, minions, armor, weapons);

        // Configurar la disciplina para el ataque especial
        Discipline discipline = new Discipline();
        discipline.setCosteSangre(3);
        discipline.setDisciplineDamage(10);
        vampire.getDiscipline().setCosteSangre(3);
        vampire.getDiscipline().setDisciplineDamage(10);

        // Incrementar puntos de sangre para activar el ataque especial
        vampire.setPuntosSangre(4);

        // Comprobar el ataque especial
        assertEquals(10, vampire.SpecialAttack());
        assertEquals(4, vampire.getPuntosSangre()); // Comprobar que se reduce la cantidad correcta de puntos de sangre
    }

    @Test
    public void testAdjustAbility() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        minions.add(minion);
        armor.add(armadura);
        weapons.add(arma);
        Vampire vampire = new Vampire("Test Vampire", type, minions, armor, weapons);

        // Establecer puntos de sangre iniciales
        vampire.setPuntosSangre(5);

        vampire.adjustAbility(7, 2);
        assertEquals(9, vampire.getPuntosSangre());

        vampire.adjustAbility(5, 2);
        assertEquals(10, vampire.getPuntosSangre());
    }
}
