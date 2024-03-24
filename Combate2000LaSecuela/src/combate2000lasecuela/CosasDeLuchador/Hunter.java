package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Stack;

public class Hunter extends Fighter implements Serializable {
int will;
    public Hunter(String name, TFighter type,String clase,
                  Stack<Minion> myMinions,Stack<Armor> myArmor,Stack<Weapon> myWeapon) {
        super(name, type,clase,
                myMinions, myArmor, myWeapon);
        this.will=will;
    }

    @Override
    public int SpecialAttack() {
        return 0;
    }

    @Override
    public int ajusteHabilidad(int pA, int pD) {
        return 0;
    }
}