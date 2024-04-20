
package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Random;

public class Gift extends Specialskill implements Serializable {
    int rageCost;
    Random random = new Random();
    int giftDamage;
    public Gift(){
        this.giftDamage=2;
        this.rageCost=random.nextInt(3)+1;
    }
    @Override
    public int getDamage() {
        return this.giftDamage;
    }

    public int getRageCost() {
        return this.rageCost;
    }
    public void setGiftDamage(int i){
        this.giftDamage=i;
    }
}
