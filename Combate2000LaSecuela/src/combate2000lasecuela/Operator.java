package combate2000lasecuela;

import java.io.Serializable;
import java.util.Scanner;

import combate2000lasecuela.managers.ChallengeManager;
import combate2000lasecuela.managers.UserManager;
import combate2000lasecuela.screen.Textterminal;


public class Operator extends User {

    private UserManager um = new UserManager();
    private Textterminal terminal = new Textterminal();

    public Operator(String name, String password, String nick) {
        super(name, password, nick);
    }

    public Player blockPlayer(String nick){
        //mensaje del messagemanager preguntando a quien quiere bloquear
        return (Player) um.deleteElement("Player", nick);
    }

    public void unblockPlayer(String nick){
        //mensaje del messagemanager enseñando los usuarios y que devuelve a quien quiere desbloquear
        um.addElement("Player", nick, (Player) um.loadElement(nick));
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
