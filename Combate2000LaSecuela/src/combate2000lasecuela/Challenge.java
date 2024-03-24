package combate2000lasecuela;

import combate2000lasecuela.Saveable;
import java.io.Serializable;

public class Challenge implements Saveable {
    private boolean accepted;
    private Player player;

    private int gold;

    public Challenge(Player player, int gold) {
        this.player = player;
        this.gold = gold;
    }

    @Override
    public String getId() {
        return null;
    }


    //------------------------ GETTERS & SETTERS
    public boolean getAccepted() {
        return accepted;
    }
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
