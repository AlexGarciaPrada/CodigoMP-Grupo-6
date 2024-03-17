package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;

public class TFighter implements Serializable {
    int suerteA;
    int suerteW;
    int suerteM;
    public TFighter(String linea){
        String [] valores = linea.split(";");
        this.suerteM = Integer.parseInt(valores[2]);
        this.suerteA = Integer.parseInt(valores[3]);
        this.suerteW = Integer.parseInt(valores[4]);
    }
}
