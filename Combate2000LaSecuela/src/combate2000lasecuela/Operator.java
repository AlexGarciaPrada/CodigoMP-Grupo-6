package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.CosasDeLuchador.Minion;
import combate2000lasecuela.CosasDeLuchador.Specialskill;
import combate2000lasecuela.CosasDeLuchador.Weapon;

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

    public boolean deleteMinion(Player player, String minionId) {
        boolean deleted = false;
        Stack<Minion> minionStack = player.getFighter().getMyMinion();
        Stack<Minion> temporaryStack = new Stack<>();
        while (!minionStack.isEmpty()) {
            Minion minion = minionStack.pop();
            if ((minion.getId().equals(minionId))){
                deleted = true;
                break;
            } else {
                temporaryStack.push(minion);
            }
        }
        while (!temporaryStack.isEmpty()) {
            minionStack.push(temporaryStack.pop());
        }

        return deleted;
    }

    public boolean addMinion(Player player, Minion minion) {
        boolean added = false;
        if (!containsMinion(player, minion)) {
            player.getFighter().getMyMinion().push(minion);
            added = true;
        }
        return added;
    }

    public boolean containsMinion(Player player, Minion minion) {
        boolean found = false;
        Stack<Minion> myMin = player.getFighter().getMyMinion();
        for (Minion minion1 : myMin) {
            if (minion1.equals(minion)) {
                found = true;
            }
        } return found;
    }

    public boolean deleteElement(Player player, String elementId) {
        boolean deleted = false;
        Iterator<Armor> armorIterator = player.getFighter().getMyArmor().iterator();
        while (armorIterator.hasNext()) {
            Armor armor = armorIterator.next();
            if (armor.getId().equals(elementId)) {
                armorIterator.remove();
                deleted = true;
                break;
            }
        }
        if (!deleted) {
            Iterator<Weapon> weaponIterator = player.getFighter().getMyWeapon().iterator();
            while (weaponIterator.hasNext()) {
                Weapon weapon = weaponIterator.next();
                if (weapon.getId().equals(elementId)) {
                    weaponIterator.remove();
                    deleted = true;
                    break;
                }
            }
        } return deleted;
    }


    public boolean addWeapon(Player player, Weapon element) {
        boolean added = false;
        if (!player.getFighter().getMyWeapon().contains(element)) {
            player.getFighter().getMyWeapon().add(element);
            added = true;
        } return added;
    }

    public boolean addArmor(Player player, Armor element) {
        boolean added = false;
        if (!player.getFighter().getMyArmor().contains(element)) {
            player.getFighter().getMyArmor().add(element);
            added = true;
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




    @Override
    public String getId() {
        return null;
    }
}

