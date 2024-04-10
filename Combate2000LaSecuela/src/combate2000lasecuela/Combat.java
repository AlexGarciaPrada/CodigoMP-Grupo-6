package combate2000lasecuela;

import java.util.Date;

import combate2000lasecuela.CosasDeLuchador.Fighter;

public class Combat implements Saveable {
    private final Fighter challenger;
    private final Fighter challenged;
    private final int rounds;
    private final Date date;
    private  Fighter winner;
    private  Fighter loser;
    private final int goldGained;
    private final String [] result;
    private boolean desafiadoEsGanador;

    public Combat(Fighter challenger, Fighter challenged, int rounds, int goldGained,boolean desafiadoEsGanador) {
        this.challenger = challenger;
        this.challenged = challenged;
        this.rounds = rounds;
        this.date = new Date();
        this.goldGained = goldGained;
        this.result = result();
        this.desafiadoEsGanador=desafiadoEsGanador;

    }


    public String [] result() {
        String [] text = new String[1];
        if (desafiadoEsGanador) {
            text[0]="Ha ganado "+challenged.getName();
            setWinner(challenged);
            setLoser(challenger);
            return text; }
        else if (!desafiadoEsGanador) {
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

    public String [] getResult() {
        return result;
    }

    @Override
    public String getId() {
        return null;
    }
}



