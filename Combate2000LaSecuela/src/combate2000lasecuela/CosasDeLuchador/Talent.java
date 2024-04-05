package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Random;

public class Talent extends Specialskill implements Serializable {
    int willDamage;
    public Talent(){
        this.willDamage=2;
    }
    @Override
    public int getDamage() {
        return this.willDamage;
    }
}
