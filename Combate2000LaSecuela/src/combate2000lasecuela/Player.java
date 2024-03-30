package combate2000lasecuela;

import java.util.LinkedList;
import java.util.Random;

import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.CosasDeLuchador.Fighter;
import combate2000lasecuela.CosasDeLuchador.Weapon;

public class Player extends User {
    private String registerNumber;
    private int victories;
    private boolean blocked;
    private Fighter fighter;


    public Player(String name, String password, String nick) {
        super(name, password, nick);
        //this.registerNumber = generateRegisterNum(num);
        this.victories=0;
        this.blocked = false;
        this.fighter =null;
    }
    /*
    public void Fight(Player playerDesafiado, int oroApostado){
        int opcion=0;
        if (haveFighter(this)&&(haveFighter(playerDesafiado))) {
            Fighter desafiante = this.getFighter();
            Fighter desafiado = playerDesafiado.getFighter();
            if (desafiante.getArmadura()==null) {
                getNumero(opcion);
                changeActiveArmor(this,desafiante.getMyArmo(),opcion);
            }else if (desafiante.getArma1()==null){r
                changeActiveWeapon(this,desafiante.getMyWeapon(),getArma(opcion));
            }

            if (desafiado.getArmadura()==null) {
                getNumero(opcion);
                changeActiveArmor(playerDesafiado,desafiado.getMyArmor(),opcion);
            }else if (desafiado.getArma1()==null){
                changeActiveWeapon(playerDesafiado,desafiado.getMyWeapon(),getArma(opcion));
            }
            //comprobaciones que petarían el combater terminadas
            Combat combate= desafiado.startFighting(desafiante,oroApostado);
            //falta guardar en el registro de combates el resultado
        }
    }
    public String getArma(int opcion){
        return Integer.toString(opcion);
    }
    public int getNumero(int opcion){
        //desde el GameFLow tengo que recibir el scanner de la opcion
        return opcion;
    }

     */
    public int whoGetsGold(Combat c) {
        if (c.getResult().equals(Constants.isTie)) {
            return 0;
        }
        else if (c.getResult().equals(this.fighter.getName())) {
            victories++;
            return c.getGoldGained();
        } else return -c.getGoldGained();
    }

    public Combat Fight(Player challenger, int gold) {
        return getFighter().startFighting(challenger.getFighter(), gold);
    }

    public void createFighter(Fighter  fighter){
        if (this.fighter == null) {
            this.fighter = fighter;
        }
    }

    private String generateRegisterNum(int num) {
        Random r = new Random();

        char letter1 = (char) ('A' + r.nextInt(26));
        char letter2 = (char) ('A' + r.nextInt(26));
        char letter3 = (char) ('A' + r.nextInt(26));

        int num1 = r.nextInt(8) + 1;
        int num2 = num;

        return String.format("%c%d%d%c%c", letter1, num1, num2, letter2, letter3);
    }

    public void deleteFighter() {fighter = null;}

    /*
    public Challenge challengePlayer(Player challenged, int gold) {
        if (!this.isBlocked() && !challenged.isBlocked()) {
            if (this.fighter != null && challenged.getFighter() != null) {
                if (this.getFighter().getGold() > gold && challenged.getFighter().getGold() > gold) {
                    return new Challenge(this,challenged, gold);
                } else return null;
            } else return null;
        } else return null;
    }
*/ // se podia reducir el numero de ifs
    public Challenge challengePlayer(Player challenged, int gold) {
                return new Challenge(this, challenged, gold);
    }

    /*
    public void updateAfterCombat(Combat c, Fighter f) {
        if (c.getChallenger() == c.getWinner()) {
            int actualGold = c.getChallenger().getGold();
            actualGold += c.getGoldGained();
            c.getChallenger().setGold(actualGold);
            c.getChallenged().setGold(-c.getGoldGained());
        }
        else if (c.getChallenged() == c.getWinner()) {
            int actualGold = c.getChallenged().getGold();
            actualGold += c.getGoldGained();
            c.getChallenged().setGold(actualGold);
            c.getChallenger().setGold(-c.getGoldGained());
        }
    }
*/


    //para saber si se puede realizar un combate antes de llamar a player.fight
    public boolean hasActiveEquipment() {
        return this.getFighter().hasActiveEquipment();
    }

    public void rejectingChallenge(int gold){
        this.getFighter().setGold(this.getFighter().getGold()- (int) (gold*0.1));
    }



    public boolean hasPendingChallenges(){
        if (this.getFighter()==null){
            return false;
        }else{
            return !(this.getFighter().getPendingChallenges().isEmpty());
        }

    }
    public void addPendingChallenge(Challenge challenge){
        this.getFighter().getPendingChallenges().addChallenge(challenge);

    }
    public void deletePendingChallenge(){
        this.getFighter().getPendingChallenges().deleteChallenge();
    }


    public int getVictories() {return victories;}

    public Fighter getFighter() {return fighter;}

    public void setBlocked(boolean state) {blocked = state;}

    public boolean isBlocked() {return blocked;}

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }

    public void setVictories(int victories) {this.victories = victories;  }
}

