package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.CosasDeLuchador.Fighter;

import java.io.Serializable;
import java.util.Stack;

public class Vampire extends Fighter implements Serializable {
    int suerteM;
    int suerteW;
    int suerteA;
    public Vampire(int suerteA, int SuerteW, int SuerteM) {
        this.suerteA=suerteA;
        this.suerteM=suerteM;
        this.suerteW=suerteW;
    }
}
