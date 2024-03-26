package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;

import static java.lang.Integer.valueOf;

public class Ghoul extends Minion implements Serializable {
int health;
int lealtad;
    public Ghoul(String linea) {

        super(linea);
        String [] valores = linea.split(";");
        this.lealtad=Integer.parseInt(valores[3].trim());
        this.health=Integer.parseInt(valores[4].trim());
        setHealth(health);
    }

    @Override
    public int getHealth() {
        return health;
    }
}
