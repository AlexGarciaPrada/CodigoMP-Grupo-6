package combate2000lasecuela.CosasDeLuchador;
import combate2000lasecuela.Combat;
import combate2000lasecuela.PendingChallenges;
import java.io.Serializable;
import java.util.*;
import java.lang.Integer;
import java.lang.String;

public abstract class Fighter implements Serializable {
    private String name;
    private int gold;
    private int health;
    private int power;
    private Stack<Minion> myMinions;
    private LinkedList <Armor> myArmor;
    private LinkedList<Weapon> myWeapon;
    private TFighter type;
    private int minionHealth;
    private Weapon arma1;
    private Weapon arma2;
    private Armor armor;
    private PendingChallenges pendingChallenges;
    private Specialskill specialskill;
    private ArrayList<String [] > mailbox ;
    ArrayList<String> textoBatalla = new ArrayList<>();


    public Fighter(String name, TFighter type,
        Stack<Minion> myMinions,LinkedList<Armor> myArmor,
        LinkedList<Weapon> myWeapon) {
        this.name = name;
        this.health = vidaAleatoria();
        this.type = type;
        this.myMinions=myMinions;
        this.myArmor= myArmor;
        this.myWeapon = myWeapon;
        this.minionHealth= calculateMinionHealth();
        this.pendingChallenges = new PendingChallenges();
        equiparPredefinidoArma();
        this.arma2=null;
        equiparPredefinidoArmadura();
        this.gold=100;
        this.specialskill= setAbility();
        this.mailbox = new ArrayList<>();
    }
/*---------------------------FUNCIONES PRINCIPALES--------------------------------*/
    public Combat startFighting (Fighter challenger, int oroApostado){
        int vidaDesafiado= this.getHealth();
        int vidaDesafiante=challenger.getHealth();
        int vidaMinionsDesafiado=this.minionHealth;
        int vidaMinionsDesafiante=challenger.minionHealth;
        int rounds=0;
        int pAA;
        int pDD;
        int pAD;
        int pDA;
        while((this.health>0)&&(challenger.health>0)){
            rounds++; //donde recibe el desafiado
                pAA = attackPotential(challenger);
                pDD = defensePotential(this);
                    if (comprobarDaños(pAA,pDD)){
                        ajusteHabilidad(pAA,pDD);
                        if (this.minionHealth>0){
                           this.minionHealth-=1;
                            estadoBatalla(rounds,this,true,textoBatalla,false);
                        }else {
                            this.health -= 1;
                            estadoBatalla(rounds,this,false,textoBatalla,false);
                        }
                    }else{
                        estadoBatalla(rounds,this,false,textoBatalla,true);
                    }
                pAD = attackPotential(this);
                pDA = defensePotential(challenger);
                if (comprobarDaños(pAD,pDA)){
                    ajusteHabilidad(pAD,pDA);
                    if (challenger.minionHealth>0){
                        challenger.minionHealth-=1;
                        estadoBatalla(rounds,challenger,true,textoBatalla,false);
                    }else {
                        challenger.health -= 1;
                        estadoBatalla(rounds,challenger,false,textoBatalla,false);
                    }
                }else{
                    estadoBatalla(rounds,challenger,false,textoBatalla,true);

                }
        }
        boolean desafiadoEsGanador = this.health>challenger.health;
        this.setHealth(vidaDesafiado);
        challenger.setHealth(vidaDesafiante);
        this.setMinionsHealth(vidaMinionsDesafiado);
        challenger.setMinionsHealth(vidaMinionsDesafiante);
        return new Combat(challenger, this, rounds, oroApostado,desafiadoEsGanador);
    }
    public String [] generateWeaponsText() {
        ArrayList<String> weapontext=new ArrayList<>();
        int i =1;
        for (Weapon element: myWeapon){
            if (element != null && element.getId().equals(arma1.getId())){
                weapontext.add("E Arma 1: "+ i +". "+element.getName()+" Ataque: "+Integer.toString(element.getAttack())+" "+element.handConverter());
            }else if (element != null && element.equals(arma2)){
                weapontext.add("E Arma 2: "+ i +". "+element.getName()+" Ataque: "+Integer.toString(element.getAttack())+" "+element.handConverter());
            }else if (element != null){
                weapontext.add(i +". "+element.getName()+" Ataque: "+Integer.toString(element.getAttack())+" "+element.handConverter());
            }

            i++;
        }
        return weapontext.toArray(new String[weapontext.size()]);
    }

    public String [] generateArmorText() {
        ArrayList<String> armortext=new ArrayList<>();
        int i =1;
        for (Armor element: myArmor){
            if (element != null && element.getId().equals(armor.getId())){
                armortext.add("E "+Integer.toString(i) +". "+element.getName()+" Ataque: "+Integer.toString(element.getAttack())+" Defensa: "+(element.getDefense()));
            }else if (element != null){
                armortext.add(Integer.toString(i) +". "+element.getName()+" Ataque: "+Integer.toString(element.getAttack())+" Defensa: "+(element.getDefense()));
            }

            i++;
        }
        return armortext.toArray(new String[armortext.size()]);
    }

