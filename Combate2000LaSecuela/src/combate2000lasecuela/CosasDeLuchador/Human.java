package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;

import static java.lang.Integer.valueOf;

import static java.lang.Integer.valueOf;
public class Human extends Minion implements Serializable {
int health;
String lealtad;
    public Human(String linea) {
        super(linea);
        String [] valores = linea.split(";");
        this.lealtad=valores[3];
        this.health=Integer.parseInt(valores[4].trim());
        setHealth(health);
    }

    @Override
    public int getHealth() {
        return health;
    }

    public String getLealtad() {return this.lealtad;}
}
