package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.Constants;

import java.io.Serializable;

public class Weapon extends Item implements Serializable {
    final boolean isOneHand;
    private String id;
    boolean chosen;
    public Weapon (String line){
        String [] values = line.split(";");
        this.id=values[0];
        this.setName(values[1].trim());
        this.setAttack(Integer.parseInt(values[2].trim())); ;
        this.isOneHand = fileTrimer(values[3].trim());
        this.chosen =false;
    }
    public boolean fileTrimer(String a){
        return ((a.trim()).equals("1"));
    }
    public int getDefense(){return 0;}
    @Override
    public String getId() {
        return this.id;
    }
    public String handConverter(){
        if (isOneHand){
            return Constants.oneHand;
        }
        return Constants.twoHands;
    }

    public boolean isOneHand() {
        return isOneHand;
    }

}
