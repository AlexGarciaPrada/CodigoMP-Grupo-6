package combate2000lasecuela.CosasDeLuchador;

import static org.junit.jupiter.api.Assertions.*;

import combate2000lasecuela.managers.Enviroment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModifierTest {
//el test es una gilipollez pero es que no sirven para nada los modifiers
@BeforeAll
static void setUp() {
    Enviroment enviroment = new Enviroment();
    enviroment.setTesting(true);
}
    @Test
    public void testConstructorAndGetters() {

        Modifier modifier = new Strength("7;TELEQUINESIS;1;");
        assertEquals("7", modifier.getId());
    }
}
