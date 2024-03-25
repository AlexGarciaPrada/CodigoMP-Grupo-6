package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.CosasDeLuchador.Item;

import java.io.Serializable;

public class Weapon extends Item implements Serializable {
    final boolean isOneHand;
    private int damage;
    private String id;
    boolean elegida;
    public Weapon (String linea){
        String [] valores = linea.split(";");
        this.id=valores[0];
        this.damage = Integer.parseInt(valores[2].trim());
        this.isOneHand = interpretarFichero( valores[3]);
        this.elegida=false;
    }
    public boolean interpretarFichero(String a){
        return (a.equals("1"));
    }
    public int getDamage(){
        return this.damage;
    }
    public int getDefense(){return 0;}
    @Override
    public String getId() {
        return this.id;
    }
}
