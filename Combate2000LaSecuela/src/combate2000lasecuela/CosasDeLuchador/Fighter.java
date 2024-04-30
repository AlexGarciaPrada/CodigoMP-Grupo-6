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
    private ArrayList<String> battleText = new ArrayList<>();


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
        int challengedHealth= this.getHealth();
        int challengerHealth=challenger.getHealth();
        int challengedMinionHealth=this.minionHealth;
        int challengerMinionHealth=challenger.minionHealth;
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
                            battleState(rounds,this,true, battleText,false);
                        }else {
                            this.health -= 1;
                            battleState(rounds,this,false, battleText,false);
                        }
                    }else{
                        battleState(rounds,this,false, battleText,true);
                    }
                pAD = attackPotential(this);
                pDA = defensePotential(challenger);
                if (checkDamage(pAD,pDA)){
                    adjustAbility(pAD,pDA);
                    if (challenger.minionHealth>0){
                        challenger.minionHealth-=1;
                        battleState(rounds,challenger,true, battleText,false);
                    }else {
                        challenger.health -= 1;
                        battleState(rounds,challenger,false, battleText,false);
                    }
                }else{
                    battleState(rounds,challenger,false, battleText,true);

                }
        }
        boolean desafiadoEsGanador = this.health>challenger.health;
        this.setHealth(challengedHealth);
        challenger.setHealth(challengerHealth);
        this.setMinionsHealth(challengedMinionHealth);
        challenger.setMinionsHealth(challengerMinionHealth);
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
        miniontext.add(minion.getName() + " Tipo: " + minion.getType() + " "  + minion.getSpecialSkillName() + ":" + minion.getSpecialSkill()+ " Salud: " + minion.getHealth());
    }
    public String [] generateMinionText() {
        ArrayList<String> miniontext = new ArrayList<>();
        for (Minion element: myMinions){
            if (element != null) {
                addMinionText(miniontext,element);
                if (element instanceof Demon) {
                    Demon demon = (Demon) element;
                    String [] demontext = demon.getDemonListText();
                    for (String text: demontext) {
                        miniontext.add(text);
                    }
                }
            }
        }
        return miniontext.toArray(new String[miniontext.size()]);
    }

    public String [] getAllMinionText () {
        ArrayList<String> allText = new ArrayList<>();
        int i = 1;
        String [] minionText = generateMinionText();
        for (String text: minionText) {
            if (!text.startsWith("Esbirros de") && !text.equals("No tiene")) {
                allText.add(i + ". " + text);
                i++;
            } else {
                allText.add(text);
            }
        } return allText.toArray(new String[allText.size()]);
    }

    public LinkedList<Minion> getAllMinionsList(LinkedList<Minion> mins) {
        LinkedList<Minion> allList = new LinkedList<>();
        for (Minion minion: mins) {
            allList.add(minion);
            if (minion instanceof  Demon) {
                if (((Demon) minion).getDemonList() != null) {
                    LinkedList<Minion> subList = getAllMinionsList(((Demon) minion).getDemonList());
                    allList.addAll(subList);
                }
            }
        } return allList;
    }

    public void battleState(int round, Fighter f, boolean impactCushioned, ArrayList<String> textoBatalla, boolean esEmpate){
        String name;
        String aux;
        if (!esEmpate){
            if (f==this){
                name=this.name;
            }else {
                name = f.name;
            }
            if (impactCushioned){
                aux= " El golpe se lo llevaron los esbirros";
            }else{
                aux= " El impacto lo sufrio el personaje";
            }
             String[] texto= {"Es la ronda "+ round+ " el luchador: "+ name + " ha recibido un golpe"+ aux+
                " a los esbirros les queda "+ f.getMinionHealth()+
                " de vida en total y al personaje "+ f.getHealth()+ " vidas"};
             textoBatalla.addAll(Arrays.asList(texto));
        }else{
            String[] texto = {"Es la ronda "+round+ " y se ha producido un empate tecnico"};
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
        textbuilder.add("Esbirros: ");
        textbuilder.addAll(Arrays.asList(generateMinionText()));
        return textbuilder.toArray(new String[textbuilder.size()]);
    }
    public String [] generateFighterForOperatorState(){
        ArrayList <String> textbuilder = new ArrayList<>();
        String [] text = this.fighterToString();
        textbuilder.addAll(Arrays.asList(text));
        textbuilder.add("Vida Minions: "+Integer.toString(minionHealth));
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
        int potential=f.armor.getDefense()+ f.specialskill.getDamage()+SpecialAttack();
        //porque la implementación de ambos sería idéntica.
        return checkSuccess(potential);
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
        int i=0;
        Armor aux = null;
        while ((aux==null)&&(i<myArmor.toArray().length)) {
            aux = myArmor.get(i);
            i++;
        }
        if (aux!=null) {
            setArmor(aux);
        }else{//caso absurdamente improbable
            Armor armor = new Armor("3; ARMADURA DE COBRE COMÚN; 1; 0;");
            myArmor.add(armor);
            setArmor(armor);
        }
    }
    public void equipDefaultWeapon(){
        int i=0;
        Weapon aux = null;
        while ((aux==null)&&(i<myWeapon.toArray().length)) {
            aux = myWeapon.get(i);
            i++;
        }
        if (aux!=null) {
            setWeapon1(aux);
        }else{//caso absurdamente improbable
            Weapon armor = new Weapon("9; HACHA ROMA GIGANTE; 1; 2;");
            myWeapon.add(armor);
            setWeapon1(armor);
        }
    }

    /*--------------------------- GETTERS Y SETTERS---------------------------------------*/
    public void setWeapon1 (Weapon arma1){
        this.arma1=arma1;
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
    }
    public void addMail(String [] element){
        mailbox.add(element);
    }
    public String [] getMail(){
       String [] text =mailbox.get(0);
       return text;
    }
    public void eraseMail(){
        if (this.isMailboxEmpty()){
            return;
        }
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
                "Armadura: " + armor.getName()};
    }

    public String displayIfArmor2(){
        if (this.arma2 != null){
            return "Arma 2: "+arma2.getName();
        }
        return "Arma 2: No equipada";
    }

    public ArrayList<String[]> getMailbox() {
        return mailbox;
    }
}

