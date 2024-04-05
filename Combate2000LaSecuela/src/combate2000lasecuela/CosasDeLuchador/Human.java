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
    }

    @Override
    public String getSpecialSkillName() {
        return "Dependencia";
    }


}
