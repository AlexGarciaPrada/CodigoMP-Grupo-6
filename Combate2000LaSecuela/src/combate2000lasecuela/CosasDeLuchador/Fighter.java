package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.Modifier;
import combate2000lasecuela.managers.MinionManager;
import combate2000lasecuela.managers.ItemManager;
import java.util.Random;
import java.util.Stack;
import combate2000lasecuela.Player;
public abstract class Fighter {
        private String name;
        private int gold;
        private int health;
        private int power;
        private Stack<Minion> myMinions;
        private Stack <Armor> myArmor;
        private Stack <Weapon> myWeapon;
        private int suerteM;
        private int suerteW;
        private int suerteA;
        private TFighter type;
        public Fighter(int suerteA,int suerteW,int suerteM) {
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
            for (int i=0; i<=numero;i++){
            esclavo = MinionManager.minionMap.get(numero);
                if (!("Vampire".equals(this.type)) || !(esclavo instanceof Human)){
                    this.myMinions.push(esclavo);
                }
            }
            return this.myMinions;
        }
    private Stack<Weapon> randomWeapons(int suerte) {
        Random random = new Random();
        Weapon arma;
        int numero = random.nextInt(28) + 1 + suerte;
        for (int i=1; i<=numero;i++){
            //arma = ItemManager.weaponMap.get(numero);
            //this.myWeapon.push(arma);
        }
        return this.myWeapon;
    }
    private Stack<Armor> randomArmor(int suerte){
        Random random = new Random();
        Armor armadura;
        int numero= random.nextInt(28)+1+suerte;
        for (int i=1; i<=numero; i++) {
            //armadura = ItemManager.armorMap.get(numero);
            //this.myArmor.push(armadura);
        }
        return this.myArmor;
    }

    //no los borres Dani, son solo setters
    public void setName(String name) {
            this.name = name;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setHealth(int Health) {
        this.health = Health;
    }

    public void setPower(int power) {
        this.power = power;
    }
    
}
