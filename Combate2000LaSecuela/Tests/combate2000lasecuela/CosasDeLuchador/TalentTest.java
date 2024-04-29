package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.managers.Enviroment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TalentTest {
    @BeforeAll
    static void setUp() {
        Enviroment enviroment = new Enviroment();
        enviroment.setTesting(true);
    }
    @Test
    public void testGetDamage_DefaultValue() {
        Talent talent = new Talent();
        assertEquals(2, talent.getDamage());
    }

    @Test
    public void testSetWillDamage() {
        Talent talent = new Talent();
        talent.setWillDamage(5);
        assertEquals(5, talent.getDamage());
    }
}
