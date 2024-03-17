package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.CosasDeLuchador.Fighter;

import java.util.Stack;

public class Lycanthrope extends Fighter {
int suerteA;
int suerteW;
int suerteM;
int rage;
    public Lycanthrope(int suerteA, int suerteW, int rage, int suerteM) {
        super(suerteA, suerteW, suerteM);
        this.suerteA=suerteA;
        this.suerteM=suerteM;
        this.suerteW=suerteW;
        this.rage=0;
    }//a expensas del código de Laura
}
