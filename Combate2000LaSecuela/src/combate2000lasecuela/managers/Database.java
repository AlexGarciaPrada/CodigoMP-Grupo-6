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
    private int demonMax;


    public Database() {
        this.usermanager = new UserManager();
        this.loader = new Loader();
        this.challengeManager = new ChallengeManager();
        this.combatregister = new CombatRegister();
        this.demonMax =0;
    }

    public void addFighter(Player player, Fighter fighter) {
        player.createFighter(fighter);
        updateUsers();
    }

    public void eraseFighter(Player player) {
        player.deleteFighter();
        updateUsers();
    }

    public void deletePendingChallenge(Player player){
        player.deletePendingChallenge();
        updateUsers();
    }

    private void updateUsers() {
        usermanager.saveCollection("User");
    }

    private void updateChallenges() {
        challengeManager.saveCollection("Challenge");
    }

    private void updateCombats() {
        combatregister.saveCollection("Combat");
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
        this.updateUsers();
    }

    public void erasePlayer(Player player) {
        usermanager.getElements().get("Player").remove(player.getNick());
        this.updateUsers();
    }

    public void eraseOperator(Operator operator) {
        usermanager.getElements().get("Operator").remove(operator.getNick());
        this.updateUsers();
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

    public LinkedList<Minion> randomMinions(int suerte, boolean isPlayer, boolean esVampiro, int max) {
        int handicap=Constants.handicapMinion;
        int election;
        int e=0;
        Random random = new Random();
        LinkedList<Minion> myMinions = new LinkedList<>();
        Minion slave;
        int numero = random.nextInt(loader.getMinionManager().getCollection("MinionMap").size()) + 1;
        if (numero>handicap){
            numero=(numero%handicap)+1+ suerte;
        }
        for (int i = 0; i <= numero; i++) {
            election = random.nextInt(loader.getMinionManager().getElements().get("MinionMap").size());
            slave = loader.getMinionManager().getElements().get("MinionMap").get(Integer.toString(election));
            if (!esVampiro) {
                myMinions.add(slave);
            }else if (!(slave instanceof Human)) {
                myMinions.add(slave);
            }else{ //caso limite que vio Alex
                while (slave instanceof Human){
                    e++;
                    election = random.nextInt(loader.getMinionManager().getElements().get("MinionMap").size());
                    slave = loader.getMinionManager().getElements().get("MinionMap").get(Integer.toString(election));
                    if (e>=10){
                        slave=loader.getMinionManager().getElements().get("MinionMap").get(Integer.toString(2));
                    } //esto mete un ghoul a la fuerza, la posibilidad de que entre aquí es rídicula
                    //y la probabilidad de que entre varias veces ya sería un chiste mal contado
                    if ((slave instanceof Ghoul)){
                        myMinions.add(slave);
                        e=0;
                    }
                }
            }
            if ((slave instanceof Demon) && (max < Constants.handicapMinion) && (getDemonMax()<Constants.handicapMinion)) { //que no se meta en bucle continuo, capo a los demonios
                if (isPlayer) {
                    max += 1;
                }else{
                    setDemonMax(getDemonMax()+1);
                }
                ((Demon) slave).setDemonList(randomMinionDemon(max));
            }

        }
        return myMinions;
    }

    public LinkedList<Minion> randomMinionDemon(int tope) {
            return randomMinions(0, false, false,tope);
    }


    public LinkedList<Weapon> randomWeapons(int suerte) {
        int handicap= handicapItem;
        int eleccion;
        Random random = new Random();
        LinkedList<Weapon> myWeapon = new LinkedList<>();
        Weapon weapon;
        int numero = random.nextInt(28) + 1;
        if (numero>handicap){
            numero=(numero%handicap)+1+ suerte;
        }
        for (int i = 1; i <= numero; i++) {
            eleccion = random.nextInt(loader.getItemManager().getElements().get("WeaponMap").size());
            weapon = (Weapon) loader.getItemManager().getElements().get("WeaponMap").get(Integer.toString(eleccion));
            myWeapon.add(weapon);
        }
        return myWeapon;
    }

    public LinkedList<Armor> randomArmor(int suerte) {
        int handicap = handicapItem;
        Random random = new Random();
        int eleccion;
        Armor armor;
        LinkedList<Armor> myArmor = new LinkedList<>();
        int numero = random.nextInt(loader.getItemManager().getCollection("ArmorMap").size()) + 1 ;
        if (numero>handicap){
            numero=(numero%handicap)+1+suerte; //si alguno no entiende el +1 que me pregunte
        }
        for (int i = 1; i <= numero; i++) {
            eleccion = random.nextInt(loader.getItemManager().getElements().get("ArmorMap").size());
            armor = (Armor) loader.getItemManager().getElements().get("ArmorMap").get(Integer.toString(eleccion));
            myArmor.add(armor);
        }
        return myArmor;
    }

    public String[] getTFighterText() {
        ArrayList<String> text = new ArrayList<>();
        text.add(createFighter);
        int i = 1;
        for (TFighter tfighter : managerToListTFighter()) {
            text.add(i + ". " + tfighter.getName() + " Esbirros: +" + tfighter.getMinionLuck() + " Armaduras: +" + tfighter.getArmorLuck() + " Armas: +" + tfighter.getWeaponLuck());
            i++;
        }
        // Convertir ArrayList a Array de Strings
        return text.toArray(new String[text.size()]);
    }

    private ArrayList<TFighter> managerToListTFighter() {
        ArrayList<TFighter> result = new ArrayList<>();
        Map<String, TFighter> tFighterManager = loader.gettFighterManager().getCollection("TFighterMap");
        for (TFighter tFighter : tFighterManager.values()) {
            result.add(tFighter);
        }
        return result;
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

    private String generateCombatHistoryText(Combat combat, Player player){
        return "Fecha de combate: " + combat.getDate()+ " Resultado de combate: " + Arrays.toString(combat.getResult())+ " Oro ganado/perdido: " + combatHistoryModifyer(combat,player) + Integer.toString(combat.getGoldGained());
    }

    private String combatHistoryModifyer(Combat combat, Player player){
        if (combat.getLoser().equals(player.getFighter())){
            return "";
        }
        else if (combat.getWinner().equals(player.getFighter())){
            return "-";
        }
        return null;
    }

    public String[] getCombatHistory(Player player) {
        ArrayList<String> combatText = new ArrayList<>();
        for (Map.Entry <String,Combat> entry: combatregister.getCollection("CombatMap").entrySet()) {
            Combat combat =entry.getValue();
            combatText.add(generateCombatHistoryText(combat, player));
        }
        if (combatText.isEmpty()){
            return null;
        }
       String [] data =  combatText.toArray(new String[combatText.size()]);
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
        Fighter fighter = player.getFighter();
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
            default:
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
        updateUsers();
        return done;
    }

    public boolean deleteWeapon(Operator operator, Player player, int elementId) {
        boolean done;
        done = operator.deleteWeapon(player, elementId);
        updateUsers();
        return done;
    }
    public boolean addWeapon(Operator operator, Player player, int newElemId) {
        boolean done = false;
        Map<String, Item> itemMap = loader.getItemManager().getElements().get("WeaponMap");
        for (String key : itemMap.keySet()) {
            Item item = itemMap.get(key);
            if (item.getId().equals(Integer.toString(newElemId))) {
                done = operator.addWeapon(player, (Weapon) item);
                updateUsers();
            }
        }
        return done;
    }

    public boolean addArmor(Operator operator, Player player, int newElemId) {
        boolean done = false;
        Map<String, Item> itemMap = loader.getItemManager().getElements().get("ArmorMap");
        for (String key : itemMap.keySet()) {
            Item item = itemMap.get(key);
            if (item.getId().equals(Integer.toString(newElemId))) {
                done = operator.addArmor(player, (Armor) item);
                updateUsers();
            }
        }
        return done;
    }

    public boolean deleteMinion(Operator operator, Player player, int minionId) {
        boolean done;
        done = operator.deleteMinion(player, minionId);
        updateUsers();
        return done;
    }
    public boolean addMinion(Operator operator, Player player, int newMinionId) {
        boolean done = false;
        Map<String, Minion> minionMap = loader.getMinionManager().getElements().get("MinionMap");
        for (String key : minionMap.keySet()) {
            Minion minion = minionMap.get(key);
            if (minion.getId().equals(Integer.toString(newMinionId))) {
                done = operator.addMinion(player, minion);
                updateUsers();
            }
        }
        return done;
    }

    public void addMinionText (ArrayList miniontext, int i, Minion minion){
        miniontext.add(i + ". " + minion.getName() + " Tipo: " + minion.getType() + " "  +minion.getSpecialSkillName() +":"+ minion.getSpecialSkill()+ " Salud: " + minion.getHealth());
    }

    public String [] generateMinionText() {
        ArrayList<String> miniontext = new ArrayList<>();
        int i =1;
        Map<String, Minion> minionMap = loader.getMinionManager().getElements().get("MinionMap");
        for (String key : minionMap.keySet()) {
            Minion minion = minionMap.get(key);
            addMinionText(miniontext,i,minion);
            i++;
        }
        return miniontext.toArray(new String[miniontext.size()]);
    }

    public String [] generateWeaponText() {
        ArrayList<String> weaponText = new ArrayList<>();
        Map<String, Item> itemMap = loader.getItemManager().getElements().get("WeaponMap");
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
        Map<String, Item> itemMap = loader.getItemManager().getElements().get("ArmorMap");
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
//
//    public String[] generateEquipmentText() {
//        String [] equipment = new String [generateWeaponText().length+ generateArmorText().length+2];
//        equipment [0] = weaponSeparator;
//        for (int i=0;i<generateWeaponText().length;i++){
//            equipment[i+1]=generateWeaponText()[i];
//        }
//        equipment [generateWeaponText().length+1] = armorSeparator;
//        for(int i =0 ;i< generateArmorText().length;i++){
//            equipment[generateWeaponText().length+2+i]=generateArmorText()[i];
//        }
//        return equipment;
//    }
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
        fighter.setPendingGold(fighter.getPendingGold()+gold);
        updateUsers();
    }
    public void addMail(Player player,String [] mail){
        player.getFighter().addMail(mail);
    }

    public void addCombat (Combat combat){
        combatregister.addElement("CombatMap",combat.getDate().toString(),combat);
        updateCombats();
    }
    public void eraseMail(Player player){
        player.getFighter().eraseMail();
        updateUsers();
    }
    public void reducePendingGold(int gold,Player player){
        player.getFighter().setPendingGold(player.getFighter().getPendingGold()-gold);
        updateUsers();
    }

    private void setDemonMax(int demonMax) {
        this.demonMax = demonMax;
    }

    public int getDemonMax() {
        return demonMax;
    }

    public UserManager getUsermanager() {
        return usermanager;
    }

    public Loader getLoader() {
        return loader;
    }

    public ChallengeManager getChallengeManager() {
        return challengeManager;
    }

    public CombatRegister getCombatregister() {
        return combatregister;
    }
    //Métodos para los test

}