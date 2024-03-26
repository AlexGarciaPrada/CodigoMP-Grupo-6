package combate2000lasecuela;

import combate2000lasecuela.Saveable;
import java.io.Serializable;

public class Challenge implements Saveable {
    private Player challenger;
    private Player challenged;
    private int gold;

    public Challenge(Player challenger,Player challenged, int gold) {
        this.challenger=challenger;
        this.challenged=challenged;
        this.gold = gold;
    }

    @Override
    public String getId() {
        return null;
    }


    //------------------------ GETTERS & SETTERS

    public Player getChallenger() {
        return challenger;
    }

    public Player getChallenged() {
        return challenged;
    }

    public int getGold() {
        return gold;
    }

    public String [] getChallengeData(){
        String [] data = {"Has sido desafiado por el usuario "+challenger.getName(),"La apuesta es de una cantidad de oro de "+ Integer.toString(getGold()),"Confirmar desafio: ","1. Aceptar","2. Rechazar"};
        return data;
    }
 }
