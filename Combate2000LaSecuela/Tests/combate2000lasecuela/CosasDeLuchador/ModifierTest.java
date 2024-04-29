package combate2000lasecuela.CosasDeLuchador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModifierTest {
//el test es una gilipollez pero es que no sirven para nada los modifiers
    @Test
    public void testConstructorAndGetters() {

        Modifier modifier = new Strength("7;TELEQUINESIS;1;");
        assertEquals("7", modifier.getId());
    }
}
