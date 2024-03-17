package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.CosasDeLuchador.Item;

import java.io.Serializable;

public class Weapon extends Item implements Serializable {
    private final boolean isOneHand;
    private final int damage;
    public Weapon (String linea){
        String [] valores = this.linea.split(";");
        this.damage = Integer.parseInt(valores[2]);
        this.isOneHand = interpretarFichero( valores[3]);
    }
    public boolean interpretarFichero(String a){
        return (a.equals("1"));
    }
}
