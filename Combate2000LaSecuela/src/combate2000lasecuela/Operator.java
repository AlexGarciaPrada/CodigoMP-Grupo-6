package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.CosasDeLuchador.Minion;
import combate2000lasecuela.CosasDeLuchador.Specialskill;
import combate2000lasecuela.CosasDeLuchador.Weapon;
import java.util.LinkedList;


public class Operator extends User {

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

    public boolean deleteMinion(Player player, int minionId) {
        boolean deleted = false;
        LinkedList<Minion> minionList = player.getFighter().getMyMinion();
        LinkedList<Minion> esclavoTemporal = new LinkedList<>();
        while (!minionList.isEmpty()) {
            Minion minion = minionList.remove();
            if (minion.getId().equals(Integer.toString(minionId))) {
                deleted = true;
                break;
            } else {
                esclavoTemporal.add(minion);
            }
        }
        while (!esclavoTemporal.isEmpty()) {
            minionList.add(esclavoTemporal.remove());
        }

        return deleted;
    }

    public boolean addMinion(Player player, Minion minion) {
        boolean added = false;
        if (!containsMinion(player, minion)) {
            player.getFighter().getMyMinion().add(minion);
            added = true;
        }
        return added;
    }

    public boolean containsMinion(Player player, Minion minion) {
        boolean found = false;
        LinkedList<Minion> myMin = player.getFighter().getMyMinion();
        for (Minion minion1 : myMin) {
            if (minion1.equals(minion)) {
                found = true;
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


    @Override
    public String getId() {
        return null;
    }
}

