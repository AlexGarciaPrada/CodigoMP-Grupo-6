package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Stack;

public class Demon extends Minion implements Serializable {
    private Stack<Minion> pilaDemoniaca;
    private MinionAttributes addedAttribute = MinionAttributes.Pacto;

    public Demon(String linea) {
        super(linea,MinionAttributes.Pacto);
        String [] valores = linea.split(";");
        this.pilaDemoniaca=null;
        this.addedAttribute.setValue(valores[3]);
    }

    public void setDemonStack(Stack<Minion> pilaDemoniaca) {
        this.pilaDemoniaca = pilaDemoniaca;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    public MinionAttributes getAddedAttribute() {
        return addedAttribute;
    }
    public void setAddedAttribute(MinionAttributes addedAttribute) {
        this.addedAttribute = addedAttribute;
    }
}
