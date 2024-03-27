package combate2000lasecuela.CosasDeLuchador;
import combate2000lasecuela.Combat;
import combate2000lasecuela.PendingChallenges;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.lang.Integer;
import java.lang.String;
import java.util.LinkedList;
public abstract class Fighter implements Serializable {

    private String name;
        private int gold;
        private int health;
        private int power;
        private Stack<Minion> myMinions;
        private LinkedList <Armor> myArmor;
        private LinkedList<Weapon> myWeapon;
        private TFighter type;
        private Random random = new Random(); //Esto es un atributo
        private int minionHealth;
        private Weapon arma1;
        private Weapon arma2;
        private Armor armadura;

        private PendingChallenges pendingChallenges;
        Specialskill specialskill;

    public Fighter(String name, TFighter type,
                   Stack<Minion> myMinions,LinkedList<Armor> myArmor,
                   LinkedList<Weapon> myWeapon) {
            this.name = name;
            this.health = random.nextInt(5) + 1;
            this.power = random.nextInt(5)+1;
            this.type = type;
            this.myMinions = myMinions;
            this.myArmor= myArmor;
            this.myWeapon = myWeapon;
            this.minionHealth= calcularVidaMinions();
            this.pendingChallenges = new PendingChallenges();
            this.arma1=null;
            this.arma2=null;
            this.armadura=null;
            this.gold=100;
        }


    public Combat startFighting (Fighter desafiante, int oroApostado){
            int i=0;
            int pA=0;
            int pD=0;
            String esEmpate = null;//preparativo para meterselo al combat
            do {
                i++; //donde recibe el desafiado
                //terminal.show("Ronda numero" + i + "comienza");
                    pA = potencialAtaque(desafiante);
                    pD = potencialDefensa(this);
                        if (comprobarDaños(pA,pD)){
                            ajusteHabilidad(pA,pD);
                           // terminal.show(this.name+" ha recibido un golpe");
                            if (this.minionHealth>0){
                              //  terminal.show(" aunque lo han acabado recibiendo los esbirros");
                               this.minionHealth-=1;
                            }else {
                                this.health -= 1; //considerar caso de que se maten a la vez
                               // terminal.show(this.health+ " vidas restantes");
                            }
                        }
                //donde recibe el desafiante
                //aunque esté separado, físicamente, ocurre de manera simultánea
                    pA = potencialAtaque(this);
                    pD = potencialDefensa(desafiante);
                    if (comprobarDaños(pA,pD)){
                        ajusteHabilidad(pA,pD);
                      //  terminal.show(desafiante.name+" ha recibido un golpe");
                        if (desafiante.minionHealth>0){
                           // terminal.show(" aunque lo han acabado recibiendo los esbirros");
                            desafiante.minionHealth-=1;
                        }else {
                            desafiante.health -= 1;
                          //  terminal.show (desafiante.health+" vidas restantes");
                        }
                    }
            }while((this.health>0)||(desafiante.health>0));
            if ((this.health==0)&& (desafiante.health==0)){
                esEmpate="si";
            }
            return new Combat(desafiante, this, i, oroApostado, esEmpate);
    }
    public int potencialAtaque (Fighter f){
        int potencial=f.power+f.arma1.getDamage()+f.armadura.getDamage()+ f.specialskill.getDamage()+SpecialAttack();
        if (f.arma2!=null){
            potencial+=f.arma2.getDamage();
        }
        return verExitos(potencial);
    }
    public int potencialDefensa (Fighter f){
        int potencial=f.armadura.getDefense()+ f.specialskill.getDamage()+SpecialAttack();
        //porque la implementación de ambos sería idéntica.
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
        if (getMyMinion() == null){
            return 0;
        }else {
            int total = 0;

            while (!this.myMinions.isEmpty()) {
                Minion esclavo = this.myMinions.pop();
                if (esclavo != null) {
                    total += esclavo.getHealth();
                }
            }
            return total;
        }
    }
    public Stack<Minion> getMyMinion(){
        return this.myMinions;
    }
    public void elegirArma(LinkedList<Weapon> myWeapon,String leido){
       // terminal.show("Se te mostraran las armas de que dispones");
        //mostrarArmas();
        //terminal.show("Elige un arma de las disponibles indicando su numero identificativo");
        setWeapon1(buscarArmaLeida(leido));
        if (getArma1()==null){
          //  terminal.show("No has introducido un valor valido");
            elegirArma(myWeapon,leido);
        }else {//si es valor valido
            if (getArma1().isOneHand) {
                //terminal.show("Como tu arma es de una mano se te permite coger otra arma");
                //terminal.show("Quieres hacerlo?");
                if ("SI".equals(leido.toUpperCase())){
                    //terminal.show("Pon su numero al igual que antes");
                    leido = "scanner.nextLine()";
                    Weapon temporal = (buscarArmaLeida(leido));
                    if (!temporal.isOneHand && !temporal.elegida) { //anti buggs (que tengas equipada la misma arma dos veces)
                       // terminal.show("No hagas trampas!!");
                        //terminal.show("Por intentarlo se te impide en esta ocasión introducir la segunda arma");
                    } else {
                        setWeapon2(temporal);
                    }
                }
            } else {
               // terminal.show("Dado que portas un mandoble no puedes tener mas armas simultaneamente");
            }
        }
    }
    public void elegirArmadura (LinkedList<Armor> myArmor, Integer opcion){
        //terminal.show("A continuacion se te mostrara tu repertorio de armaduras");
        mostrarArmaduras();
      //  terminal.show("Elige la que quieras de todas ellas indicando el numero que les corresponde");
        String leido = opcion.toString();
        setArmor(buscarArmaduraLeida(leido));
        if (getArmadura()==null){
            //terminal.show("Valor no valido");
            elegirArmadura(myArmor,opcion);
        }else{
           // terminal.show("La armadura se ha seleccionado con exito");
        }
    }
    public abstract void ajusteHabilidad(int pA, int pD);
    public abstract int SpecialAttack();

