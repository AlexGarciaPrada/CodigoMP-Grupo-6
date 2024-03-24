package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;

import static java.lang.Integer.valueOf;

public class Ghoul extends Minion implements Serializable {
int health;
int lealtad;
    public Ghoul(String linea) {

        super(linea);
        String [] valores = linea.split(";");
        this.lealtad=valueOf(valores[3]);
        this.health=valueOf(valores[4]);
        setHealth(health);
    }
}
