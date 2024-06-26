package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.LinkedList;

public class Hunter extends Fighter implements Serializable {
    private int will;
    private Talent talent;
    public Hunter(String name, TFighter type, LinkedList<Minion> myMinions, LinkedList<Armor> myArmor, LinkedList<Weapon> myWeapon) {
        super(name, type, myMinions, myArmor, myWeapon);
        this.will=3;
        this.talent = new Talent();
    }

    public int SpecialAttack() {
        return (talent.getDamage()+getWill());
    }

    @Override
    public void adjustAbility(int pA, int pD) {
        if ((pA<pD)&&(getWill()>0)){
            setWill(getWill()-1);
        }
    }

    public int getWill() {
        return this.will;
    }

    public void setWill(int will) {
        this.will = will;
    }

}