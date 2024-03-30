package combate2000lasecuela.CosasDeLuchador;
import combate2000lasecuela.Combat;
import combate2000lasecuela.PendingChallenges;
import java.io.Serializable;
import java.util.*;
import java.lang.Integer;
import java.lang.String;
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
        this.health = vidaAleatoria();
        this.type = type;
        this.myMinions=myMinions;
        this.myArmor= myArmor;
        this.myWeapon = myWeapon;
        this.minionHealth= calcularVidaMinions();
        this.pendingChallenges = new PendingChallenges();
        equiparPredefinidoArma();
        this.arma2=null;
        equiparPredefinidoArmadura();
        this.gold=100;
        this.specialskill=verHabilidad();
    }
/*---------------------------FUNCIONES PRINCIPALES--------------------------------*/
    public Combat startFighting (Fighter challenger, int oroApostado){
        int rounds=0;
        int pA=0;
        int pD=0;
        do {
            rounds++; //donde recibe el desafiado
                pA = potencialAtaque(challenger);
                pD = defensePotential(this);
                    if (comprobarDaños(pA,pD)){
                        ajusteHabilidad(pA,pD);
                        if (this.minionHealth>0){
                          //  terminal.show(" aunque lo han acabado recibiendo los esbirros");
                           this.minionHealth-=1;
                        }else {
                            this.health -= 1; //considerar caso de que se maten a la vez
                        }
                    }
                pA = potencialAtaque(this);
                pD = defensePotential(challenger);
                if (comprobarDaños(pA,pD)){
                    ajusteHabilidad(pA,pD);
                    if (challenger.minionHealth>0){
                        challenger.minionHealth-=1;
                    }else {
                        challenger.health -= 1;
                    }
                }
        }while((this.health>0)||(challenger.health>0));
        return new Combat(challenger, this, rounds, oroApostado);
    }


    public void elegirArma(LinkedList<Weapon> myWeapon,String leido){
        setWeapon1(buscarArmaLeida(leido));
        if (getArma1()==null){
            elegirArma(myWeapon,leido);
        }else {//si es valor valido
            if (getArma1().isOneHand) {
                if ("SI".equals(leido.toUpperCase())){
                    leido = "scanner.nextLine()";
                    Weapon temporal = (buscarArmaLeida(leido));
                    if (!temporal.isOneHand && !temporal.elegida) { //anti buggs (que tengas equipada la misma arma dos veces)
                    } else {
                        setWeapon2(temporal);
                    }
                }
            }
        }
    }

    public String [] generateWeaponsText() {
        ArrayList<String> weapontext=new ArrayList<>();
        int i =1;
        for (Weapon element: myWeapon){
            if (element.equals(arma1)){
                weapontext.add("E Arma 1 "+Integer.toString(i) +". "+element.getName()+" Ataque: "+Integer.toString(element.getAttack())+" "+element.handConverter());
            }else if (element.equals(arma2)){

                weapontext.add("E Arma 2 "+Integer.toString(i) +". "+element.getName()+" Ataque: "+Integer.toString(element.getAttack())+" "+element.handConverter());
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
            if (element.equals(armor)){
                armortext.add("E "+Integer.toString(i) +". "+element.getName()+" Ataque: "+Integer.toString(element.getAttack())+" Defensa: "+(element.getDefense()));
            }else{
                armortext.add(Integer.toString(i) +". "+element.getName()+" Ataque: "+Integer.toString(element.getAttack())+" Defensa: "+(element.getDefense()));
            }

            i++;
        }
        return armortext.toArray(new String[armortext.size()]);
    }

    public String [] generateMinionText() {
        ArrayList<String> miniontext = new ArrayList<>();
        int i =1;
        for (Minion element: getMyMinions()){
            if (element instanceof Ghoul) {
                miniontext.add(element.getId() + ". " + element.getName() + " Tipo: " + element.getTipo() + " Dependencia: " + ((Ghoul) element).getLealtad() + " Salud: " + element.getHealth());
            } else if (element instanceof Human) {
                miniontext.add(element.getId() + ". " + element.getName() + " Tipo: " + element.getTipo() + " Lealtad: " + ((Human) element).getLealtad() + " Salud: " + element.getHealth());
            } else {
                miniontext.add(element.getId() + ". " + element.getName() + " Tipo: " + element.getTipo() + " Pacto: " + ((Demon) element).getPact() + " Salud: " + element.getHealth());
            }
            i++;
        }
        return miniontext.toArray(new String[miniontext.size()]);
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
            String [] text ={"Nombre del luchador: "+this.getName(),"Oro del luchador: "+Integer.toString(this.getGold()),"Raza: "+ subtype
                    ,"Tipo: "+type.getName(),"Arma 1: "+arma1.getName(),"Arma 2: No tienes un segundo arma activado","Armadura: "+armor.getName(),"Esbirros: "};
            textbuilder.addAll(Arrays.asList(text));
            textbuilder.addAll(Arrays.asList(generateMinionText()));
            return textbuilder.toArray(new String[textbuilder.size()]);


        }
        String [] text ={"Nombre del luchador: "+this.getName(),"Oro del luchador: "+Integer.toString(this.getGold()),"Raza: "+ subtype
        ,"Tipo: "+type.getName(),"Arma 1: "+arma1.getName(),"Arma 2: "+arma2.getName(),"Armadura: "+armor.getName(),"Esbirros"};
        textbuilder.addAll(Arrays.asList(text));
        textbuilder.addAll(Arrays.asList(generateMinionText()));
        return textbuilder.toArray(new String[textbuilder.size()]);
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
    /*---------------------------FUNCIONES MEDIANAS---------------------------------------*/
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
    public Stack<Minion> copiaSegura(){
        Stack<Minion> aux= new Stack<Minion>();
        for (Minion minion: getMyMinion()){
            aux.push(minion);
        }
        return aux;
    }
    public int calcularVidaMinions(){
        Stack<Minion> aux= getMyMinions();
        Stack<Minion> copia=copiaSegura();
        if (aux == null){
            return 0;
        }else {
            int total = 0;

            while (!aux.isEmpty()) {
                Minion slave = aux.pop();
                if (slave != null) {
                    total += slave.getHealth();
                }
            }
            setMyMinions(copia);
            return total;
        }
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
    public void elegirArmadura (LinkedList<Armor> myArmor, Integer opcion){
        String leido = opcion.toString();
        setArmor(buscarArmaduraLeida(leido));
        if (getArmor()==null){
            elegirArmadura(myArmor,opcion);
        }
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
    /*---------------------------FUNCIONES PEQUEÑAS---------------------------------------*/
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
