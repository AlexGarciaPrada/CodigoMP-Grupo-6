package combate2000lasecuela;

import java.util.Date;

import combate2000lasecuela.CosasDeLuchador.Fighter;

public class Combat implements Saveable {
    private Fighter challenger;
    private Fighter challenged;
    private int rounds;
    private Date date;
    private Fighter winner;
    private int goldGained;

    private String result;

    //private boolean esEmpate;
    //private String empate;


    public Combat(Fighter challenger, Fighter challenged, int rounds, int goldGained, String result) {
        this.challenger = challenger;
        this.challenged = challenged;
        this.rounds = rounds;
        this.date = new Date();
        this.winner = whoWin(challenger, challenged);
        this.goldGained = goldGained;
        //this.esEmpate=esEmpate;
        //this.empate=verSiEmpate();
        this.result = Result();
    }

    public Fighter whoWin(Fighter challenger, Fighter challenged) {
        if (challenger.getHealth() < challenged.getHealth()) {
            return challenged;
        } else if (challenger.getHealth() > challenged.getHealth()) {
            return challenger;
        } else return null;
    }


   /* public void setEmpate(String empate) {
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

    */

    public String Result() {
        if (this.winner == challenger) {
            return "Ganado";
        }
        else if (this.winner == challenged) {
            return "Perdido";
        }
        else return "Empate";
    }

    public Date getDate() {return date;}

    public Fighter getWinner () {return winner;}

    public int getGoldGained() {return goldGained;}

    public Fighter getChallenger() {return challenger;}

    public Fighter getChallenged() {return challenged;}

    public void setChallenger(Fighter challenger) {
        this.challenger = challenger;
    }

    public void setChallenged(Fighter challenged) {
        this.challenged = challenged;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setWinner(Fighter winner) {
        this.winner = winner;
    }

    public void setGoldGained(int goldGained) {
        this.goldGained = goldGained;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String getId() {
        return null;
    }
}



