package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.*;

import java.util.LinkedList;


public class Operator extends User {

    public Operator(String name, String password, String nick) {
        super(name, password, nick);
    }

    //opciones editar personaje y equipo
    public boolean deleteMinion(Player player, int minionId) {
        boolean deleted = false;
        LinkedList<Minion> minList = player.getFighter().getMyMinions();
        if (minionId <= minList.size()) {
            minList.remove(minionId - 1);
            deleted = true;
        } return deleted;
    }

    public boolean addMinion(Player player, Minion minion) {
        boolean added = false;
        if (!containsMinion(player.getFighter().getMyMinions(), minion)) {
            player.getFighter().getMyMinion().add(minion);
            added = true;
        }
        return added;
    }

    public boolean containsMinion(LinkedList<Minion> myMins, Minion minion) {
        boolean found = false;
        //LinkedList<Minion> myMin = player.getFighter().getMyMinion();
        for (Minion minion1 : myMins) {
            if (minion1.equals(minion)) {
                found = true;
            } else if (minion1 instanceof Demon) {
                LinkedList<Minion> demMins = ((Demon) minion1).getDemonList();
                return containsMinion(demMins, minion);
            }
        } return found;
    }

    public boolean deleteWeapon(Player player, int elementId) {
        boolean deleted = false;
        LinkedList<Weapon> weaponList = player.getFighter().getMyWeapon();
        if (elementId <= weaponList.size()) {
            weaponList.remove(elementId - 1);
            deleted = true;
        } return deleted;
    }
    public boolean deleteArmor(Player player, int elementId) {
        boolean deleted = false;
        LinkedList<Armor> armorList = player.getFighter().getMyArmor();
        if (elementId <= armorList.size()) {
            armorList.remove(elementId-1);
            deleted = true;
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

    //opciones bloqueo/desbloqueo
    public void blockPlayer(Player player){
        player.setBlocked(true);
    }

    public void unblockPlayer(Player player){
        player.setBlocked(false);
    }


    @Override
    public String getId() {
        return null;
    }
}