    public void addMinionText (ArrayList miniontext, int i, Minion minion){
        miniontext.add(minion.getId() + ". " + minion.getName() + " Tipo: " + minion.getTipo() + " "  + minion.getSpecialSkillName() + ":" + minion.getSpecialSkill()+ " Salud: " + minion.getHealth());
    }
    public String [] generateMinionText(Stack<Minion> mmins) {
        ArrayList<String> miniontext = new ArrayList<>();
        int i =1;
        for (Minion element: mmins){
            if (element != null) {
                addMinionText(miniontext,i,element);
            }
            if (element instanceof Demon) {
                Demon demon = (Demon) element;
                Stack<Minion> mins = demon.getDemonStack();
                if (mins != null) {
                    String [] minss = generateMinionText(mins);
                    for (String text: minss) {
                        miniontext.add(text);
                    }
                }
            }
            i++;
        }
        return miniontext.toArray(new String[miniontext.size()]);
    }


    public void estadoBatalla(int ronda, Fighter f,boolean impactoAmortiguado,ArrayList<String> textoBatalla,boolean esEmpate){
        String nombre;
        String aux;
        if (!esEmpate){
            if (f==this){
                nombre=this.name;
            }else {
                nombre = f.name;
            }
            if (impactoAmortiguado){
                aux= " El golpe se lo llevaron los esbirros";
            }else{
                aux= " El impacto lo sufrio el personaje";
            }
             String[] texto= {"Es la ronda "+ ronda+ " el luchador: "+ nombre + " ha recibido un golpe"+ aux+
                " a los esbirros les queda "+ f.getMinionHealth()+
                " de vida en total y al personaje "+ f.getHealth()+ " vidas"};
             textoBatalla.addAll(Arrays.asList(texto));
        }else{
            String[] texto = {"Es la ronda "+ronda+ " y se ha producido un empate tecnico"};
            textoBatalla.addAll(Arrays.asList(texto));

        }


    }
    public String[] publicarTocho(){
        return this.textoBatalla.toArray(new String[this.textoBatalla.size()]);
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
        ArrayList <String> textbuilder = new ArrayList<>();
            if (arma2 ==null){
                String [] text ={"Nombre del luchador: "+this.getName(),"Vida: "+Integer.toString(health),"Oro del luchador: "+Integer.toString(this.getGold()),"Raza: "+ subtype
                    ,"Tipo: "+type.getName(),"Arma 1: "+arma1.getName(),"Arma 2: No tienes un segundo arma activado","Armadura: "+armor.getName(),"Esbirros: "};

            textbuilder.addAll(Arrays.asList(text));
            textbuilder.addAll(Arrays.asList(generateMinionText(getMyMinions())));
            return textbuilder.toArray(new String[textbuilder.size()]);


        }
        String [] text ={"Nombre del luchador: "+this.getName(),"Vida: "+Integer.toString(health),"Oro del luchador: "+Integer.toString(this.getGold()),"Raza: "+ subtype
        ,"Tipo: "+type.getName(),"Arma 1: "+arma1.getName(),"Arma 2: "+arma2.getName(),"Armadura: "+armor.getName(),"Esbirros"};

        textbuilder.addAll(Arrays.asList(text));
        textbuilder.addAll(Arrays.asList(generateMinionText(getMyMinions())));
        return textbuilder.toArray(new String[textbuilder.size()]);
    }

    /*---------------------------FUNCIONES MEDIANAS---------------------------------------*/
    public Specialskill setAbility(){
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
    public int calculateMinionHealth(){
        if (myMinions == null){
            return 0;
        }else {
            int total = 0;
            for (Minion minion:myMinions){
                total+=minion.getHealth();
            }
            return total;
    }}
    public int checkSuccess(int potential){
        Random random = new Random();
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

    /*---------------------------FUNCIONES PEQUEÑAS---------------------------------------*/
    public int attackPotential(Fighter f){
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

    private int vidaAleatoria(){
        Random random = new Random();
        return random.nextInt(5)+1;
    }
    private boolean comprobarDaños(int pA, int pD){
        return (pA>pD);
    }
    public abstract void ajusteHabilidad(int pA, int pD);
    public abstract int SpecialAttack();
    public boolean hasActiveEquipment() {
        return (this.armor != null && this.arma1 != null);
    }
    public void equiparPredefinidoArmadura(){
        armor= myArmor.get(0);
    }
    public void equiparPredefinidoArma(){
        arma1 = myWeapon.get(0);
    }

    /*--------------------------- GETTERS Y SETTERS---------------------------------------*/
    public void setWeapon1 (Weapon arma1){
        this.arma1=arma1;
        this.arma1.setEquipped1(true);
    }
    public void setMinionsHealth(int vida){
        this.minionHealth=vida;
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
    public Stack<Minion> getMyMinion(){
        return this.myMinions;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public void setMyMinions(Stack<Minion> a){
        this.myMinions=a;
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
        return this.myMinions;
    }

    public TFighter getType() {
        return type;
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
        armor.setEquipped(true);
    }
    public void setWeapon2 (Weapon arma2){
        this.arma2=arma2;
        this.arma2.setEquipped2(true);
    }
    public void addMail(String [] element){
        mailbox.add(element);
    }
    public String [] getMail(){
       String [] text =mailbox.get(0);
       mailbox.remove(0);
       return text;
    }
    public boolean isMailboxEmpty(){
        return mailbox.isEmpty();
    }

    public LinkedList<Weapon> getMyWeapon(){
        return this.myWeapon;
    }

    public LinkedList<Armor> getMyArmor(){
        return this.myArmor;
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
