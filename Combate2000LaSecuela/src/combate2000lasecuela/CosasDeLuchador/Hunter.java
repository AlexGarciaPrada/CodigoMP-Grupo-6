package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Stack;

public class Hunter extends Fighter implements Serializable {
int suerteA;
int suerteM;
int suerteW;
int will;
    public Hunter(int suerteA, int suerteM, int suerteW, int will) {
        super(suerteA, suerteM,suerteW);
        this.suerteA=suerteA;
        this.suerteM=suerteM;
        this.suerteW=suerteW;
        this.will=will;
    }
}