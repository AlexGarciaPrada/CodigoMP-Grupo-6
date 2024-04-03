package combate2000lasecuela.managers;

import combate2000lasecuela.*;
import combate2000lasecuela.CosasDeLuchador.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static combate2000lasecuela.Constants.*;

public class Database {
    private UserManager usermanager;
    private Loader loader;
    private ChallengeManager challengeManager;
    private CombatRegister combatregister;

    private MinionManager minionManager;
    private ItemManager itemManager;
    private ModifierManager modifierManager;


    public Database() {
        this.usermanager = new UserManager();
        this.loader = new Loader();
        this.challengeManager = new ChallengeManager();
        this.combatregister = new CombatRegister();
        this.minionManager = new MinionManager();
        this.itemManager = new ItemManager();
    }

    public void addFighter(Player player, Fighter fighter) {
        player.createFighter(fighter);
        updateUsers();
    }

    public void eraseFighter(Player player) {
        player.deleteFighter();
        updateUsers();
    }

    public void updateUsers() {
        usermanager.saveCollection("User");
    }

    public void updateChallenges() {
        challengeManager.saveCollection("Challenge");
    }

    public void updateCombats() {
        combatregister.saveCollection("Combat");
    }
    public void addPendingChallenge(Player challenged, Challenge challenge) {
        challenged.addPendingChallenge(challenge);
        updateUsers();
    }

    public void loadUsers() {
        usermanager.loadElement("User");
    }

    public void addPlayer(Player player) {
        usermanager.addElement("Player", player.getNick(), player);
        updateUsers();
    }

    public boolean isAPlayer(String nick) {
        return usermanager.inMap("Player", nick);
    }

    public void addOperator(Operator operator) {
        usermanager.addElement("Operator", operator.getNick(), operator);
        usermanager.saveCollection("User");
    }

    public void erasePlayer(Player player) {
        usermanager.getElements().get("Player").remove(player.getNick());
        usermanager.saveCollection("User");
    }

    public void eraseOperator(Operator operator) {
        usermanager.getElements().get("Operator").remove(operator.getNick());
        usermanager.saveCollection("User");
    }

