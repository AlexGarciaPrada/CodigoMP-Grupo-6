package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.*;
import combate2000lasecuela.managers.MinionManager;
import combate2000lasecuela.managers.ItemManager;
import combate2000lasecuela.managers.ModifierManager;
import combate2000lasecuela.managers.TFighterManager;

import java.io.*;

import static combate2000lasecuela.Constants.*;

public class Loader implements Serializable {
    private ItemManager itemManager;
    private MinionManager minionManager;
    private TFighterManager tFighterManager;
    private ModifierManager modifierManager;

    public Loader() {
        this.itemManager = new ItemManager();
        this.minionManager = new MinionManager();
        this.tFighterManager = new TFighterManager();
        this.modifierManager = new ModifierManager();
        this.load();
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
        read(tfighterFile);
        read(weaknessFile);
    }

    // ------------------------ MINIONS
    private void readMinionFile(String line) {

       String [] parts = line.split(";");
       switch(parts[2].trim()){
           case "HUMANO":
               minionManager.addElement("MinionMap", parts[0], new Human(line));
               break;
           case "DEMONIO":
               minionManager.addElement("MinionMap", parts[0], new Demon (line));
               break;
           case "GHOUL":
               minionManager.addElement("MinionMap", parts[0], new Ghoul (line));
               break;
       }
    }

    // ------------------------ WEAPONS
    private void readWeaponFile(String line) {
       String [] parts = line.split(";");
        itemManager.addElement("WeaponMap", parts[0], new Weapon(line));
    }

    // ------------------------ ARMORS
    private void readArmorFile(String line) {
        String [] parts = line.split(";");
        itemManager.addElement("ArmorMap", parts[0], new Armor(line));
    }

    private void readStrengthFile(String line) {
        String [] parts = line.split(";");
        modifierManager.addElement("StrengthMap", parts[0], new Strength(line));
    }

    private void readWeaknessFile(String line) {
        String [] parts = line.split(";");
        modifierManager.addElement("WeaknessMap", parts[0], new Weakness(line));
    }

    // ------------------------ TFIGHTER
    private void readTFighterFile(String line) {
        String [] parts = line.split(";");
        tFighterManager.addElement("TFighterMap", parts[0].trim(), new TFighter(line));
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public MinionManager getMinionManager() {
        return minionManager;
    }

    public TFighterManager gettFighterManager() {
        return tFighterManager;
    }

    public ModifierManager getModifierManager() {return modifierManager;}
}
