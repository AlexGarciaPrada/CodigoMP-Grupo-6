package combate2000lasecuela.CosasDeLuchador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DisciplineTest {

    @Test
    public void testUsarDisciplina_PuntosSuficientes() {
        Discipline discipline = new Discipline();
        int puntosDisponibles = 3;
        boolean resultado = discipline.usarDisciplina(puntosDisponibles);
        assertTrue(resultado);
        assertTrue(discipline.getCosteSangre() >= 1 && discipline.getCosteSangre() <= 3);
    }

    @Test
    public void testUsarDisciplina_PuntosInsuficientes() {
        Discipline discipline = new Discipline();
        int puntosDisponibles = 0;

        boolean resultado = discipline.usarDisciplina(puntosDisponibles);

        assertFalse(resultado);
        assertTrue(discipline.getCosteSangre() >= 1 && discipline.getCosteSangre() <= 3);
    }
}
