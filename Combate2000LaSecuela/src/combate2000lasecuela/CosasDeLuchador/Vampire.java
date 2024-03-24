package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.CosasDeLuchador.Fighter;

import java.io.Serializable;
import java.util.Stack;

public class Vampire extends Fighter implements Serializable {
    public Vampire(String name, TFighter type,String clase,
                   Stack<Minion> myMinions,Stack<Armor> myArmor,Stack<Weapon> myWeapon) {
        super(name, type,clase,
                 myMinions, myArmor, myWeapon);
    }
}
