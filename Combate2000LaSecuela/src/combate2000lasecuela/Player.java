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
        this.registerNumber = generateRegisterNum();
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

    private String generateRegisterNum() {
        Random r = new Random();

        char letter1 = (char) ('A' + r.nextInt(26));
        char letter2 = (char) ('A' + r.nextInt(26));
        char letter3 = (char) ('A' + r.nextInt(26));

        int num1 = r.nextInt(8) + 1;
        int num2 = r.nextInt(8) + 1;

        return String.format("%c%d%d%c%c", letter1, num1, num2, letter2, letter3);
    }

    public void deleteFighter() {fighter = null;}


    public Challenge challengePlayer(Player challenged, int gold) {
        return new Challenge(challenged, gold);
    }

    //esto tal vez deba hacerlo Alex con el challenge que devuelve el metodo anterior

    /*public Challenge ChallengePlayer(Player challenged) {
        ChallengeManager cm = new ChallengeManager();
        cm.addElement("Challenge", generateRandomChallengeKey(), new Challenge());
        return cm.getElements().get("Challenge").get(generateRandomChallengeKey());
    }

    public static String generateRandomChallengeKey() {
        return UUID.randomUUID().toString();
    }*/

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

