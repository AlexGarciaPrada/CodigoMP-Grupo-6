package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;

public class Demon extends Minion implements Serializable {

    public Demon(String linea) {
        super(linea);
    }

    @Override
    public int getHealth() {
        return 0;
    }
}
