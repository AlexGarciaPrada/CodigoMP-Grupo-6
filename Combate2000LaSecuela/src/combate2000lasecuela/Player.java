package combate2000lasecuela;

import java.util.Map;
import java.util.UUID;

import combate2000lasecuela.CosasDeLuchador.Fighter;
import combate2000lasecuela.CosasDeLuchador.TFighter;
import combate2000lasecuela.managers.ChallengeManager;
public class Player extends User {
    private String registerNumber;
    private int victories;
    private boolean blocked;

    private Fighter fighter;

    private boolean deleted;



    public Player(String name, String password, String nick) {
        super(name, password, nick);
        this.registerNumber = registerNumber;

        this.victories = victories;
        this.blocked = blocked;
        this.fighter = fighter;

        this.victories=0;
        this.blocked = false;
        this.fighter =null;
        this.deleted = false;

    }

    public int getVictories() {return victories;}

    public Fighter getFighter() {return fighter;}


    public void setBlocked() {blocked = true;}

    public void setBlocked(boolean state) {blocked = state;}


    public boolean isBlocked() {return blocked;}


    //estos dos para "darse de baja" (querer borrar la cuenta), en gameFlow se borrará el usuario del mapa
    public void setDeleted() {deleted = true;}

    public boolean isDeleted() {return deleted;}


    //Tendria que ser algo asi creo si Dani considera modificar el constructor de fighter

    //public void registerFighter(String name, int type) {
    //Fighter f = new Fighter(name, type);
    //setFighter(f);}

    public void setFighter(Fighter character) {fighter = character;}

    public void deleteFighter() {fighter = null;}

    public Challenge ChallengePlayer(Player challenged) {
        ChallengeManager cm = new ChallengeManager();
        cm.addElement("Challenge", generateRandomChallengeKey(), new Challenge());
        return cm.loadElement(generateRandomChallengeKey());
    }

    public static String generateRandomChallengeKey() {
        return UUID.randomUUID().toString();
    }

    //sigo pensando en si tiene sentido porque pendingChallenges tiene relacion con fighter

   // public void fight(Challenge c) {
    //    fighter.startFighting(c);
    //    }
}
