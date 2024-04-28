package combate2000lasecuela.CosasDeLuchador;

import static org.junit.jupiter.api.Assertions.*;

import combate2000lasecuela.managers.Enviroment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class HumanTest {
    @BeforeAll
    static void setUp() {
        Enviroment enviroment = new Enviroment();
        enviroment.setTesting(true);
    }
    @Test
    public void testGetSpecialSkillName() {
        String linea = "4; LUIS; HUMANO; NORMAL; 2;";
        Human human = new Human(linea);
        assertEquals("Lealtad", human.getSpecialSkillName());
    }
}
