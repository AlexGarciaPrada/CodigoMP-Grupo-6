package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.Combat;
import combate2000lasecuela.Modifier;
import combate2000lasecuela.managers.MinionManager;
import combate2000lasecuela.managers.ItemManager;
import java.util.Random;
import java.util.Stack;
import combate2000lasecuela.Player;
import combate2000lasecuela.screen.Terminal;
import combate2000lasecuela.screen.Textterminal;
import java.lang.Integer;
import java.lang.String;
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
        private String clase;
        private TFighter type;
        private MinionManager minionManager;
        private ItemManager itemManager;
        private Random random = new Random();
        private int minionHealth;

    public Fighter(String name, TFighter type,String clase,
        Stack<Minion> myMinions,Stack<Armor> myArmor,Stack<Weapon> myWeapon) {
            this.name = name;
            this.health = random.nextInt(5) + 1;
            this.power = random.nextInt(5)+1;
            this.clase=clase;
            this.type = type;
            this.suerteM=type.suerteM;
            this.suerteW=type.suerteW;
            this.suerteA=type.suerteA;
            this.myMinions = myMinions;
            this.myArmor= myArmor;
            this.myWeapon = myWeapon;
            this.minionHealth=calcularVidaMinions();
        }


    public Combat startFighting (Fighter desafiante){
            Textterminal terminal = new Textterminal();
            int i=0;
            int pA=0;
            int pD=0;
            do {
                i++;
                terminal.show("Ronda numero" + i + "comienza");
                if ( i % 2 != 0) {//es impar
                    pA = potencialAtaque(desafiante);
                    pD = potencialDefensa(this);
                        if (comprobarDaños(pA,pD)){
                            this.health-=1;
                        }
                }else{ //es par
                    pA = potencialAtaque(this);
                    pD = potencialDefensa(desafiante);
                    if (comprobarDaños(pA,pD)){
                        desafiante.health-=1;
                    }
                }
            }while((this.health>0)||(desafiante.health>0));
            return null;
    }
    private int potencialAtaque (Fighter f){

            return 0;
    }
    private int potencialDefensa (Fighter f){
            return 0;
    }
    private boolean comprobarDaños(int pA, int pD){
        return (pA>pD);
    }
    public int calcularVidaMinions(){
        Minion esclavo;
        Stack<Minion> copia;
        copia=this.myMinions;
        while (!copia.isEmpty()){
         esclavo=copia.pop();
         int total = esclavo.getHealth();
        }return 0;
    }
}
