package combate2000lasecuela.managers;

import combate2000lasecuela.*;
import combate2000lasecuela.CosasDeLuchador.*;

import java.util.*;

public class Database {
    private UserManager usermanager;
    private Loader loader;
    private ChallengeManager challengeManager;
    private CombatResgister combatregister;

    private ModifierManager modifierManager;


    public Database() {
        this.usermanager = new UserManager();
        this.loader=new Loader();
        this.challengeManager = new ChallengeManager();
        this.combatregister = new CombatResgister();
    }
    public void addFighter(Player player,Fighter fighter){
        player.createFighter(fighter);
        usermanager.saveCollection("User");

    }
    public void eraseFighter(Player player){
        player.deleteFighter();;
        usermanager.saveCollection("User");

    }
    public void updateUsers(){
        usermanager.saveCollection("User");
    }
    public void addPendingChallenge(Player challenged, Challenge challenge){
        challenged.addPendingChallenge(challenge);
        usermanager.saveCollection("User");
    }

    public void loadUsers(){
        usermanager.loadElement("User");
    }

    public void addPlayer(Player player){
        usermanager.addElement("Player", player.getNick(), player);
        usermanager.saveCollection("User");
    }
    public boolean isAPlayer(String nick){
        return usermanager.inMap("Player",nick);
    }
    public void addOperator(Operator operator){
        usermanager.addElement("Operator", operator.getNick(), operator);
        usermanager.saveCollection("User");
    }
    public void erasePlayer(Player player){
        usermanager.getElements().get("Player").remove(player.getNick());
        usermanager.saveCollection("User");
    }
    public void eraseOperator(Operator operator){
        usermanager.getElements().get("Operator").remove(operator.getNick());
        usermanager.saveCollection("User");
    }

    public boolean isNickUsed(String nick){
        if ((usermanager.inMap("Player",nick)) || (usermanager.inMap("Operator",nick))){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isPasswordCorrect(String nick,String password){
        if ((usermanager.inMap("Player",nick))){
            return (usermanager.getElements().get("Player").get(nick).getPassword().equals(password));
        }else{
            return (usermanager.getElements().get("Operator").get(nick).getPassword().equals(password));
        }
    }
    public User getUser(String nick){
        if ((usermanager.inMap("Player",nick))){
            return usermanager.getElements().get("Player").get(nick);
        }else{
            return usermanager.getElements().get("Operator").get(nick);
        }
    }
    private ArrayList<Player> mapToNotOrderedRanking(Map<String,User> map) {
        ArrayList<Player> playerList = new ArrayList<>();
        for (User user : map.values()) {
            playerList.add((Player) user); //Tengo que añadirlos uno a uno para castearlos
        }
        return playerList;
    }
    public ArrayList<String> getRanking(){
        ArrayList <String> ranking = new ArrayList<>();
        ArrayList<Player> playerList = mapToNotOrderedRanking(usermanager.getElements().get("Player"));
        Collections.sort(playerList,Comparator.comparing(Player::getVictories));
        for (Player player:playerList){
            ranking.add(String.valueOf(playerList.indexOf(player)+1)+". "+player.getNick() + " Victorias: "+player.getVictories());
        }
        return ranking;
    }
    public Stack<Minion> randomMinions(int suerte, boolean esVampiro,int tope){
        Random random = new Random();
        Stack<Minion> myMinions=new Stack<>();
        Minion esclavo;
        int numero= random.nextInt(80)+1+suerte;
        for (Integer i=0; i<=numero;i++){
            esclavo = loader.getMm().getElements().get("MinionMap").get(i.toString());
            if (!(esVampiro) || !(esclavo instanceof Human)){
                myMinions.push(esclavo);
                if ((esclavo instanceof Demon)&&(tope<=3)){ //que no se meta en bucle continuo, capo a los demonios
                    tope+=1;
                    ((Demon) esclavo).setPilaDemoniaca(randomMinionDemon(tope));
                }
            }
        }
        return myMinions;
    } //no estoy muy seguro de que esto este bien, pero no veo problemas de primeras
    public Stack<Minion> randomMinionDemon(int tope){
      if (tope<=3){
         return randomMinions(0,false,tope+1);
      }
      return null;
    }
    public TFighter getTFighter(){
        return null;
    }
    public LinkedList<Weapon> randomWeapons(int suerte) {
        Random random = new Random();
        LinkedList<Weapon> myWeapon=new LinkedList<>();
        Weapon arma;
        int number = random.nextInt(28) + 1 + suerte;
        for (Integer i=1; i<=number;i++){
            arma = (Weapon) loader.getIm().getElements().get("WeaponMap").get(i.toString());
            myWeapon.add(arma);
        }
        return myWeapon;
    }
    public LinkedList<Armor> randomArmor(int suerte){
        Random random = new Random();
        Armor armor;
        LinkedList<Armor> myArmor=new LinkedList<>();
        int numero= random.nextInt(28)+1+suerte;
        for (Integer i=1; i<=numero; i++) {
            armor = (Armor) loader.getIm().getElements().get("ArmorMap").get(i.toString());
            myArmor.add(armor);
        }
        return myArmor;
    }
    public String[] getTFighterText(ArrayList<TFighter> tFightersList) {
        ArrayList<String> text = new ArrayList<>();
        text.add("Elige el tipo de personaje que deseas crear: ");
        int i = 1;
        for (TFighter tfighter : tFightersList) {
            text.add(i + ". " + tfighter.getName() + " Esbirros: +" + tfighter.getSuerteM() + " Armaduras: +" + tfighter.getSuerteA() + " Armas: +" + tfighter.getSuerteW());
            i++;
        }
        // Convertir ArrayList a Array de Strings
        return text.toArray(new String[text.size()]);
    }
    public ArrayList<TFighter>  managerToListTFighter(){
        ArrayList <TFighter> result = new ArrayList<>();
        Map <String,TFighter> tFighterManager= loader.getTfm().getElements().get("TFighterMap");
        for(TFighter tFighter: tFighterManager.values()){
            result.add(tFighter);
        }
        return result;
    }

    public LinkedList<Strength> getStrengths() {
        LinkedList<Strength> MyStrength = new LinkedList<>();
        Map<String, Modifier> modifierMap =  loader.getMom().getElements().get("StrengthMap");

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
        Map<String, Modifier> modifierMap =  loader.getMom().getElements().get("WeaknessMap");

        for (String key : modifierMap.keySet()) {
            Modifier modifier = modifierMap.get(key);

            if (modifier instanceof Weakness) {
                Weakness weakness = (Weakness) modifier;
                MyWeakness.add(weakness);
            }
        }
        return MyWeakness;
    }

}