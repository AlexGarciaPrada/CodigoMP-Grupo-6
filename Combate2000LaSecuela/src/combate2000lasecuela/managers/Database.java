package combate2000lasecuela.managers;

import combate2000lasecuela.CosasDeLuchador.Minion;
import combate2000lasecuela.Player;

public class Database {
    private UserManager usermanager;
    private ItemManager itemManager;
    private ChallengeManager challengeManager;
    private CombatResgister combatregister;
    private MinionManager minionmanager;

    public void addPlayer(Player player){
        usermanager.addElement("Player","Player",player);
    }
    public boolean isNickUsed(String nick){
        if (usermanager.isInTheMap(usermanager.getElements(),nick)){
            return true;
        }
        else{
            return false;
        }
    }

}
