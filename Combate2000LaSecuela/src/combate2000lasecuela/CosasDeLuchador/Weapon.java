package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.CosasDeLuchador.Item;

import java.io.Serializable;

public class Weapon extends Item implements Serializable {
    final boolean isOneHand;

    private String id;
    boolean elegida;
    public Weapon (String linea){
        String [] valores = linea.split(";");
        this.id=valores[0];
        setEquipped(false);
        setName(valores[1].trim());
        setAttack(Integer.parseInt(valores[2].trim())); ;
        this.isOneHand = interpretarFichero( valores[3].trim());
        this.elegida=false;
    }
    public boolean interpretarFichero(String a){
        return ((a.trim()).equals("1"));
    }
    public int getDefense(){return 0;}
    @Override
    public String getId() {
        return this.id;
    }
    public String handConverter(){
        if (isOneHand){
            return "Una Mano";
        }
        return "Dos Manos";
    }
}
