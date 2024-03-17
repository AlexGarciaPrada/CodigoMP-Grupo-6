package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.Modifier;
import combate2000lasecuela.managers.MinionManager;

import java.util.ArrayList;
import java.util.Stack;

public abstract class Fighter {
        private final String name;
        private int gold;
        private int health;
        private int power;
        private String type;
        private Stack<Minion> myMinions;
        private Stack <Armor> myArmor;
        private Stack <Weapon> myWeapon;
        private int suerteM;
        private int suerteW;
        private int suerteA;
        public Fighter(String name,Stack <Weapon> myWeapon , int health, Stack <Armor> myArmor, int power, String type, Stack<Minion> myMinions) {
            this.name = name;
            this.health = health;
            this.power = power;
            this.type = type;
            this.suerteM=type.suerteM;
            this.suerteW=type.suerteW;
            this.suerteA=type.suerteA;
            this.myMinions = randomMinions(suerteM);
            this.myArmor= randomArmor(suerteA);
            this.myWeapon = randomWeapons(suerteW);
        }

        private Stack<Minion> randomMinions(int suerte){
            Random random = new Random();
            Minion esclavo;
            int numero= random.nextInt(80)+1+suerte;
            esclavo = MinionManager.minionMap.get(numero);
            if (esclavo instanceof Demon || esclavo instanceof Ghoul){
                this.myMinions.push (esclavo);
            }
            else{
                if ("Vampire".equals(this.type)){
                    randomMinions();
                    return null;
                }else{
                    this.myMinions.push(esclavo);
                }
            }
            return this.myMinions;
        }
    private Stack<Weapon> randomWeapons(int suerte){
        Random random = new Random();
        Weapon arma;
        int numero= random.nextInt(28)+1+suerte;
        arma = ItemManager.weaponMap.get(numero);
            this.myWeapon.push (arma);
        }
        return this.myMinions;
    }
    private Stack<Armor> randomArmor(int suerte){
        Random random = new Random();
        Armor armadura;
        int numero= random.nextInt(28)+1+suerte;
        armadura = ItemManager.armorMap.get(numero);
            this.myMinions.push (armadura);
        return this.myMinions;
    }
    }

    
}
