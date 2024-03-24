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
        private Weapon arma1;
        private Weapon arma2;
        private Armor armadura;

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
                i++; //donde recibe el desafiado
                terminal.show("Ronda numero" + i + "comienza");
                    pA = potencialAtaque(desafiante);
                    pD = potencialDefensa(this);
                        if (comprobarDaños(pA,pD)){
                            terminal.show(this.name+" ha recibido un golpe");
                            if (this.minionHealth>0){
                                terminal.show(" aunque lo han acabado recibiendo los esbirros");
                               this.minionHealth-=1;
                            }else {
                                this.health -= 1;
                                terminal.show(this.health+ " vidas restantes");
                            }
                        }
                        //donde recibe el desafiante
                //aunque esté separado, físicamente, ocurre de manera simultánea
                    pA = potencialAtaque(this);
                    pD = potencialDefensa(desafiante);
                    if (comprobarDaños(pA,pD)){
                        terminal.show(desafiante.name+" ha recibido un golpe");
                        if (desafiante.minionHealth>0){
                            terminal.show(" aunque lo han acabado recibiendo los esbirros");
                            desafiante.minionHealth-=1;
                        }else {
                            desafiante.health -= 1;
                            terminal.show (desafiante.health+" vidas restantes");
                        }
                    }
            }while((this.health>0)||(desafiante.health>0));
            return null;
    }
    public int potencialAtaque (Fighter f){
        int potencial=f.power+f.arma1.getDamage()+f.armadura.getDamage();
        return verExitos(potencial);
    }
    public int potencialDefensa (Fighter f){
        int potencial=f.armadura.getDefense();
            return verExitos(potencial);
    }
    public int verExitos (int potencial){
        int acierto=0;
        int aux;
        for (int i=0; i<potencial;i++){
         aux = random.nextInt(6)+1;
         if (aux>=5){
             acierto+=1;
         }
        }
        return acierto;
    }
    private boolean comprobarDaños(int pA, int pD){
        return (pA>pD);
    }
    public int calcularVidaMinions(){
        Minion esclavo;
        int total=0;
        Stack<Minion> copia;
        copia=this.myMinions;
        while (!copia.isEmpty()){
         esclavo=copia.pop();
         total += esclavo.getHealth();
        }return total;
    }
}
