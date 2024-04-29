package combate2000lasecuela.CosasDeLuchador;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    public void testSetAndGetAttributes() {
        Item item = new Weapon("14; ESTOQUE; 2; 1;");

        // Establecer atributos
        item.setName("ESTOQUE");
        item.setAttack(2);
        assertEquals("ESTOQUE", item.getName());
        assertEquals(2, item.getAttack());
    }
}
