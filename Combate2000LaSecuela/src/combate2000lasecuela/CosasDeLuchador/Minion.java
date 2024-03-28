package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import combate2000lasecuela.Saveable;
import combate2000lasecuela.managers.MinionManager;
public abstract class Minion implements Saveable {
    private String name;
    private int health;
    private String tipo;
    private int id;
    private int minionId;
    private boolean equipped;

    public Minion(String linea){
        String [] valores = linea.split(";");
        this.id=Integer.parseInt(valores[0]);
        this.name = valores[1];
        this.tipo = valores[2];
    }

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

    public abstract int getHealth();

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMinionId() {
        return minionId;
    }

    public void setMinionId(int minionId) {
        this.minionId = minionId;
    }
    public void setEquipped(boolean state){equipped = state;}
    public boolean isEquipped() {
        return equipped;
    }

    public String getTipo() { return this.tipo;}

}
