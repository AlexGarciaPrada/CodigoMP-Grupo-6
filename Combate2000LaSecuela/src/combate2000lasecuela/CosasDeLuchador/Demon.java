package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Stack;

public class Demon extends Minion implements Serializable {

    private String Pact;
private Stack<Minion> pilaDemoniaca;

    public Demon(String linea) {
        super(linea);
        String [] valores = linea.split(";");
        this.pilaDemoniaca=null;
        this.Pact = valores[3];
    }

    public void setDemonStack(Stack<Minion> pilaDemoniaca) {
        this.pilaDemoniaca = pilaDemoniaca;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    public String getPact() {return this.Pact;}
}
