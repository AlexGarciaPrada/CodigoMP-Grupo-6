package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Random;

public class Talent implements Serializable {
    int willCost;
    Random random = new Random();
    int willDamage;
    public Talent(){
        this.willDamage=2;
    }

    public int getWillDamage() {
        return this.willDamage;
    }
}
