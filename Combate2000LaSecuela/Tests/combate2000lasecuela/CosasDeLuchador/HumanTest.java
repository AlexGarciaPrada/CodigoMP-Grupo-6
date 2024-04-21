package combate2000lasecuela.CosasDeLuchador;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class HumanTest {

    @Test
    public void testGetSpecialSkillName() {
        String linea = "4; LUIS; HUMANO; NORMAL; 2;";
        Human human = new Human(linea);
        assertEquals("Lealtad", human.getSpecialSkillName());
    }
}
