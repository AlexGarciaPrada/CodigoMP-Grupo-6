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

import static combate2000lasecuela.Constants.armorSeparator;
import static combate2000lasecuela.Constants.weaponSeparator;

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
    private Armor armor;

    private PendingChallenges pendingChallenges;
    Specialskill specialskill;

    public Fighter(String name, TFighter type,
        Stack<Minion> myMinions,LinkedList<Armor> myArmor,
        LinkedList<Weapon> myWeapon) {
        this.name = name;
        this.health = random.nextInt(5) + 1;
        //this.power = random.nextInt(5)+1;
        // this.power = (int) (Math.random()+1);
        //bound es 5
        this.type = type;
        this.myMinions = myMinions;
        this.myArmor= myArmor;
        this.myWeapon = myWeapon;
        this.minionHealth= calcularVidaMinions();
        this.pendingChallenges = new PendingChallenges();
        this.arma1=equiparPredefinidoArma();
        this.arma2=null;
        this.armor =equiparPredefinidoArmadura();
        this.gold=100;
        this.specialskill=verHabilidad();
    }

    public Specialskill verHabilidad(){
        if (this instanceof Lycanthrope){
            Gift gift = new Gift();
            return gift;
        }else if (this instanceof  Vampire){
            Discipline discipline = new Discipline();
            return discipline;
        }else{
            Talent talent = new Talent();
            return talent;
        }
    }
