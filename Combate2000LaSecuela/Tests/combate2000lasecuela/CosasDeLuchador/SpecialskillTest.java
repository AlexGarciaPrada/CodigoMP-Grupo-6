package combate2000lasecuela.CosasDeLuchador;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialskillTest {

    @Test
    public void testGetDamage_AbstractMethod() {
        Specialskill specialskill = new Specialskill() {
            @Override
            public int getDamage() {
                return 10;
            }
        };
        assertEquals(10, specialskill.getDamage());
    }
}