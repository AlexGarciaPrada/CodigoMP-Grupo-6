package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.CosasDeLuchador.Item;

import java.io.Serializable;

public class Weapon extends Item implements Serializable {
    final boolean isOneHand;
    private boolean equipped1;
    private boolean equipped2;
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
        equipped1= false;
        equipped2=false;
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

    public boolean isOneHand() {
        return isOneHand;
    }

    public boolean getEquipped1() {
        return equipped1;
    }

    public boolean getEquipped2() {
        return equipped2;
    }

    public void setEquipped1(boolean equipped1) {
        this.equipped1 = equipped1;
    }

    public void setEquipped2(boolean equipped2) {
        this.equipped2 = equipped2;
    }
}
