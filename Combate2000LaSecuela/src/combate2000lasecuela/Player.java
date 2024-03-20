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
        this.victories = victories;
        this.blocked = blocked;
        this.fighter = fighter;
    }

    public int getVictories() {return victories;}

    public Fighter getFighter() {return fighter;}

    public void setBlocked() {blocked = true;}

    public boolean isBlocked() {return blocked;}

    public void setFighter(Fighter character) {fighter = character;}

    public void deleteFighter() {fighter = null;}

    public Challenge ChallengePlayer() {
        ChallengeManager cm = new ChallengeManager();
        cm.addElement("Challenge", generateRandomChallengeKey(), new Challenge());
        return cm.loadElement(generateRandomChallengeKey());
    }

    public static String generateRandomChallengeKey() {
        return UUID.randomUUID().toString();
    }

    public void fight(Challenge c) {}
}
