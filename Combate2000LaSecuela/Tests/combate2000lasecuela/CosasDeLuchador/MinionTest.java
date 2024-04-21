package combate2000lasecuela.CosasDeLuchador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinionTest {

    @Test
    public void testMinionConstructor() {
        // Crear una línea de datos simulada para un esbirro
        String linea = "41;MORRIGAN;GHOUL;3;1;";

        // Crear una instancia de Minion utilizando el constructor y la línea de datos simulada
        Minion minion = new MinionImpl(linea);

        // Verificar que los valores de los atributos se hayan inicializado correctamente
        assertEquals("41", minion.getId());
        assertEquals("MORRIGAN", minion.getName());
        assertEquals("GHOUL", minion.getType());
        assertEquals("3", minion.getSpecialSkill());
        assertEquals(1, minion.getHealth());
    }

    // Clase de prueba
    private class MinionImpl extends Minion {
        public MinionImpl(String linea) {
            super(linea);
        }
        @Override
        public String getSpecialSkillName() {
            return "3";
        }
    }
}
