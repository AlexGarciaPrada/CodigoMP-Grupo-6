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

    public void setName(String name) {
        this.name = name;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
