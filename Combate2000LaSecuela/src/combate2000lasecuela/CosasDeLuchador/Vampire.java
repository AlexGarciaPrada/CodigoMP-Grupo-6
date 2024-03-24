package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.CosasDeLuchador.Fighter;

import java.io.Serializable;
import java.util.Stack;

public class Vampire extends Fighter implements Serializable {
    int suerteM;
    int suerteW;
    int suerteA;
    public Vampire(int suerteA, int suerteW, int suerteM) {
        super(suerteA, suerteW, suerteM);
        this.suerteA=suerteA;
        this.suerteM=suerteM;
        this.suerteW=suerteW;
    }
}