    public String [] generateWeaponsText() {
        ArrayList<String> weapontext=new ArrayList<>();
        int i =1;
        for (Weapon element: myWeapon){
            weapontext.add(Integer.toString(i) +". "+element.getName()+"Ataque: "+Integer.toString(element.getAttack()));
            i++;
        }
        return weapontext.toArray(new String[weapontext.size()]);
    }
    public void mostrarArmaduras(){
        do {
            //terminal.show(getMyWeapon().remove().toString());
        } while(!getMyWeapon().isEmpty());
    }
    public LinkedList<Weapon> getMyWeapon(){
        return this.myWeapon;
    }
    public LinkedList<Armor> getMyArmor(){
        return this.myArmor;
    }


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

    public int getHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }

    public Weapon buscarArmaLeida (String leido){
        boolean encontrado=false;
        Weapon aux;
        Weapon aux2=null;
        while ((!getMyWeapon().isEmpty())||(encontrado)) {
            aux=getMyWeapon().remove();
            if (leido.equals(aux.getId())){
                encontrado=true;
                aux2=aux;
            }
        }return aux2;
    }
    public Armor buscarArmaduraLeida(String leido){
        boolean encontrado=false;
        Armor aux=null;
        Armor aux2=null;
        while ((!getMyArmor().isEmpty())||(encontrado)) {
            aux=getMyArmor().remove();
            if (leido.equals(aux.getId())){
                encontrado=true;
                aux2=aux;
            }
        }return aux2;
    }
    public void setWeapon1 (Weapon arma1){
      this.arma1=arma1;
      this.arma1.elegida=true;
    }
    public Weapon getArma1(){
        return this.arma1;
    }
    public Weapon getArma2(){
        return this.arma2;
    }
    public Armor getArmadura(){
        return this.armadura;
    }
    public void setArmor (Armor armadura){
        this.armadura=armadura;
    }
    public void setWeapon2 (Weapon arma2){
        this.arma2=arma2;
        this.arma2.elegida=true;
    }
    public void changeSpecialSkill(Specialskill nuevo){
        this.specialskill=nuevo;
    }

    public PendingChallenges getPendingChallenges() {
        return pendingChallenges;
    }

    public String getName() {
        return name;
    }

    public String [] generateFighterState(){
        String hija =null;
        if (this instanceof Vampire){
            hija="Vampiro";
        } else if (this instanceof Lycanthrope) {
            hija="Licantropo";
        } else if (this instanceof Hunter) {
            hija = "Hunter";
        }
        String [] text ={"Nombre del luchador: "+this.getName(),"Oro del luchador: "+Integer.toString(this.getGold()),"Raza: "+hija
        ,"Tipo: "+type.getName()};
        return text;
    }

    public boolean hasActiveEquipment() {
        return (this.armadura != null && this.arma1 != null);
    }
}
