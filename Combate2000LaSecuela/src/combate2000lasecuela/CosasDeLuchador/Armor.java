package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.lang.String;
public class Armor extends Item implements Serializable {
    private final int protection;
    private final int damage;
    private final String id;
    public Armor (String linea){
        String [] valores = linea.split(";");
        this.id=valores[0];
        this.protection = Integer.parseInt(valores[2]);
        this.damage = Integer.parseInt( valores[3]);
    }
    public int getDefense (){
        return this.protection;
    }
    public int getDamage(){
        return this.damage;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
