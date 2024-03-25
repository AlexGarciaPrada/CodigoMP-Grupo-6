package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.Random;
public class Discipline implements Serializable {
    int disciplineDamage;
    int costeSangre;
    int puntosDisponibles;
    public Discipline(){
       this.disciplineDamage=2; //por ejemplo
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
    public int getDisciplineDamage() {
        return this.disciplineDamage;
    }
}
