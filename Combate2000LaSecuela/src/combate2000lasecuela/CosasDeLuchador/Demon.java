package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Stack;

public class Demon extends Minion implements Serializable {
    private Stack<Minion> pilaDemoniaca;
    private MinionAttributes addedAttribute = MinionAttributes.Pacto;

    public Demon(String linea) {
        super(linea);
        String [] valores = linea.split(";");
        this.pilaDemoniaca=null;
    }

    @Override
    public String getSpecialSkillName() {
        return "Pacto";
    }

    public void setDemonStack(Stack<Minion> pilaDemoniaca) {
        this.pilaDemoniaca = pilaDemoniaca;
    }



}
