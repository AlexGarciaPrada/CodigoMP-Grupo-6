package combate2000lasecuela;

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
        String [] data = {Constants.youwerechallenged+challenger.getName(),Constants.betamount+ Integer.toString(getGold()),Constants.confirmchallenge,Constants.acceptchallenge,Constants.rejectchallenge};
        return data;
    }
 }
