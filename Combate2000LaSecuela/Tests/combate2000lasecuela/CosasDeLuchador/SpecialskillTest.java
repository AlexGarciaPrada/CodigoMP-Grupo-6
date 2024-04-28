package combate2000lasecuela.CosasDeLuchador;


import combate2000lasecuela.managers.Enviroment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialskillTest {
    @BeforeAll
    static void setUp() {
        Enviroment enviroment = new Enviroment();
        enviroment.setTesting(true);
    }
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