package combate2000lasecuela.CosasDeLuchador;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TalentTest {

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
