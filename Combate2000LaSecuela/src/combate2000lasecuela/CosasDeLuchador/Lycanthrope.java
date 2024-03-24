package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.CosasDeLuchador.Fighter;

import java.io.Serializable;
import java.util.Random;
import java.util.Stack;

public class Lycanthrope extends Fighter implements Serializable {
int rage;
int altura;
int peso;
Random random= new Random();
private Gift don;
    public Lycanthrope(String name, TFighter type,String clase, Stack<Minion> myMinions,Stack<Armor> myArmor,Stack<Weapon> myWeapon) {
        super(name, type,clase, myMinions, myArmor, myWeapon);
        this.peso= random.nextInt(20)+90;
        this.rage=0;
        this.altura=random.nextInt(100)+50;//en centímetros
        this.don= new Gift();
    }

    @Override
    public int SpecialAttack() {
        if (getRage()>=this.don.getRageCost()){
            return don.getGiftDamage();
        }
        return 0;
    }

    @Override
    public void ajusteHabilidad(int pA, int pD) {
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
