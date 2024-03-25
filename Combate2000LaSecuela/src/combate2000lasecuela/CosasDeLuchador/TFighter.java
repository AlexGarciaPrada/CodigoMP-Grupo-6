package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.Saveable;

import java.io.Serializable;

public class TFighter implements Saveable {
    private int suerteA;
    private int suerteW;
    private int suerteM;
    public TFighter(String linea){
        String [] valores = linea.split(";");
        this.suerteM = Integer.parseInt(valores[2]);
        this.suerteA = Integer.parseInt(valores[3]);
        this.suerteW = Integer.parseInt(valores[4]);
    }

    public int getSuerteA() {
        return suerteA;
    }

    public int getSuerteW() {
        return suerteW;
    }

    public int getSuerteM() {
        return suerteM;
    }

    @Override
    public String getId() {
        return null;
    }
}