/*
    public Combat startFighting (Fighter desafiante, int oroApostado){
        fightEachPlayer(desafiante,this,oroApostado);
        fightEachPlayer(this,desafiante,oroApostado);
        return new Combat(desafiante,this,0,oroApostado);
    }

    public void fightEachPlayer(Fighter desafiante, Fighter challenged, int oroApostado){
        int rounds=0;
        int pA=0;
        int pD=0;
        //boolean esEmpate=false;//preparativo para meterselo al combat
        do {
            rounds++; //donde recibe el desafiado
            //terminal.show("Ronda numero" + rounds + "comienza");
            pA = potencialAtaque(desafiante);
            pD = potencialDefensa(challenged);
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
        }while((this.health>0)||(desafiante.health>0));
    }
*/

    public Combat startFighting (Fighter challenger, int oroApostado){
        int rounds=0;
        int pA=0;
        int pD=0;
        //boolean esEmpate=false;//preparativo para meterselo al combat
        do {
            rounds++; //donde recibe el desafiado
            //terminal.show("Ronda numero" + rounds + "comienza");
                pA = potencialAtaque(challenger);
                pD = defensePotential(this);
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
            //donde recibe el challenger
            //aunque esté separado, físicamente, ocurre de manera simultánea
                pA = potencialAtaque(this);
                pD = defensePotential(challenger);
                if (comprobarDaños(pA,pD)){
                    ajusteHabilidad(pA,pD);
                  //  terminal.show(challenger.name+" ha recibido un golpe");
                    if (challenger.minionHealth>0){
                       // terminal.show(" aunque lo han acabado recibiendo los esbirros");
                        challenger.minionHealth-=1;
                    }else {
                        challenger.health -= 1;
                      //  terminal.show (challenger.health+" vidas restantes");
                    }
                }
        }while((this.health>0)||(challenger.health>0));
        return new Combat(challenger, this, rounds, oroApostado);
    }

    public int potencialAtaque (Fighter f){
        int potential=f.power+f.arma1.getAttack()+f.armor.getAttack()+ f.specialskill.getDamage()+SpecialAttack();
        if (f.arma2!=null){
            potential+=f.arma2.getAttack();
        }
        return checkSuccess(potential);
    }
    public int defensePotential(Fighter f){
        int potencial=f.armor.getDefense()+ f.specialskill.getDamage()+SpecialAttack();
        //porque la implementación de ambos sería idéntica.
            return checkSuccess(potencial);
    }
    public int checkSuccess(int potential){
        int acierto=0;
        int aux;
        for (int i=0; i<potential;i++){
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
                Minion slave = this.myMinions.pop();
                if (slave != null) {
                    total += slave.getHealth();
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
        if (getArmor()==null){
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
            if (element.isEquipped()){
                weapontext.add("E "+Integer.toString(i) +". "+element.getName()+" Ataque: "+Integer.toString(element.getAttack())+" "+element.handConverter());
            }else{
                weapontext.add(Integer.toString(i) +". "+element.getName()+" Ataque: "+Integer.toString(element.getAttack())+" "+element.handConverter());
            }

            i++;
        }
        return weapontext.toArray(new String[weapontext.size()]);
    }
    public String [] generateArmorText() {
        ArrayList<String> armortext=new ArrayList<>();
        int i =1;
        for (Armor element: myArmor){
            if (element.isEquipped()){
                armortext.add("E "+Integer.toString(i+ generateWeaponsText().length) +". "+element.getName()+" Ataque: "+Integer.toString(element.getAttack())+" Defensa: "+(element.getDefense()));
            }else{
                armortext.add(Integer.toString(i+ generateWeaponsText().length) +". "+element.getName()+" Ataque: "+Integer.toString(element.getAttack())+" Defensa: "+(element.getDefense()));
            }

            i++;
        }
        return armortext.toArray(new String[armortext.size()]);
    }

    public String [] generateMinionText() {
        ArrayList<String> miniontext=new ArrayList<>();
        int i =1;
        for (Minion element: myMinions){
            if (element.isEquipped()) {
                if (element instanceof Ghoul) {
                    miniontext.add("M" + Integer.toString(i) + ". " + element.getName() + "Tipo: " + element.getTipo() + "Dependencia: " + ((Ghoul) element).getLealtad() + "Salud: " + element.getHealth());
                } else if (element instanceof Human) {
                    miniontext.add("M" + Integer.toString(i) + ". " + element.getName() + "Tipo: " + element.getTipo() + "Lealtad: " + ((Human) element).getLealtad() + "Salud: " + element.getHealth());
                } else {
                    miniontext.add("M" + Integer.toString(i) + ". " + element.getName() + "Tipo: " + element.getTipo() + "Pacto: " + ((Demon) element).getPact() + "Salud: " + element.getHealth());
                }
            } else {
                if (element instanceof Ghoul) {
                    miniontext.add(Integer.toString(i) + ". " + element.getName() + "Tipo: " + element.getTipo() + "Dependencia: " + ((Ghoul) element).getLealtad() + "Salud: " + element.getHealth());
                } else if (element instanceof Human) {
                    miniontext.add(Integer.toString(i) + ". " + element.getName() + "Tipo: " + element.getTipo() + "Lealtad: " + ((Human) element).getLealtad() + "Salud: " + element.getHealth());
                } else {
                    miniontext.add(Integer.toString(i) + ". " + element.getName() + "Tipo: " + element.getTipo() + "Pacto: " + ((Demon) element).getPact() + "Salud: " + element.getHealth());
                }
            }
            i++;
        }
        return miniontext.toArray(new String[miniontext.size()]);
    }

    public Armor equiparPredefinidoArmadura(){
        return this.myArmor.remove(0);
    }
    public Weapon equiparPredefinidoArma(){
        return this.myWeapon.remove(0);
    }

    public void mostrarArmaduras(){
        do {
            //terminal.show(getMyWeapon().remove().toString());
        } while(!getMyWeapon().isEmpty());
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
    public String [] generateFighterState(){
        String subtype =null;
        if (this instanceof Vampire){
            subtype ="Vampiro";
        } else if (this instanceof Lycanthrope) {
            subtype ="Licantropo";
        } else if (this instanceof Hunter) {
            subtype = "Cazador";
        }
        String [] text ={"Nombre del luchador: "+this.getName(),"Oro del luchador: "+Integer.toString(this.getGold()),"Raza: "+ subtype
        ,"Tipo: "+type.getName()};
        return text;
    }
    public boolean hasActiveEquipment() {
        return (this.armor != null && this.arma1 != null);
    }
    public String[] generateEquipment(){
        String [] equipment = new String [generateWeaponsText().length+ generateArmorText().length+2];
        equipment [0] = weaponSeparator;
        for (int i=0;i<generateWeaponsText().length;i++){
            equipment[i+1]=generateWeaponsText()[i];
        }
        equipment [generateWeaponsText().length+1] = armorSeparator;
        for(int i =0 ;i< generateArmorText().length;i++){
            equipment[generateWeaponsText().length+2+i]=generateArmorText()[i];
        }
        return equipment;
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

    public int getPower() {
        return power;
    }

    public Stack<Minion> getMyMinions() {
        return myMinions;
    }

    public TFighter getType() {
        return type;
    }

    public Random getRandom() {
        return random;
    }

    public int getMinionHealth() {
        return minionHealth;
    }

    public Specialskill getSpecialskill() {
        return specialskill;
    }

    public void setType(TFighter type) {
        this.type = type;
    }

    public Weapon getArma1(){
        return this.arma1;
    }
    public Weapon getArma2(){
        return this.arma2;
    }
    public Armor getArmor(){
        return this.armor;
    }
    public void setArmor (Armor armadura){
        this.armor =armadura;
    }
    public void setWeapon2 (Weapon arma2){
        this.arma2=arma2;
        this.arma2.elegida=true;
    }
    public LinkedList<Weapon> getMyWeapon(){
        return this.myWeapon;
    }

    public LinkedList<Armor> getMyArmor(){
        return this.myArmor;
    }
}

