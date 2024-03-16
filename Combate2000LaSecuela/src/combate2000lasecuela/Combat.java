package combate2000lasecuela;

import java.util.Date;
import combate2000lasecuela.Saveable;


public class Combat implements Saveable {
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


