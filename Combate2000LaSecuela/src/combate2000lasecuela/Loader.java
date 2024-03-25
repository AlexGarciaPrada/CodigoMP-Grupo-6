package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.Minion;
import combate2000lasecuela.CosasDeLuchador.TFighter;
import combate2000lasecuela.CosasDeLuchador.Weapon;
import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.managers.MinionManager;
import combate2000lasecuela.managers.ItemManager;
import combate2000lasecuela.managers.TFighterManager;

import java.io.*;

import static combate2000lasecuela.Constants.*;

public class Loader implements Serializable {
    private ItemManager im;
    private MinionManager mm;
    private TFighterManager tfm;

    public Loader() {
      im = new ItemManager();
      mm= new MinionManager();
      tfm = new TFighterManager();
      read(minionsFile);
      read(weaponsFile);
      read(tfighterFile);
      read(armorsFile);


    }

    private void read(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                switch (filename) {
                    case minionsFile:
                        readMinionFile(line);
                        break;
                    case weaponsFile:
                        readWeaponFile(line);
                        break;
                    case armorsFile:
                        readArmorFile(line);
                        break;
                    case tfighterFile:
                        readTFighterFile(line);
                        break;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void load() {
        read(minionsFile);
        read(Constants.weaponsFile);
        read(Constants.armorsFile);
    }

    // ------------------------ MINIONS
    private void readMinionFile(String line) {

       String [] parts = line.split(";");
        mm.addElement("MinionMap", parts[0], new Minion(line));
    }

    // ------------------------ WEAPONS
    private void readWeaponFile(String line) {
       String [] parts = line.split(";");
        im.addElementSubMap("WeaponMap", parts[0], new Weapon(line));
    }

    // ------------------------ ARMORS
    private void readArmorFile(String line) {
        String [] parts = line.split(";");
        im.addElementSubMap("ArmorMap", parts[0], new Armor(line));
    }

    // ------------------------ TFIGHTER
    private void readTFighterFile(String line) {

        String [] parts = line.split(";");
        tfm.addElement("TFighterMap", parts[0], new TFighter(line));
    }

    public ItemManager getIm() {
        return im;
    }

    public MinionManager getMm() {
        return mm;
    }

    public TFighterManager getTfm() {
        return tfm;
    }
}
