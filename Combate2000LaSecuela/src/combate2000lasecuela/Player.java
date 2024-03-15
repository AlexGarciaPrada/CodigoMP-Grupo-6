package combate2000lasecuela;

import java.io.Serializable;
public class Player extends User  {
    private String registerNumber;
    private int victories;
    private boolean blocked;

    public Player(String name, String password, String nick) {
        super(name, password, nick);
        this.registerNumber = registerNumber;
        this.victories = victories;
        this.blocked = blocked;
    }

    public void ChallengePlayer(){
    }
}
