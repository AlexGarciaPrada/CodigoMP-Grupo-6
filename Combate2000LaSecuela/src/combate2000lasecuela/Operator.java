package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.CosasDeLuchador.Minion;
import combate2000lasecuela.CosasDeLuchador.Specialskill;
import combate2000lasecuela.CosasDeLuchador.Weapon;
import combate2000lasecuela.managers.ChallengeManager;
import combate2000lasecuela.managers.UserManager;
import combate2000lasecuela.screen.Textterminal;

import java.util.Iterator;
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

    public boolean deleteMinion(Player player, String minionName) {
        boolean deleted = false;
        for (Minion minion: player.getFighter().getMyMinions()) {
            if (minion.getName().equals(minionName) && minion.isEquipped()) {
                player.getFighter().getMyMinions().remove(minion);
                deleted = true;
            }
        }
        return deleted;
    }

    public boolean addMinion(Player player, String minionName) {
        boolean added = false;
        for (Minion minion: player.getFighter().getMyMinions()) {
            if (minion.getName().equals(minionName) && !minion.isEquipped()) {
                player.getFighter().getMyMinions().push(minion);
                added = true;
            }
        }
        return added;
    }

    //para eliminar armadura de la lista (no al fichero), Alex me pasa la lista y la armadura (elegida del fichero) que quiere eliminarse
    public boolean deleteElement(Player player, String elementName) {
        boolean deleted = false;
        for (Armor armor: player.getFighter().getMyArmor()) {
            if (armor.getName().equals(elementName) && armor.isEquipped()) {
                player.getFighter().getMyArmor().remove(armor);
                deleted = true;
            }
        }
        if (!deleted) {
            for (Weapon weapon: player.getFighter().getMyWeapon()) {
                if (weapon.getName().equals(elementName) && weapon.isEquipped()) {
                    player.getFighter().getMyWeapon().remove(weapon);
                    deleted = true;
                }
            }
        } return deleted;
     }

     public boolean addElement(Player player, String elementName) {
         boolean added = false;
         for (Armor armor: player.getFighter().getMyArmor()) {
             if (armor.getName().equals(elementName) && !armor.isEquipped()) {
                 player.getFighter().getMyArmor().add(armor);
                 added = true;
             }
         }
         if (!added) {
             for (Weapon weapon: player.getFighter().getMyWeapon()) {
                 if (weapon.getName().equals(elementName) && !weapon.isEquipped()) {
                     player.getFighter().getMyWeapon().add(weapon);
                     added = true;
                 }
             }
         } return added;

     }




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

