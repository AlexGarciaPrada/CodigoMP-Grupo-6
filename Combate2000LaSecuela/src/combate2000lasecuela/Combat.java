package combate2000lasecuela;

import java.util.Date;

import combate2000lasecuela.CosasDeLuchador.Fighter;

public class Combat implements Saveable {
    private Fighter challenger;
    private Fighter challenged;
    private int rounds;
    private Date date;
    private Fighter winner;
    private Fighter loser;
    private int goldGained;
    private String [] result;

    public Combat(Fighter challenger, Fighter challenged, int rounds, int goldGained) {
        this.challenger = challenger;
        this.challenged = challenged;
        this.rounds = rounds;
        this.date = new Date();
        this.goldGained = goldGained;
        this.result = result();
    }


    public String [] result() {
        String [] text = new String[1];
        if (challenger.getHealth() < challenged.getHealth()) {
            text[0]="Ha ganado "+challenged.getName();
            setWinner(challenged);
            setLoser(challenger);
            return text; }
        else if (challenger.getHealth() > challenged.getHealth()) {
            text[0]="Ha ganado "+challenger.getName();
            setWinner(challenger);
            setLoser(challenged);
            return text;
        }else {
            return Constants.isTie;
        }

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

    public void setWinner(Fighter winner) {
        this.winner = winner;
    }

    public void setLoser(Fighter loser) {
        this.loser = loser;
    }

    public Fighter getWinner() {
        return winner;
    }

    public Fighter getLoser() {
        return loser;
    }

    public void setGoldGained(int goldGained) {
        this.goldGained = goldGained;
    }

    public String [] getResult() {
        return result;
    }

    public void setResult(String [] result) {
        this.result = result;
    }

    @Override
    public String getId() {
        return null;
    }
}



