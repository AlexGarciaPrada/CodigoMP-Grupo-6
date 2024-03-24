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


    public void registerFighter(Fighter f) {
        this.fighter = f;
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

    public Challenge ChallengePlayer(Player challenged) {
        ChallengeManager cm = new ChallengeManager();
        cm.addElement("Challenge", generateRandomChallengeKey(), new Challenge());
        return cm.getElements().get("Challenge").get(generateRandomChallengeKey());
    }

    public static String generateRandomChallengeKey() {
        return UUID.randomUUID().toString();
    }

    //sigo pensando en si tiene sentido porque pendingChallenges tiene relacion con fighter

   // public void fight(Challenge c) {
    //    fighter.startFighting(c);
    //    }
}
