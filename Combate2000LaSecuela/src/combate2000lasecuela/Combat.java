package combate2000lasecuela;

import java.util.Date;


public class Combat extends Saveable {
    private Player challenger;
    private Player challenged;
    private int rounds;
    private Date date;
    private boolean isFinished;
    private Player winner;
    private int goldGained;

    @Override
    public String getId() {
        return null;
    }
}


