package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import combate2000lasecuela.Saveable;

public class Minion implements Saveable {
    private String name;
    private int health;

    @Override
    public String getId() {
        return null;
    }



    //------------------------ GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
