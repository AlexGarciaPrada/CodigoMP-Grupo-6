package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.Minion;
import combate2000lasecuela.CosasDeLuchador.Weapon;
import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.managers.MinionManager;
import combate2000lasecuela.managers.ItemManager;
import java.io.*;

public class Loader implements Serializable {
    private String filename;
    private String[] parts;
    private ItemManager im;
    private MinionManager mm;

    public Loader(String filename) {
        this.filename = filename;
    }

    private void read(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                switch (filename) {
                    case Constants.minionsFile:
                        readMinionFile(line);
                        break;
                    case Constants.weaponsFile:
                        readWeaponFile(line);
                        break;
                    case Constants.armorsFile:
                        readArmorFile(line);
                        break;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------------------ MINIONS
    private void readMinionFile(String line) {
        parts = line.split(";");
        mm.addElement("Minion", parts[0], new Minion(line));
    }

    // ------------------------ WEAPONS
    private void readWeaponFile(String line) {
        parts = line.split(";");
        im.addElementSubMap("Weapon", parts[0], new Weapon(line));
    }

    // ------------------------ ARMORS
    private void readArmorFile(String line) {
        parts = line.split(";");
        im.addElementSubMap("Armor", parts[0], new Armor(line));
    }
}
