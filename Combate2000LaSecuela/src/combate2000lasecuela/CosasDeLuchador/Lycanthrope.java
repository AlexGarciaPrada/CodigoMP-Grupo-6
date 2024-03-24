package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.CosasDeLuchador.Fighter;

import java.io.Serializable;
import java.util.Stack;

public class Lycanthrope extends Fighter implements Serializable {
int rage;
    public Lycanthrope(String name, TFighter type,String clase,
                       Stack<Minion> myMinions,Stack<Armor> myArmor,Stack<Weapon> myWeapon) {
        super(name, type,clase, myMinions, myArmor, myWeapon);

        this.rage=0;
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
