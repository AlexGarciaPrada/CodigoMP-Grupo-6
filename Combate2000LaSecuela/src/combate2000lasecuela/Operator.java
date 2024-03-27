package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.CosasDeLuchador.Minion;
import combate2000lasecuela.CosasDeLuchador.Specialskill;
import combate2000lasecuela.CosasDeLuchador.Weapon;
import combate2000lasecuela.managers.ChallengeManager;
import combate2000lasecuela.managers.UserManager;
import combate2000lasecuela.screen.Textterminal;

import java.util.LinkedList;
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

    public void changeSpecialSkill(Player player, Specialskill skill) {
        player.getFighter().changeSpecialSkill(skill);
    }


    //para añadir arma a la lista (no al fichero), Alex me pasa la lista y la arma (elegida del fichero) que quiere añadirse
    public void addWeapon(LinkedList<Weapon> MyWeapons, Weapon weapon) {
        MyWeapons.add(weapon);
    };

    //para añadir armadura a la lista (no al fichero), Alex me pasa la lista y la armadura (elegida del fichero) que quiere añadirse
    public void addArmor(LinkedList<Armor> MyArmor, Armor armor) {
        MyArmor.add(armor);
    };

    //para añadir minion a la pila (no al fichero), Alex me pasa la pila y el minion (elegido del fichero) que quiere añadirse
    public void addMinion(Stack<Minion> MyMinions, Minion minion) {
        MyMinions.push(minion);
    };

    //para eliminar arma de la lista (no al fichero), Alex me pasa la lista y la arma (elegida del fichero) que quiere eliminarse
    public void deleteWeapon(LinkedList<Weapon> MyWeapons, Weapon weapon) {
        MyWeapons.remove(weapon);
    };

    //para eliminar armadura de la lista (no al fichero), Alex me pasa la lista y la armadura (elegida del fichero) que quiere eliminarse
    public void deleteArmor(LinkedList<Armor> MyArmor, Armor armor) {
        MyArmor.remove(armor);
    };

    //para eliminar minion de la pila (no al fichero), Alex me pasa la pila y se elimina la cima
    public void deleteMinion(Stack<Minion> MyMinions, Minion minion) {
        MyMinions.pop();
    };


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

    public boolean validateChallenge(Challenge c , int option) { // option = 1 (aceptar desafio). opption = 2 (denegar desafio)
        Player challenged = c.getChallenged();
        switch (option) {
            case 1:
                challenged.addPendingChallenge(c);
                return true;
            case 2:
                return false;
        }
        return false;
    }

    public void changeActiveWeapon(Player player, LinkedList<Weapon> MyWeapons, String weapon) {
        super.changeActiveWeapon(player, MyWeapons, weapon);
    }

    public void changeActiveArmor(Player player, LinkedList<Armor> MyArmor, int option) {
        super.changeActiveArmor(player, MyArmor, option);
    }


    @Override
    public String getId() {
        return null;
    }
}

