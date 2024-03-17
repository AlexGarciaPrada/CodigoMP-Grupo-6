package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Stack;

public class Hunter extends Fighter implements Serializable {
int suerteA;
int suerteM;
int suerteW;
    public Hunter(int suerteA, int SuerteW, int SuerteM) {
        this.suerteA=suerteA;
        this.suerteM=suerteM;
        this.suerteW=suerteW;
    }
}
