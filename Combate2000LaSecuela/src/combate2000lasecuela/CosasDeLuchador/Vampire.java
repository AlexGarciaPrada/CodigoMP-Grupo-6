package combate2000lasecuela.CosasDeLuchador;

import combate2000lasecuela.CosasDeLuchador.Fighter;

import java.io.Serializable;
import java.util.Random;
import java.util.Stack;

public class Vampire extends Fighter implements Serializable {
    private int puntosSangre;
    private int age;
    private Discipline discipline;
    Random random = new Random();
    public Vampire(String name, TFighter type,String clase, Stack<Minion> myMinions,Stack<Armor> myArmor,Stack<Weapon> myWeapon) {
        super(name, type,clase, myMinions, myArmor, myWeapon);
        this.puntosSangre=0;
        this.discipline = new Discipline();
        this.age = random.nextInt(1500)+120;
    }
    public int aumentarSangre(){
        return this.puntosSangre+=1;
    }
    public int disminuirSangre(int cantidad){
        return (this.puntosSangre-cantidad);
    }
    public int SpecialAttack(){
        if (getDiscipline().usarDisciplina(getPuntosSangre())){
            disminuirSangre(discipline.getCosteSangre());
           return discipline.getDisciplineDamage();
        }
        return 0;
    }
    @Override
    public void ajusteHabilidad(int pA, int pD) {
        if (pA-5>=pD){
           setPuntosSangre(getPuntosSangre()+4);
        }
    }

    public int getPuntosSangre() {
        return this.puntosSangre;
    }
    public Discipline getDiscipline(){
        return this.discipline;
    }

    public void setPuntosSangre(int puntosSangre) {
        this.puntosSangre = puntosSangre;
    }
}
