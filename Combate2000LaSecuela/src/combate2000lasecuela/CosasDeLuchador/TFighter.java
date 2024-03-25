package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.Saveable;

import java.io.Serializable;

public class TFighter implements Saveable {
    private String name;
    private int suerteA;
    private int suerteW;
    private int suerteM;
    public TFighter(String linea){
        String [] valores = linea.split(";");
        this.name=valores[1];
        this.suerteM = Integer.parseInt(valores[2].trim());
        this.suerteA = Integer.parseInt(valores[3].trim());
        this.suerteW = Integer.parseInt(valores[4].trim());
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

    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return null;
    }
}
