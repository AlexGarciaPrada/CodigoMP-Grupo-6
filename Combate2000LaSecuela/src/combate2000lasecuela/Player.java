package combate2000lasecuela;

import java.util.LinkedList;
import java.util.Random;

import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.CosasDeLuchador.Fighter;
import combate2000lasecuela.CosasDeLuchador.Weapon;

public class Player extends User {
    private String registerNumber;
    private int victories;
    private boolean blocked;
    private Fighter fighter;
    private static int numRegisterCount = 0;


    public Player(String name, String password, String nick) {
        super(name, password, nick);
        this.registerNumber = generateRegisterNum();
        this.victories=0;
        this.blocked = false;
        this.fighter =null;
    }

    public int whoGetsGold(Combat c) {
        if (c.getResult().equals(Constants.isTie)) {
            return 0;
        }
        else if (c.getResult().equals(this.fighter.getName())) {
            victories++;
            return c.getGoldGained();
        } else return -c.getGoldGained();
    }

    public Combat Fight(Player challenger, int gold) {
        return getFighter().startFighting(challenger.getFighter(), gold);
    }

    public void createFighter(Fighter  fighter){
        if (this.fighter == null) {
            this.fighter = fighter;
        }
    }
    
    private String generateRegisterNum() {
        Random r = new Random();

        char letter1 = (char) ('A' + r.nextInt(26));
        char letter2 = (char) ('A' + r.nextInt(26));
        char letter3 = (char) ('A' + r.nextInt(26));

        int num1 = r.nextInt(8) + 1;
        int num2 = numRegisterCount++;

        return String.format("%c%d%d%c%c", letter1, num1, num2, letter2, letter3);
    }

    public void deleteFighter() {fighter = null;}
    public Challenge challengePlayer(Player challenged, int gold) {
                return new Challenge(this, challenged, gold);
    }

    public void rejectingChallenge(int gold){
        this.getFighter().setGold(this.getFighter().getGold()- (int) (gold*0.1));
    }

    public boolean hasPendingChallenges(){
        if (this.getFighter()==null){
            return false;
        }else{
            return !(this.getFighter().getPendingChallenges().isEmpty());
        }

    }
    public void addPendingChallenge(Challenge challenge){
        this.getFighter().getPendingChallenges().addChallenge(challenge);

    }
    public void deletePendingChallenge(){
        this.getFighter().getPendingChallenges().deleteChallenge();
    }


    public int getVictories() {return victories;}

    public Fighter getFighter() {return fighter;}

    public void setBlocked(boolean state) {blocked = state;}

    public boolean isBlocked() {return blocked;}

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }

    public void setVictories(int victories) {this.victories = victories;  }
}

