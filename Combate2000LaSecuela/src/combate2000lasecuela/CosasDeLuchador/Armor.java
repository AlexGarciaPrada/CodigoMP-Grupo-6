package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.lang.String;
public class Armor extends Item implements Serializable {
    private final String id;
    private final int defense;
    public Armor (String linea){
        String [] valores = linea.split(";");
        this.id=valores[0];
        setName(valores[1].trim());
        this.defense = Integer.parseInt(valores[2].trim());
        setAttack(Integer.parseInt( valores[3].trim()));;
    }
    public int getDefense (){
        return this.defense;
    }
    @Override
    public String getId() {
        return this.id;
    }
}
