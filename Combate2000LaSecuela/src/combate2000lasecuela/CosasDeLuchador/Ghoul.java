package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;

import static java.lang.Integer.valueOf;

public class Ghoul extends Minion implements Serializable {
    int health;

    public Ghoul(String linea) {
        super(linea);
            String [] valores = linea.split(";");
//            this.health=Integer.parseInt(valores[4].trim());
//            setHealth(health);
        }

    @Override
    public String getSpecialSkillName() {
        return "Lealtad";
    }
}
