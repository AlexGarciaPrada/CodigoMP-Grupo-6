package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.managers.Enviroment;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import combate2000lasecuela.CosasDeLuchador.Weapon;
public class WeaponTest {
    @BeforeAll
    static void setUp() {
        Enviroment enviroment = new Enviroment();
        enviroment.setTesting(true);
    }
    @Test
    void weaponTest() {
    }

    @Test
    void handConverterIfOneHand() {
        Weapon weapon = new Weapon("1; HACHA ROMA PEQUEÑA; 1; 1;");
        assertEquals("Una Mano", weapon.handConverter());
    }

    @Test
    void handConverterIfTwoHands() {
        Weapon weapon = new Weapon("23; CLAYMORE; 2; 2;");
        assertEquals("Dos Manos", weapon.handConverter());
    }

}
