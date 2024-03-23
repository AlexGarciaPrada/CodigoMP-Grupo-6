package combate2000lasecuela;

import combate2000lasecuela.managers.ChallengeManager;
import combate2000lasecuela.managers.UserManager;
import combate2000lasecuela.screen.Textterminal;


public class Operator extends User {

    private UserManager um = new UserManager();
    private Textterminal terminal = new Textterminal();

    public Operator(String name, String password, String nick) {
        super(name, password, nick);
    }

    public void blockPlayer(Player player){
        player.setBlocked(true);
    }

    public void unblockPlayer(Player player){
        player.setBlocked(false);
    }

    public void validateChallenge(String challengeKey){
        // mensaje del messagemanager enseñando los retos
        // mensaje del messagemanager preguntando a reto quiere validar y si quiere validar o no
        int option = terminal.read(2);
        ChallengeManager cm = new ChallengeManager();
        if(option == 1){
            cm.loadElement(challengeKey).setAccepted(true);

        }
        else if(option == 2){
            cm.deleteElement("Challenge", challengeKey);
        }
    }


    @Override
    public String getId() {
        return null;
    }
}
