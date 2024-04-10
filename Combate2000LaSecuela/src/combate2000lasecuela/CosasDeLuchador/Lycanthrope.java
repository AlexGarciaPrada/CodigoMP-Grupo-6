package combate2000lasecuela.CosasDeLuchador;

import java.util.LinkedList;
import java.util.Random;

public class Lycanthrope extends Fighter{
    int rage;
    int altura;
    int peso;
    Random random= new Random();
    private Gift don;
    public Lycanthrope(String name, TFighter type, LinkedList<Minion> myMinions, LinkedList<Armor> myArmor, LinkedList<Weapon> myWeapon) {
        super(name, type, myMinions, myArmor, myWeapon);
        this.peso= random.nextInt(20)+90;
        this.rage=0;
        this.altura=random.nextInt(100)+50;//en centímetros
        this.don= new Gift();
    }


    public int SpecialAttack() {
        if (getRage()>=this.don.getRageCost()){
            return don.getDamage();
        }
        return 0;
    }

    @Override
    public void adjustAbility(int pA, int pD) {
        if ((pA<pD)&&(getRage()<=3)){
            setRage(getRage()+1);
        }
    }
    public int getRage(){
        return this.rage;
    }
    public void setRage(int rage){
        this.rage=rage;
    }
}
