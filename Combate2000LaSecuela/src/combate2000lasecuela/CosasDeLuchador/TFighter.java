package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.Saveable;


public class TFighter implements Saveable {
    private String name;
    private int armorLuck;
    private int weaponLuck;
    private int minionLuck;
    public TFighter(String linea){
        String [] valores = linea.split(";");
        this.name=valores[1];
        this.minionLuck = Integer.parseInt(valores[2].trim());
        this.armorLuck = Integer.parseInt(valores[3].trim());
        this.weaponLuck = Integer.parseInt(valores[4].trim());
    }

    // ------------------------ GETTERS AND SETTERS

    public int getArmorLuck() {
        return armorLuck;
    }

    public int getWeaponLuck() {
        return weaponLuck;
    }

    public int getMinionLuck() {
        return minionLuck;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return null;
    }
}
