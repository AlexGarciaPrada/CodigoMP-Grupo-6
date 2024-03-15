package combate2000lasecuela;

import java.io.Serializable;

public abstract class Item implements Saveable {
    private String name;
    private int defense;
    private int attack;

    @Override
    public String getId() {
        return null;
    }
}
