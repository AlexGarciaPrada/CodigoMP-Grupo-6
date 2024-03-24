package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.CosasDeLuchador.Fighter;

import java.util.Stack;

public class Lycanthrope extends Fighter {
int rage;
    public Lycanthrope(String name, TFighter type,String clase,
                       Stack<Minion> myMinions,Stack<Armor> myArmor,Stack<Weapon> myWeapon) {
        super(name, type,clase, myMinions, myArmor, myWeapon);

        this.rage=0;
    }
}
