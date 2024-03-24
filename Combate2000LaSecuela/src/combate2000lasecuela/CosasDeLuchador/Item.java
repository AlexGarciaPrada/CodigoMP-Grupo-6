package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.Saveable;

public abstract class Item implements Saveable {
    private String name;
    private int defense;
    private int attack;

    @Override
    public abstract String getId() ;

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public abstract int getDamage();
    public abstract int getDefense ();
}
