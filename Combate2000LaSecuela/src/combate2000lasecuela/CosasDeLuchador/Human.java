package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;

import static java.lang.Integer.valueOf;

import static java.lang.Integer.valueOf;
public class Human extends Minion implements Serializable {
    public Human(String line) {
        super(line);
        String [] values = line.split(";");
    }

    @Override
    public String getSpecialSkillName() {
        return "Dependencia";
    }
}
