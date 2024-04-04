package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Random;
public class Discipline extends Specialskill implements Serializable {
    int disciplineDamage;
    int costeSangre;
    int puntosDisponibles;
    public Discipline(){
       this.disciplineDamage=1; //se acaba de comer un nerfeo
       this.costeSangre=0;
    }
    public boolean usarDisciplina(int puntosDisponibles){
        Random random = new Random();
        int selection = random.nextInt(3)+1;
        setCosteSangre(selection);
        return (puntosDisponibles>=selection);
    }
//el motivo de hacerlo así es que las disciplinas cambian
    public void setCosteSangre(int costeSangre) {
        this.costeSangre = costeSangre;
    }
    public int getCosteSangre(){
        return this.costeSangre;
    }
    @Override
    public int getDamage() {
        return this.disciplineDamage;
    }
}
