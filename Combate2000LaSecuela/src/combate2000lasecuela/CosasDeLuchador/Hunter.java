package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Stack;

public class Hunter extends Fighter implements Serializable {
int will;
Talent talent;
    public Hunter(String name, TFighter type, Stack<Minion> myMinions,Stack<Armor> myArmor,Stack<Weapon> myWeapon) {
        super(name, type, myMinions, myArmor, myWeapon);
        this.will=3;
        this.talent = new Talent();
    }

    @Override
    public int SpecialAttack() {
        return (talent.getWillDamage()+getWill());
    }

    @Override
    public void ajusteHabilidad(int pA, int pD) {
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