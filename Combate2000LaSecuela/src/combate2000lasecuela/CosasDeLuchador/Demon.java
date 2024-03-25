package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Stack;

public class Demon extends Minion implements Serializable {
private Stack<Minion> pilaDemoniaca;
    public Demon(String linea) {
        super(linea);
        this.pilaDemoniaca=null;
    }

    public void setPilaDemoniaca(Stack<Minion> pilaDemoniaca) {
        this.pilaDemoniaca = pilaDemoniaca;
    }

    @Override
    public int getHealth() {
        return 0;
    }
}
