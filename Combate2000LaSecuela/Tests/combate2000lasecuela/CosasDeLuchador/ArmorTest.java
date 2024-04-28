package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.managers.Enviroment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArmorTest {
    @BeforeAll
    static void setUp() {
        Enviroment enviroment = new Enviroment();
        enviroment.setTesting(true);
    }
    @Test
    public void testArmorConstructor() {
        String linea = "1; ARMADURA DE BRONCE; 3; 2";
        Armor armor = new Armor(linea);
        assertEquals("1", armor.getId());
        assertEquals("ARMADURA DE BRONCE", armor.getName());
        assertEquals(3, armor.getDefense());
        assertEquals(2, armor.getAttack());
    }

    @Test
    public void testGetDefense() {
        Armor armor = new Armor("1; ARMADURA DE ORO; 5; 2");
        assertEquals(5, armor.getDefense());
    }

    @Test
    public void testGetId() {
        Armor armor = new Armor("10; ARMADURA DE DIAMANTE; 7; 3");
        assertEquals("10", armor.getId());
    }
}
