package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.CosasDeLuchador.Minion;
import combate2000lasecuela.CosasDeLuchador.Weapon;
import combate2000lasecuela.managers.ChallengeManager;
import combate2000lasecuela.managers.UserManager;
import combate2000lasecuela.screen.Textterminal;

import java.util.Stack;


public class Operator extends User {

    private boolean validate;
    public Operator(String name, String password, String nick) {
        super(name, password, nick);
    }

    //opciones editar personaje y equipo
    public void changeName (Player player, String newName) {
        player.getFighter().setName(newName);
    }

    /* a espera de que Dani haga linkedlist

    public void changeSpecialSkill(Player player, String skill) {
        player.getFighter().setSpecialSkill(skill);
    }


    //para añadir arma a la pila (no al fichero), Alex me pasa la pila y la arma (elegida del fichero) que quiere añadirse
    public void addWeapon(Stack<Weapon> MyWeapons, Weapon weapon) {
        MyWeapons.push(weapon);
    };

    //para añadir armadura a la pila (no al fichero), Alex me pasa la pila y la armadura (elegida del fichero) que quiere añadirse
    public void addArmor(Stack<Armor> MyArmor, Armor armor) {
        MyArmor.push(armor);
    };

    //para añadir minion a la pila (no al fichero), Alex me pasa la pila y el minion (elegido del fichero) que quiere añadirse
    public void addMinion(Stack<Minion> MyMinions, Minion minion) {
        MyMinions.push(minion);
    };


    /* en estos falta ver con Dani como ajustamos parametros

    public void changeActiveWeapon(Player player, int option) {
        player.getFighter().setWeapon1(option);
    }



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




    @Override
    public String getId() {
        return null;
    }
}
