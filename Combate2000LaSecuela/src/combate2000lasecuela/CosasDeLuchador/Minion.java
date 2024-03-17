package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import combate2000lasecuela.Saveable;
import combate2000lasecuela.managers.MinionManager;
public class Minion implements Saveable {
    private String name;
    private int health;
    private String tipo;
    public Minion(String linea){
        String [] valores = linea.split(";");
        this.name = valores[2];
        this.tipo = valores[3];
    }
    @Override
    public String getId() {
        return null;
    }
}
