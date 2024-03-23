package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.Fighter;
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


    //este metodo probablemente deba ir en gameFlow y cada opcion aqui debido a que se necesita messageManagr
    public void editCharacter(Player player, int option) {
        Fighter f = player.getFighter();
        switch(option) {
            case 1: //cambiar nombre
                break;
            case 2: //Cambiar habilidad
                break;
            case 3: //Cambiar set de armas
                break;
            case 4: //Cambiar set de armaduras
                break;
            case 5: //Cambiar set de esbirros
                break;
            case 6: //Cambiar cantidad de oro
                break;
            case 7: //Cambiar valor salud
                break;
            case 8: //Cambiar valor poder
                break;
            case 9: //Cambiar set debilidades
                break;
            case 10: //Cambiar set fortalezas
                break;
        }

    }

    public void changeName(Fighter f, String newName) {
        f.setName(newName);
    }

    public void changeGold(Fighter f, int gold) {
        f.setGold(gold);
    }

    public void changeHealth(Fighter f, int newHealth) {
        f.setHealth(newHealth);
    }

    public void changePower(Fighter f, int newPower) {
        f.setPower(newPower);
    }


    @Override
    public String getId() {
        return null;
    }
}
