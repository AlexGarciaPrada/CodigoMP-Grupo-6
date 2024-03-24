package combate2000lasecuela.managers;

import combate2000lasecuela.CosasDeLuchador.Minion;
import combate2000lasecuela.Operator;
import combate2000lasecuela.Player;
import combate2000lasecuela.User;

import java.util.*;

public class Database {
    private UserManager usermanager;
    private ItemManager itemManager;
    private ChallengeManager challengeManager;
    private CombatResgister combatregister;
    private MinionManager minionmanager;

    public Database() {
        this.usermanager = new UserManager();
        this.itemManager = new ItemManager();
        this.challengeManager = new ChallengeManager();
        this.combatregister = new CombatResgister();
        this.minionmanager = new MinionManager();
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

}
