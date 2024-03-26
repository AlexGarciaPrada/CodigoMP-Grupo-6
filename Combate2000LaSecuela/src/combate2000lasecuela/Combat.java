package combate2000lasecuela;

import java.util.Date;


import combate2000lasecuela.CosasDeLuchador.Fighter;
import combate2000lasecuela.Saveable;


public class Combat implements Saveable {
    private Fighter challenger;
    private Fighter challenged;
    private int rounds;
    private Date date;
    private Fighter winner;
    private int goldGained;
    private boolean esEmpate;
    private String empate;


    public Combat(Fighter challenger, Fighter challenged, int rounds, int goldGained, boolean esEmpate) {
        this.challenger = challenger;
        this.challenged = challenged;
        this.rounds = rounds;
        this.date = new Date();
        this.winner = whoWin(challenger, challenged);
        this.goldGained = goldGained;
        this.esEmpate=esEmpate;
        this.empate=verSiEmpate();
    }

    public Fighter whoWin(Fighter challenger, Fighter challenged) {
        if (challenger.getHealth() < challenged.getHealth()) {
            return challenged;
        } else if (challenger.getHealth() > challenged.getHealth()) {
            return challenger;
        } else return null;
    }

    public Fighter getWinner () {return this.winner;}

    public int getGoldGained() {return goldGained;}

    public Fighter getChallenger() {return challenger;}

    public Fighter getChallenged() {return challenged;}
    @Override
    public String getId() {
        return null;
    }

    public void setEmpate(String empate) {
        this.empate = empate;
    }

    public boolean getEsEmpate() {
        return this.esEmpate;
    }

    public String getEmpate() {
        return this.empate;
    }

    public String verSiEmpate(){
        if (getEsEmpate()) {
            setEmpate("Empate");
            return getEmpate();
        }else{
            setEmpate("Hubo ganador");
            return getEmpate();
        }
    }
}



