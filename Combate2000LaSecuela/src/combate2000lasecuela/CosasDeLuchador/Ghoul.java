package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;

import static java.lang.Integer.valueOf;

public class Ghoul extends Minion implements Serializable {

    public Ghoul(String linea) {
        super(linea);
            String [] valores = linea.split(";");
        }
    @Override
    public String getSpecialSkillName() {
        return "Lealtad";
    }
}
