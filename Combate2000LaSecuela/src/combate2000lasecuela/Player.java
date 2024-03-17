package combate2000lasecuela;

import java.util.UUID;

import combate2000lasecuela.CosasDeLuchador.User;
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

    public Challenge ChallengePlayer(){
        //mensaje del messagemanager preguntando a quien quiere retar
        ChallengeManager cm = new ChallengeManager();
        cm.addElement("Challenge", generateRandomChallengeKey(), new Challenge());
        return cm.loadElement(generateRandomChallengeKey());
    }

    public static String generateRandomChallengeKey() {
        return UUID.randomUUID().toString();
    }
}
