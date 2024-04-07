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
    private int pendingGold;
    private int health;
    private int power;
    private LinkedList<Minion> myMinions;
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
    ArrayList<String> battleText = new ArrayList<>();


    public Fighter(String name, TFighter type,
        LinkedList<Minion> myMinions,LinkedList<Armor> myArmor,
        LinkedList<Weapon> myWeapon) {
        this.name = name;
        this.health = randomHealth();
        this.type = type;
        this.myMinions=myMinions;
        this.myArmor= myArmor;
        this.myWeapon = myWeapon;
        this.minionHealth= calculateMinionHealth();
        this.pendingChallenges = new PendingChallenges();
        equipDefaultWeapon();
        this.arma2=null;
        equipDefaultArmor();
        this.gold=100;
        this.pendingGold=100;
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
                    if (checkDamage(pAA,pDD)){
                        adjustAbility(pAA,pDD);
                        if (this.minionHealth>0){
                           this.minionHealth-=1;
                            estadoBatalla(rounds,this,true, battleText,false);
                        }else {
                            this.health -= 1;
                            estadoBatalla(rounds,this,false, battleText,false);
                        }
                    }else{
                        estadoBatalla(rounds,this,false, battleText,true);
                    }
                pAD = attackPotential(this);
                pDA = defensePotential(challenger);
                if (checkDamage(pAD,pDA)){
                    adjustAbility(pAD,pDA);
                    if (challenger.minionHealth>0){
                        challenger.minionHealth-=1;
                        estadoBatalla(rounds,challenger,true, battleText,false);
                    }else {
                        challenger.health -= 1;
                        estadoBatalla(rounds,challenger,false, battleText,false);
                    }
                }else{
                    estadoBatalla(rounds,challenger,false, battleText,true);

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

    public void addMinionText (ArrayList miniontext, Minion minion){
        miniontext.add("Id: "+ minion.getId() + ". " + minion.getName() + " Tipo: " + minion.getTipo() + " "  + minion.getSpecialSkillName() + ":" + minion.getSpecialSkill()+ " Salud: " + minion.getHealth());
    }
    public String [] generateMinionText(LinkedList<Minion> mins) {
        ArrayList<String> miniontext = new ArrayList<>();
        int i =1;
        for (Minion element: mins){
            if (element != null) {
                addMinionText(miniontext,element);
            }
            if (element instanceof Demon) {
                Demon demon = (Demon) element;
                LinkedList<Minion> mmins = demon.getDemonList();
                if (mmins != null) {
                    String [] minss = generateMinionText(mmins);
                    for (String text: minss) {
                        miniontext.add(text);
                    }
                }
            }
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

    public String[] publishText(){
        return this.battleText.toArray(new String[this.battleText.size()]);
    }
    public String [] generateFighterState(){
        ArrayList <String> textbuilder = new ArrayList<>();
        String [] text = this.fighterToString();
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
        int value = 0;
        if (myMinions == null){
            return 0;
        }else {
            int total = 0;
            for (Minion minion : myMinions) {
                if (minion != null) {
                    total += minion.getHealth();
                    value = total;
                }
            }
        } return value;
    }
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

    private int randomHealth(){
        Random random = new Random();
        return random.nextInt(5)+1;
    }
    private boolean checkDamage(int pA, int pD){
        return (pA>pD);
    }
    public abstract void adjustAbility(int pA, int pD);
    public abstract int SpecialAttack();
    public boolean hasActiveEquipment() {
        return (this.armor != null && this.arma1 != null);
    }
    public void equipDefaultArmor(){
        armor= myArmor.get(0);
    }
    public void equipDefaultWeapon(){
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
    public LinkedList<Minion> getMyMinion(){
        return this.myMinions;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public void setMyMinions(LinkedList<Minion> a){
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

    public LinkedList<Minion> getMyMinions() {
        return this.myMinions;
    }

    public TFighter getType() {
        return type;
    }
    public int getMinionHealth() {
        return minionHealth;
    }
    public void setType(TFighter type) {
        this.type = type;
    }

    public void setPendingGold(int pendingGold) {
        this.pendingGold = pendingGold;
    }

    public Weapon getArma1(){
        return this.arma1;
    }

    public int getPendingGold() {
        return pendingGold;
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
        this.arma2.setEquipped2(true);
    }
    public void addMail(String [] element){
        mailbox.add(element);
    }
    public String [] getMail(){
       String [] text =mailbox.get(0);
       return text;
    }
    public void eraseMail(){
        mailbox.removeFirst();
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
    public boolean getEquipped2(){
        if (arma2!=null){
            return false;
        }
        return false;
    }

    public String[] fighterToString(){
        return new String[]{"Nombre del luchador: " + this.getName(),
                "Vida: " + Integer.toString(health),
                "Oro del luchador: " + Integer.toString(this.getGold()),
                "Raza: " + this.getClass().getSimpleName(),
                "Tipo: " + type.getName(),
                "Arma 1: " + arma1.getName(),
                displayIfArmor2(),
                "Armadura: " + armor.getName(),
                "Esbirros: "};
    }

    public String displayIfArmor2(){
        if (this.arma2 != null){
            return "Arma 2: "+arma2.getName();
        }
        return "Arma 2: No equipada";
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
