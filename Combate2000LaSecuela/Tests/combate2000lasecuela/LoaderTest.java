package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.Strength;
import combate2000lasecuela.managers.Enviroment;
import combate2000lasecuela.managers.MinionManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoaderTest {
    @BeforeAll
    static void setUp() {
        Loader loader = new Loader();
        Enviroment enviroment = new Enviroment();
        enviroment.setTesting(true);
    }

    @Test
    void testRead() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./files/txtfiles/Armaduras.txt"));
            assertEquals("1; ARMADURA DE COBRE COMÚN; 1; 0;", br.readLine());  //testing first line of the txt file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testReadMinionFileGhoul() {
        Loader loader = new Loader();

        loader.readMinionFile("5; NEFARIUS; GHOUL; 3; 1;");
        assertEquals(loader.getMinionManager().getElements().get("MinionMap").get("5").getName(), " NEFARIUS");
    }
    @Test
    void testReadMinionFileHuman() {
        Loader loader = new Loader();

        loader.readMinionFile("2; PEPE; HUMANO; ALTA; 3;");
        assertEquals(loader.getMinionManager().getElements().get("MinionMap").get("2").getName(), " PEPE");
    }
    @Test
    void testReadMinionFileDemon() {
        Loader loader = new Loader();

        loader.readMinionFile("3; LILITH; DEMONIO; Pacto de Sangre; 2;");
        assertEquals(loader.getMinionManager().getElements().get("MinionMap").get("3").getName(), " LILITH");
    }
    @Test
    void testReadArmorFile() {
        Loader loader = new Loader();

        loader.readWeaponFile("5; ARMADURA DE COBRE RARA; 1; 1;");
        assertEquals(loader.getItemManager().getElements().get("WeaponMap").get("5").getName(), "ARMADURA DE COBRE RARA");
    }
    @Test
    void testReadWeaponFile() {
        Loader loader = new Loader();

        loader.readWeaponFile("1; HACHA ROMA PEQUEÑA; 1; 1;");
        assertEquals(loader.getItemManager().getElements().get("WeaponMap").get("1").getName(), "HACHA ROMA PEQUEÑA");
    }
    @Test
    void testReadStrengthFile() {
        Loader loader = new Loader();

        loader.readStrengthFile("4;SENTIDOS AGUDIZADOS;4;");
        Strength strength = (Strength) loader.getModifierManager().getCollection("StrengthMap").get("4");
        assertNotNull(strength);
    }
    @Test
    void testReadWeaknessFile() {
        Loader loader = new Loader();

        loader.readStrengthFile("9;CRUCES DE PLATA;4;");
        Strength strength = (Strength) loader.getModifierManager().getCollection("StrengthMap").get("9");
        assertNotNull(strength);
    }
    @Test
    void testLoad() {
        Loader loader = new Loader();
        loader.load();
        assertFalse(loader.getItemManager().getElements().get("ArmorMap").isEmpty());
        assertFalse(loader.getItemManager().getElements().get("WeaponMap").isEmpty());
        assertFalse(loader.getMinionManager().getElements().get("MinionMap").isEmpty());
        assertFalse(loader.getModifierManager().getCollection("StrengthMap").isEmpty());
        assertFalse(loader.getModifierManager().getCollection("WeaknessMap").isEmpty());
        assertFalse(loader.gettFighterManager().getElements().get("TFighterMap").isEmpty());
    }
}