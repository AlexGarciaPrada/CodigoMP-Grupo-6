package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.Saveable;

public abstract class Item implements Saveable {
    private String name;
    private int attack;
    private boolean equipped;

    @Override
    public abstract String getId() ;

    public String getName() {
        return name;
    }
    public int getAttack() {
        return attack;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEquipped(boolean state){equipped = state;}
    public void setAttack(int attack) {
        this.attack = attack;
    }

}
