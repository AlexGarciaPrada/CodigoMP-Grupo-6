package combate2000lasecuela;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

import combate2000lasecuela.CosasDeLuchador.Fighter;
import combate2000lasecuela.CosasDeLuchador.TFighter;
import combate2000lasecuela.managers.ChallengeManager;
public class Player extends User {
    private String registerNumber;
    private int victories;
    private boolean blocked;
    private Fighter fighter;


    public Player(String name, String password, String nick) {
        super(name, password, nick);
        //this.registerNumber = generateRegisterNum(num);
        this.victories=0;
        this.blocked = false;
        this.fighter =null;
    }

    public int getVictories() {return victories;}

    public Fighter getFighter() {return fighter;}

    public void setBlocked(boolean state) {blocked = state;}

    public boolean isBlocked() {return blocked;}

    public void createFighter(Fighter  fighter){
        this.fighter=fighter;
    }

    private String generateRegisterNum(int num) {
        Random r = new Random();

        char letter1 = (char) ('A' + r.nextInt(26));
        char letter2 = (char) ('A' + r.nextInt(26));
        char letter3 = (char) ('A' + r.nextInt(26));

        int num1 = r.nextInt(8) + 1;
        int num2 = num;

        return String.format("%c%d%d%c%c", letter1, num1, num2, letter2, letter3);
    }

    public void deleteFighter() {fighter = null;}


    public Challenge challengePlayer(Player challenged, int gold) {
        if (this.fighter != null && challenged.getFighter() != null) {
            return new Challenge(challenged, gold);
        } else return null;
    }

    public void updateAfterCombat(Combat c) {
        if (c.getChallenger() == c.getWinner()) {
            int actualGold = c.getChallenger().getGold();
             actualGold += c.getGoldGained();
        } else if (c.getChallenged() == c.getWinner()) {
            int actualGold = c.getChallenged().getGold();
            actualGold += c.getGoldGained();
        }
    }


    public void fight(Player challenged) {
        fighter.startFighting(challenged.getFighter());
    }

    /* toda esta parte deberia ser asi, pero no coincide con los parametros de Dani

    public void chooseWeapon1(int option) {
        fighter.setWeapon1(option);
    }

    public void chooseWeapon2(int option) {
        fighter.setWeapon2(option);
    }

    public void chooseArmor(int option) {
        fighter.setArmor(option);
    }

     */

}

