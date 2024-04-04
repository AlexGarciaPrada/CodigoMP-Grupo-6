package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Stack;

public class Demon extends Minion implements Serializable {
    private Stack<Minion> demonStack;
    public Demon(String line) {
        super(line);
        String [] values = line.split(";");
        this.demonStack =null;
    }

    @Override
    public String getSpecialSkillName() {
        return "Pacto";
    }

    public void setDemonStack(Stack<Minion> demonStack) {
        this.demonStack = demonStack;
    }
}
