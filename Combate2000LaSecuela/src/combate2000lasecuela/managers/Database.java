package combate2000lasecuela.managers;

import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.CosasDeLuchador.Human;
import combate2000lasecuela.CosasDeLuchador.Minion;
import combate2000lasecuela.CosasDeLuchador.Weapon;
import combate2000lasecuela.Operator;
import combate2000lasecuela.Player;
import combate2000lasecuela.User;

import java.util.*;

public class Database {
    private UserManager usermanager;
    private ItemManager itemManager;
    private ChallengeManager challengeManager;
    private CombatResgister combatregister;
    private MinionManager minionManager;


    public Database() {
        this.usermanager = new UserManager();
        this.itemManager = new ItemManager();
        this.challengeManager = new ChallengeManager();
        this.combatregister = new CombatResgister();
        this.minionManager = new MinionManager();
    }

    public void loadUsers(){
        usermanager.loadElement("User");
    }

    public void addPlayer(Player player){
        usermanager.addElement("Player", player.getNick(), player);
        usermanager.saveCollection("User");

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
        if ((usermanager.isInTheMap("Player",nick)) || (usermanager.isInTheMap("Operator",nick))){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isPasswordCorrect(String nick,String password){
        if ((usermanager.isInTheMap("Player",nick))){
            return (usermanager.getElements().get("Player").get(nick).getPassword().equals(password));
        }else{
            return (usermanager.getElements().get("Operator").get(nick).getPassword().equals(password));
        }
    }
    public User getUser(String nick){
        if ((usermanager.isInTheMap("Player",nick))){
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
    private Stack<Minion> randomMinions(int suerte){
        Random random = new Random();
        Stack<Minion> myMinions=null;
        Minion esclavo;
        int numero= random.nextInt(80)+1+suerte;
        for (Integer i=0; i<=numero;i++){
            esclavo = minionManager.getElements().get("MinionMap").get(i.toString());
            if (!("Vampire".equals(this.type)) || !(esclavo instanceof Human)){
                myMinions.push(esclavo);
            }
        }
        return myMinions;
    } //mandar todo esto a DataBase
    private Stack<Weapon> randomWeapons(int suerte) {
        Random random = new Random();
        Stack<Weapon> myWeapon=null;
        Weapon arma;
        int numero = random.nextInt(28) + 1 + suerte;
        for (Integer i=1; i<=numero;i++){
            arma = (Weapon) itemManager.getElements().get("WeaponMap").get(i.toString());
            myWeapon.push(arma);
        }
        return myWeapon;
    }
    private Stack<Armor> randomArmor(int suerte){
        Random random = new Random();
        Armor armadura;
        Stack<Armor> myArmor=null;
        int numero= random.nextInt(28)+1+suerte;
        for (Integer i=1; i<=numero; i++) {
            armadura = (Armor) itemManager.getElements().get("ArmorMap").get(i.toString());
            myArmor.push(armadura);
        }
        return myArmor;
    }
}
