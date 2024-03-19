package combate2000lasecuela.managers;

import combate2000lasecuela.CosasDeLuchador.Minion;
import combate2000lasecuela.Operator;
import combate2000lasecuela.Player;
import combate2000lasecuela.User;

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

    public void addPlayer(Player player){

        usermanager.addElement("Player","Player",player);
        //Falta un método para guardar el fichero
    }
    public void addOperator(Operator operator){
        usermanager.addElement("Operator","Operator",operator);
        usermanager.addElement("Player",player.getNick(),player);
        //Falta un método para guardar el fichero
    }
    public void addOperator(Operator operator){
        usermanager.addElement("Operator", operator.getNick(), operator);
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
        return (usermanager.getElements().get("Player").get(nick).getPassword().equals(password)) || (usermanager.getElements().get("Operator").get(nick).getPassword().equals(password));
    }
    public User getUser(String nick, String password){
        if ((usermanager.isInTheMap("Player",nick))){
            return usermanager.getElements().get("Player").get(nick);
        }else{
            return usermanager.getElements().get("Operator").get(nick);
        }
    }


}
