package combate2000lasecuela.CosasDeLuchador;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class GhoulTest {

    @Test
    public void testGetSpecialSkillName() {
        // Crear un Ghoul con una línea de datos
        String linea = "5; NEFARIUS; GHOUL; 3; 1;";
        Ghoul ghoul = new Ghoul(linea);

        // Verificar que el nombre de la habilidad especial sea "Dependencia"
        assertEquals("Dependencia", ghoul.getSpecialSkillName());
    }
}
