package combate2000lasecuela.CosasDeLuchador;

import static java.lang.Integer.valueOf;

import static java.lang.Integer.valueOf;
public class Human extends Minion {
int health;
String lealtad;
    public Human(String linea) {
        super(linea);
        String [] valores = linea.split(";");
        this.lealtad=valores[3];
        this.health=valueOf(valores[4]);
        setHealth(health);
    }

}
