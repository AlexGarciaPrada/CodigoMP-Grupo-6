package combate2000lasecuela;

import java.util.Map;
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
        this.registerNumber = registerNumber;
        this.victories=0;
        this.blocked = false;
        this.fighter =null;


    }

    public int getVictories() {return victories;}

    public Fighter getFighter() {return fighter;}

    public void setBlocked(boolean state) {blocked = state;}


    public boolean isBlocked() {return blocked;}


    //public void registerFighter(Fighter f) {
    //this.fighter = f;}



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
