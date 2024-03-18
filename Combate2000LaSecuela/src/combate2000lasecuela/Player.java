package combate2000lasecuela;

import java.util.Map;
import java.util.UUID;

import combate2000lasecuela.CosasDeLuchador.TFighter;
import combate2000lasecuela.managers.ChallengeManager;
public class Player extends User {
    private String registerNumber;
    private int victories;
    private boolean blocked;

    public Player(String name, String password, String nick) {
        super(name, password, nick);
        this.registerNumber = registerNumber;
        this.victories = victories;
        this.blocked = blocked;
    }

    public static TFighter elegirTipo() {
        return null;
    }

    public Challenge ChallengePlayer(){
        String challenged = getMessageManager().challengeMenu();
        if (isAPlayer(challenged)) {
            int amount = getMessageManager().selectGold();
        }
        ChallengeManager cm = new ChallengeManager();
        cm.addElement("Challenge", generateRandomChallengeKey(), new Challenge());
        return cm.loadElement(generateRandomChallengeKey());
    }

    public boolean isAPlayer(String playerNick) {return true;}
    public void createCharacter() {
        
    }
    public static String generateRandomChallengeKey() {
        return UUID.randomUUID().toString();
    }


}