    public boolean isNickUsed(String nick) {
        if ((usermanager.inMap("Player", nick)) || (usermanager.inMap("Operator", nick))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPasswordCorrect(String nick, String password) {
        if ((usermanager.inMap("Player", nick))) {
            return (usermanager.getElements().get("Player").get(nick).getPassword().equals(password));
        } else {
            return (usermanager.getElements().get("Operator").get(nick).getPassword().equals(password));
        }
    }

    public User getUser(String nick) {
        if ((usermanager.inMap("Player", nick))) {
            return usermanager.getElements().get("Player").get(nick);
        } else {
            return usermanager.getElements().get("Operator").get(nick);
        }
    }

    private ArrayList<Player> mapToNotOrderedRanking(Map<String, User> map) {
        ArrayList<Player> playerList = new ArrayList<>();
        for (User user : map.values()) {
            playerList.add((Player) user); //Tengo que añadirlos uno a uno para castearlos
        }
        return playerList;
    }

    public ArrayList<String> getRanking() {
        ArrayList<String> ranking = new ArrayList<>();
        ArrayList<Player> playerList = mapToNotOrderedRanking(usermanager.getElements().get("Player"));
        playerList.sort(Comparator.comparing(Player::getVictories).reversed());
        for (Player player : playerList) {
            ranking.add(String.valueOf(playerList.indexOf(player) + 1) + ". " + player.getNick() + " Victorias: " + player.getVictories());
        }
        return ranking;
    }

    public void addVictories(Player player){
        player.setVictories(player.getVictories()+1);
        updateUsers();
    }

    public Stack<Minion> randomMinions(int suerte, boolean esVampiro, int tope) {
        int handicap=10;
        int eleccion;
        Random random = new Random();
        Stack<Minion> myMinions = new Stack<>();
        Minion slave;
        int numero = random.nextInt(loader.getMm().getCollection("MinionMap").size()) + 1;
        if (numero>handicap){
            numero=(numero%handicap)+1+ suerte;
        }
        for (int i = 0; i <= numero; i++) {
            eleccion = random.nextInt(loader.getMm().getElements().get("MinionMap").size());
            slave = loader.getMm().getElements().get("MinionMap").get(Integer.toString(eleccion));
            if (!(esVampiro) || !(slave instanceof Human)) {
                myMinions.push(slave);
                if ((slave instanceof Demon) && (tope <= 3)) { //que no se meta en bucle continuo, capo a los demonios
                    tope += 1;
                    ((Demon) slave).setDemonStack(randomMinionDemon(tope));
                }
            }
        }
        return myMinions;
    }

    public Stack<Minion> randomMinionDemon(int tope) {
        if (tope <= 3) {
            return randomMinions(0, false, tope + 1);
        }
        return null;
    }

    public TFighter getTFighter() {
        return null;
    }

    public LinkedList<Weapon> randomWeapons(int suerte) {
        int handicap=10;
        int eleccion;
        Random random = new Random();
        LinkedList<Weapon> myWeapon = new LinkedList<>();
        Weapon weapon;
        int numero = random.nextInt(28) + 1;
        if (numero>handicap){
            numero=(numero%handicap)+1+ suerte;
        }
        for (int i = 1; i <= numero; i++) {
            eleccion = random.nextInt(loader.getIm().getElements().get("WeaponMap").size());
            weapon = (Weapon) loader.getIm().getElements().get("WeaponMap").get(Integer.toString(eleccion));
            myWeapon.add(weapon);
        }
        return myWeapon;
    }

    public LinkedList<Armor> randomArmor(int suerte) {
        int handicap = 10;
        Random random = new Random();
        int eleccion;
        Armor armor;
        LinkedList<Armor> myArmor = new LinkedList<>();
        int numero = random.nextInt(loader.getIm().getCollection("ArmorMap").size()) + 1 ;
        if (numero>handicap){
            numero=(numero%handicap)+1+suerte; //si alguno no entiende el +1 que me pregunte
        }
        for (int i = 1; i <= numero; i++) {
            eleccion = random.nextInt(loader.getIm().getElements().get("ArmorMap").size());
            armor = (Armor) loader.getIm().getElements().get("ArmorMap").get(Integer.toString(eleccion));
            myArmor.add(armor);
        }
        return myArmor;
    }

    public String[] getTFighterText(ArrayList<TFighter> tFightersList) {
        ArrayList<String> text = new ArrayList<>();
        text.add(createFighter);
        int i = 1;
        for (TFighter tfighter : tFightersList) {
            text.add(i + ". " + tfighter.getName() + " Esbirros: +" + tfighter.getSuerteM() + " Armaduras: +" + tfighter.getSuerteA() + " Armas: +" + tfighter.getSuerteW());
            i++;
        }
        // Convertir ArrayList a Array de Strings
        return text.toArray(new String[text.size()]);
    }

    public ArrayList<TFighter> managerToListTFighter() {
        ArrayList<TFighter> result = new ArrayList<>();
        Map<String, TFighter> tFighterManager = loader.getTfm().getCollection("TFighterMap");
        for (TFighter tFighter : tFighterManager.values()) {
            result.add(tFighter);
        }
        return result;
    }

    public LinkedList<Strength> getStrengths() {
        LinkedList<Strength> MyStrength = new LinkedList<>();
        Map<String, Modifier> modifierMap = loader.getMom().getElements().get("StrengthMap");

        for (String key : modifierMap.keySet()) {
            Modifier modifier = modifierMap.get(key);

            if (modifier instanceof Strength) {
                Strength strength = (Strength) modifier;
                MyStrength.add(strength);
            }
        }
        return MyStrength;
    }

    public LinkedList<Weakness> getWeaknesses() {
        LinkedList<Weakness> MyWeakness = new LinkedList<>();
        Map<String, Modifier> modifierMap = loader.getMom().getElements().get("WeaknessMap");

        for (String key : modifierMap.keySet()) {
            Modifier modifier = modifierMap.get(key);

            if (modifier instanceof Weakness) {
                Weakness weakness = (Weakness) modifier;
                MyWeakness.add(weakness);
            }
        }
        return MyWeakness;
    }

    //He tenido que hacer una pseudocola, es decir, una pepsi.
    public Challenge getChallenge() {
        for (Map.Entry<String, Challenge> entry : this.challengeManager.getCollection("ChallengeMap").entrySet()) {
            return entry.getValue(); //Esto debería devolver el primer desafio insertado
        }
        return null; //Esto en principio no se usa nunca
    }

    public void eraseChallenge() {
        for (Map.Entry<String, Challenge> entry : this.challengeManager.getCollection("ChallengeMap").entrySet()) {
            this.challengeManager.getCollection("ChallengeMap").remove(entry.getKey()); //Esto debería devolver el primer desafio insertado
            updateChallenges();
            return; //Para salir en la primera iteracion
        }
    }

    public void addChallenge(Challenge challenge) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(date);
        challengeManager.getCollection("ChallengeMap").put(formattedDate, challenge);
        updateChallenges();
    }

    public boolean isEmptyChallengeManager() {
        return (challengeManager.getCollection("ChallengeMap").isEmpty());
    }

    public String[] getCombatHistory(Player player) {
        ArrayList<String> combattext = new ArrayList<>();
        for (Map.Entry <String,Combat> entry: combatregister.getCollection("CombatMap").entrySet()) {
            Combat combat =entry.getValue();
            if (combat.getChallenger().equals(player.getFighter()) || combat.getChallenged().equals(player.getFighter())) {
                combattext.add("Fecha de combate: " + combat.getDate()+ " Resultado de combate: " + combat.result()+ " Oro ganado/perdido: " + player.whoGetsGold(combat));

            }
        }
        if (combattext.isEmpty()){
            return null;
        }
       String [] data =  combattext.toArray(new String[combattext.size()]);
        return data;
    }
    public boolean isCombatRegisterEmpty(){
        return combatregister.getCollection("CombatMap").isEmpty();
    }
    public void changeFighterName(Player player,String name){
        Player aux = (Player) usermanager.getCollection("Player").get(player.getNick());
        Fighter fighter = aux.getFighter();
        fighter.setName(name);
        updateUsers();
    }
    public void changeFighterRace(Player player, int option){
        Player aux = (Player) usermanager.getCollection("Player").get(player.getNick());
        Fighter fighter = aux.getFighter();
        switch (option){
            case 1:
                fighter = new Vampire(fighter.getName(), fighter.getType(),fighter.getMyMinions(),fighter.getMyArmor(),fighter.getMyWeapon());
                break;
            case 2:
                fighter = new Lycanthrope(fighter.getName(), fighter.getType(),fighter.getMyMinions(),fighter.getMyArmor(),fighter.getMyWeapon());
                break;
            case 3:
                fighter = new Hunter(fighter.getName(), fighter.getType(),fighter.getMyMinions(),fighter.getMyArmor(),fighter.getMyWeapon());
                break;
        }
        fighter.setGold(player.getFighter().getGold());
        player.setFighter(fighter);
        updateUsers();
    }
    public void changeFighterType(Player player, TFighter type){
        Player aux = (Player) usermanager.getCollection("Player").get(player.getNick());
        Fighter fighter = aux.getFighter();
        fighter.setType(type);
        updateUsers();
    }

    public boolean deleteArmor(Operator operator, Player player, int elementId) {
        boolean done;
        done = operator.deleteArmor(player, elementId);
        return done;
    }

    public boolean deleteWeapon(Operator operator, Player player, int elementId) {
        boolean done;
        done = operator.deleteWeapon(player, elementId);
        return done;
    }
    public boolean addWeapon(Operator operator, Player player, int newElemId) {
        boolean done = false;
        Map<String, Item> itemMap = loader.getIm().getElements().get("WeaponMap");
        for (String key : itemMap.keySet()) {
            Item item = itemMap.get(key);
            if (item.getId().equals(Integer.toString(newElemId))) {
                done = operator.addWeapon(player, (Weapon) item);
            }
        }
        return done;
    }

    public boolean addArmor(Operator operator, Player player, int newElemId) {
        boolean done = false;
        Map<String, Item> itemMap = loader.getIm().getElements().get("ArmorMap");
        for (String key : itemMap.keySet()) {
            Item item = itemMap.get(key);
            if (item.getId().equals(Integer.toString(newElemId))) {
                done = operator.addArmor(player, (Armor) item);
            }
        }
        return done;
    }

    public boolean deleteMinion(Operator operator, Player player, int minionId) {
        boolean done;
        done = operator.deleteMinion(player, minionId);
        return done;
    }
    public boolean addMinion(Operator operator, Player player, int newMinionId) {
        boolean done = false;
        Map<String, Minion> minionMap = loader.getMm().getElements().get("MinionMap");
        for (String key : minionMap.keySet()) {
            Minion minion = minionMap.get(key);
            if (minion.getId().equals(Integer.toString(newMinionId))) {
                done = operator.addMinion(player, minion);
            }
        }
        return done;
    }

    public void addMinionText (ArrayList miniontext, int i, Minion minion){
        miniontext.add(i + ". " + minion.getName() + " Tipo: " + minion.getTipo() + " " +  minion.getAddedAttribute().name() + ":" + minion.getAddedAttribute().getValue() + " Salud: " + minion.getHealth());
    }

    public String [] generateMinionText() {
        ArrayList<String> miniontext = new ArrayList<>();
        int i =1;
        Map<String, Minion> minionMap = loader.getMm().getElements().get("MinionMap");
        for (String key : minionMap.keySet()) {
            Minion minion = minionMap.get(key);
            addMinionText(miniontext,i,minion);
            i++;
        }
        return miniontext.toArray(new String[miniontext.size()]);
    }

    public String [] generateWeaponText() {
        ArrayList<String> weaponText = new ArrayList<>();
        Map<String, Item> itemMap = loader.getIm().getElements().get("WeaponMap");
        int i = 1;
        for (String key : itemMap.keySet()) {
            Item item = itemMap.get(key);
            if (item instanceof Weapon) {
                weaponText.add(i +". "+item.getName()+" Ataque: "+item.getAttack() + " " + ((Weapon) item).handConverter());
            }
            i++;
        }
        return weaponText.toArray(new String[weaponText.size()]);
    }

    public String [] generateArmorText() {
        ArrayList<String> armorText = new ArrayList<>();
        Map<String, Item> itemMap = loader.getIm().getElements().get("ArmorMap");
        int i = 1;
        for (String key : itemMap.keySet()) {
            Item item = itemMap.get(key);
            if (item instanceof Armor) {
                armorText.add(i +". "+item.getName()+" Ataque: "+item.getAttack() + " Defensa: " + ((Armor) item).getDefense());
            }
            i++;
        }
        return armorText.toArray(new String[armorText.size()]);
    }

    public String[] generateEquipmentText() {
        String [] equipment = new String [generateWeaponText().length+ generateArmorText().length+2];
        equipment [0] = weaponSeparator;
        for (int i=0;i<generateWeaponText().length;i++){
            equipment[i+1]=generateWeaponText()[i];
        }
        equipment [generateWeaponText().length+1] = armorSeparator;
        for(int i =0 ;i< generateArmorText().length;i++){
            equipment[generateWeaponText().length+2+i]=generateArmorText()[i];
        }
        return equipment;
    }
    public void equipWeapon1(Player player, Weapon weapon){
        Player aux = (Player) usermanager.getCollection("Player").get(player.getNick());
        Fighter fighter = aux.getFighter();
        if (fighter.getArma2() != null){
            if (weapon.isOneHand()){
                fighter.setWeapon1(weapon);
                updateUsers();
            }

        }else{
            fighter.setWeapon1(weapon);
            updateUsers();
        }
    }


    public void equipWeapon2(Player player, Weapon weapon) {
        Player aux = (Player) usermanager.getCollection("Player").get(player.getNick());
        Fighter fighter = aux.getFighter();
        if ( !(weapon.isOneHand())){
            return;
        }
        if (fighter.getArma1().isOneHand()){
            fighter.setWeapon2(weapon);
            updateUsers();
        }
    }
    public void equipArmor(Player player, Armor armor){
        player.getFighter().setArmor(armor);
        updateUsers();
    }
    public void updateGold(Fighter fighter, int gold){
        fighter.setGold(fighter.getGold()+gold);
        updateUsers();
    }
    public void addMail(Player player,String [] mail){
        player.getFighter().addMail(mail);
    }
    public String [] getMail(Player player){
        return player.getFighter().getMail();
    }

}