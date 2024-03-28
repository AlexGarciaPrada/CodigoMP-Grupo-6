package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.*;
import combate2000lasecuela.managers.MinionManager;
import combate2000lasecuela.managers.ItemManager;
import combate2000lasecuela.managers.ModifierManager;
import combate2000lasecuela.managers.TFighterManager;

import java.io.*;

import static combate2000lasecuela.Constants.*;

public class Loader implements Serializable {
    private ItemManager im;
    private MinionManager mm;
    private TFighterManager tfm;

    private ModifierManager mom;

    public Loader() {
        im = new ItemManager();
        mm= new MinionManager();
        tfm = new TFighterManager();
        mom = new ModifierManager();
        read(minionsFile);
        read(weaponsFile);
        read(tfighterFile);
        read(armorsFile);
        read(strentghFile);
        read(weaknessFile);
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
                    case strentghFile:
                        readStrengthFile(line);
                        break;
                    case weaknessFile:
                        readWeaknessFile(line);
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
        read(strentghFile);
        read(weaknessFile);
    }

    // ------------------------ MINIONS
    private void readMinionFile(String line) {

       String [] parts = line.split(";");
       switch(parts[2].trim()){
           case "HUMANO":
               mm.addElement("MinionMap", parts[0], new Human(line));
               break;
           case "DEMONIO":
               mm.addElement("MinionMap", parts[0], new Demon (line));
               break;
           case "GHOUL":
               mm.addElement("MinionMap", parts[0], new Ghoul (line));
               break;

       }
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

    private void readStrengthFile(String line) {
        String [] parts = line.split(";");
        mom.addElementSubMap("StrengthMap", parts[0], new Strength(line));
    }

    private void readWeaknessFile(String line) {
        String [] parts = line.split(";");
        mom.addElementSubMap("WeaknessMap", parts[0], new Weakness(line));
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

    public ModifierManager getMom() {return mom;}
}
