package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;

public class Armor extends Item implements Serializable {
    private final int protection;
    private final int damage;
    public Armor (String linea){
        String [] valores = linea.split(";");
        this.protection = Integer.parseInt(valores[2]);
        this.damage = Integer.parseInt( valores[3]);
    }
    public int getDefense (){
        return this.protection;
    }
    public int getDamage(){
        return this.damage;
    }
}
