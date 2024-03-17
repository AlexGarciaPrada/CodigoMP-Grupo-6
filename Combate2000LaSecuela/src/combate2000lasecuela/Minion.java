package combate2000lasecuela;

import java.io.Serializable;
import combate2000lasecuela.Saveable;

public class Minion implements Saveable {
    private String name;
    private int health;

    @Override
    public String getId() {
        return null;
    }
}
