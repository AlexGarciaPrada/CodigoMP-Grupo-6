package combate2000lasecuela;

import combate2000lasecuela.managers.ChallengeManager;
import combate2000lasecuela.managers.UserManager;
import combate2000lasecuela.screen.Textterminal;


public class Operator extends User {

    private boolean validate;
    public Operator(String name, String password, String nick) {
        super(name, password, nick);
    }

    //opciones editar personaje y equipo
    public void changeName (Player player, String newName) {
        player.getFighter().setName(newName);
    }

    /* en estos metodos dudo porque aun no se que parametros me dara Alex

    public void changeSpecialSkill(Player player, String skill) {
        player.getFighter().setSpecialSkill(skill);
    }

    public void changeWeaponStack();

    public void changeArmorStack();

    public void changeMinionsStack();
     */

    /* en estos falta ver con Dani como ajustamos parametros

    public void changeActiveWeapon(Player player, int option) {
        player.getFighter().setWeapon1(option);
    }
    Hay dos armas activas pero el metodo puede ser el mismo


    public void changeActiveArmor(Player player, int option) {
        player.getFighter().setArmor(option);
    }
    */


    //aun no estan definidas las fortalezas y debilidades
    public void changeWeakness() {};

    public void changeStrength() {};

    public void changeGold (Player player, int amount) {
        player.getFighter().setGold(amount);
    }

    public void changeHealth(Player player, int health) {
        player.getFighter().setHealth(health);
    }

    public void changePower(Player player, int power) {
        player.getFighter().setPower(power);
    }

    //opciones bloqueo/desbloqueo
    public void blockPlayer(Player player){
        player.setBlocked(true);
    }

    public void unblockPlayer(Player player){
        player.setBlocked(false);
    }

    public boolean validateChallenge(boolean state) {
        return validate = state;
    }


   /* public void validateChallenge(String challengeKey){
        // mensaje del messagemanager enseñando los retos
        // mensaje del messagemanager preguntando a reto quiere validar y si quiere validar o no
        int option = terminal.read(2);
        ChallengeManager cm = new ChallengeManager();
        if(option == 1){
            // cm.loadElement(challengeKey).setAccepted(true);
            // LOAD ES PARA CARGAR COSAS AL INICIAR EL PROGRAMA
        }
        else if(option == 2){
            cm.deleteElement("Challenge", challengeKey);
        }
    }*/


    @Override
    public String getId() {
        return null;
    }
}
