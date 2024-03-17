package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.Modifier;

import java.util.ArrayList;
import java.util.Stack;

public abstract class Fighter {
        private String name;
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
            this.myMinions = randomMinions(suerte);
            this.myArmor= randomArmor();
            this.myWeapon = randomWeapons();
        }

        private Stack<Minion> randomMinions(int suerte){
            Random random = new Random();
            Minion esclavo;
            int numero= random.nextInt(80)+1+suerte;
            esclavo = GameManager.minionMap.get(numero);

            if (esclavo instanceof Demon || esclavo instanceof Ghoul){
                myMinions.push (esclavo);
            }
            else{
                if ("Vampire".equals(this.type)){
                    randomMinions();
                    return null;
                }else{
                    myMinions.push(esclavo);
                }
            }
            return myMinions;
        }
    }

    
}
