package combate2000lasecuela.CosasDeLuchador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedList;

public class DemonTest {

    @Test
    public void testGetDemonListText_NoMinions() {
        String linea = "9; ASMODEO; DEMONIO; Pacto de Sombra; 1;";
        Demon demon = new Demon(linea);
        assertEquals(2, demon.getDemonListText().length);
        assertEquals("Esbirros de  ASMODEO :", demon.getDemonListText()[0]);
    }

    @Test
    public void testGetDemonListText_WithMinions() {
        // Crear un Demon con una lista de esbirros
        String linea = "9;ASMODEO; DEMONIO; Pacto de Sombra; 1;";
        Demon demon = new Demon(linea);
        LinkedList<Minion> listaDemoniaca = new LinkedList<>();
        listaDemoniaca.add(new Ghoul("5; NEFARIUS; GHOUL; 3; 1;"));
        listaDemoniaca.add(new Human("7; ANA; HUMANO; BAJA; 1;"));
        demon.setDemonList(listaDemoniaca);

        String[] demonListText = demon.getDemonListText();
        assertEquals(3, demonListText.length); // Solo hay tres elementos en la lista
        assertEquals("Esbirros de ASMODEO :", demonListText[0]);
        assertEquals(" NEFARIUS Tipo:  GHOUL Dependencia: 3 Salud: 1", demonListText[1]);
        assertEquals(" ANA Tipo:  HUMANO Lealtad: BAJA Salud: 1", demonListText[2]);
    }

}
