package combate2000lasecuela;

import java.util.Date;

import combate2000lasecuela.CosasDeLuchador.Fighter;

public class Combat implements Saveable {
    private Fighter challenger;
    private Fighter challenged;
    private int rounds;
    private Date date;
    private String winner;
    private int goldGained;
    private String result;

    public Combat(Fighter challenger, Fighter challenged, int rounds, int goldGained) {
        this.challenger = challenger;
        this.challenged = challenged;
        this.rounds = rounds;
        this.date = new Date();
        this.goldGained = goldGained;
        this.result = result();
    }


    public String result() {
        if (challenger.getHealth() < challenged.getHealth()) {
            return challenged.getName();        }
        else if (challenger.getHealth() > challenged.getHealth()) {
            return challenger.getName();
        }
        else return Constants.isTie;
    }

    public Date getDate() {return date;}

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



