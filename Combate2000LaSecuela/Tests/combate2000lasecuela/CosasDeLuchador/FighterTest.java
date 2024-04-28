package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.Combat;
import combate2000lasecuela.managers.Enviroment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class FighterTest {
Minion minion = new Ghoul("2; MOROK; GHOUL; 4; 2;");
Weapon weapon1 = new Weapon("4; HACHA ROMA PEQUEÑA; 1; 1;");
Weapon weapon2= new Weapon("14; ESTOQUE; 2; 1;");
Armor armadura = new Armor("6; ARMADURA DE COBRE RARA; 1; 1;");
TFighter type = new TFighter("4;DESGRACIADO;0;0;0");
    @BeforeAll
    static void setUp() {
        Enviroment enviroment = new Enviroment();
        enviroment.setTesting(true);
    }
    @Test
    public void testStartFighting() {
        // Creamos dos panas
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        weapons.add(weapon1);
        minions.add(minion);
        armor.add(armadura);
        Fighter fighter1 = new Hunter("Fighter 1", type,minions,armor,weapons);
        Fighter fighter2 = new Vampire("Fighter 2", type,minions,armor,weapons);
        fighter1.setHealth(1); //forzamos que pierda
        Combat result = fighter1.startFighting(fighter2, 100);

        // Verificar que el resultado no sea nulo
        assertNotNull(result);

        // Verificar que el resultado de la pelea esté correcto
        assertNotNull(result.getWinner());
    }
    @Test
    public void testGenerateWeaponsText() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        weapons.add(weapon1);
        minions.add(minion);
        armor.add(armadura);
        Fighter fighter = new Hunter("Test Fighter", type, minions, armor, weapons);
        String[] expected = { "E Arma 1: 1. HACHA ROMA PEQUEÑA Ataque: 1 Una Mano"};
        assertArrayEquals(expected, fighter.generateWeaponsText());
    }

    @Test
    public void testGenerateArmorText() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        minions.add(minion);
        armor.add(armadura);
        weapons.add(weapon1);
        Fighter fighter = new Hunter("Test Fighter", type, minions, armor, weapons);

        String[] expected = {"E 1. ARMADURA DE COBRE RARA Ataque: 1 Defensa: 1"};
        assertArrayEquals(expected, fighter.generateArmorText());
    }
    @Test
    public void testCalculateMinionHealth() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        weapons.add(weapon1);
        minions.add(minion);
        armor.add(armadura);
        Fighter fighter = new Hunter("Attacker", type, minions, armor, weapons);
        // Crear una lista de esbirros y agregar algunos esbirros con diferentes valores de salud
        minions.add(minion);
        minions.add(minion);
        minions.add(minion);
        // Establecer la lista de esbirros en el luchador
        fighter.setMyMinions(minions);

        // Calcular la salud total de los esbirros
        int totalMinionHealth = fighter.calculateMinionHealth();

        // Verificar que la salud total de los esbirros sea la suma de las salud de cada esbirro
        assertEquals(8, totalMinionHealth);
    }

    @Test
    public void testAddMinionText() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        minions.add(minion);
        armor.add(armadura);
        weapons.add(weapon1);
        Fighter fighter = new Hunter("Test Fighter", type, minions, armor, weapons);
        ArrayList<String> minionText = new ArrayList<>();
        fighter.addMinionText(minionText, minion);

        String[] expected = {" MOROK Tipo:  GHOUL Dependencia: 4 Salud: 2"};
        assertArrayEquals(expected, minionText.toArray());
    }
    @Test
    public void testCheckSuccess() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        weapons.add(weapon1);
        minions.add(minion);
        armor.add(armadura);
        Fighter fighter = new Hunter("Attacker", type, minions, armor, weapons);
        int success = fighter.checkSuccess(10);

        assertTrue(success >= 0);
        assertTrue(success <= 10);
    }

    @Test
    public void testAttackPotential() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        weapons.add(weapon1);
        minions.add(minion);
        armor.add(armadura);
        Fighter attacker = new Hunter("Attacker", type, minions, armor, weapons);
        Fighter defender = new Vampire("Defender", type,  minions, armor, weapons);

        // Calcular el potencial de ataque del luchador atacante
        int attackPotential = attacker.attackPotential(defender);

        // Verificar que el potencial de ataque sea mayor o igual a 0
        assertTrue(attackPotential >= 0);
    }

    @Test
    public void testDefensePotential() {
        // Crear un luchador para la prueba
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        weapons.add(weapon1);
        minions.add(minion);
        armor.add(armadura);
        Fighter fighter = new Hunter("Attacker", type, minions, armor, weapons);
        int defensePotential = fighter.defensePotential(fighter);
        assertTrue(defensePotential >= 0);
    }
    //lo que interesa es ver que no te mete un nulo, el resto me la pela
    @Test
    public void testEquipDefaultArmor() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        weapons.add(weapon1);
        minions.add(minion);
        armor.add(armadura);
        Fighter fighter = new Hunter("Attacker", type, minions, armor, weapons);
        fighter.setArmor(armadura);
        fighter.equipDefaultArmor();
        assertNotNull(fighter.getArmor());
    }

    @Test
    public void testEquipDefaultWeapon() {
        LinkedList<Minion> minions = new LinkedList<>();
        LinkedList<Armor> armor = new LinkedList<>();
        LinkedList<Weapon> weapons = new LinkedList<>();
        weapons.add(weapon1);
        minions.add(minion);
        armor.add(armadura);
        Fighter fighter = new Hunter("Attacker", type, minions, armor, weapons);
        weapons.add(weapon1);
        fighter.setWeapon1(weapon1);
        fighter.equipDefaultWeapon();
        assertNotNull(fighter.getArma1());
    }
}
