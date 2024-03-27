package combate2000lasecuela;

import java.io.Serializable;
import java.util.LinkedList;

import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.CosasDeLuchador.Weapon;
import combate2000lasecuela.Saveable;
import combate2000lasecuela.screen.MessageManager;

public abstract class User implements Saveable {
        private String name;
        private String password;
        private String nick;
        private String registerNumber;

        private MessageManager messageManager;

        public User(String name, String password, String nick) {
            this.name = name;
            this.password = password;
            this.nick = nick;
        }

        // --------------------------------------   GETTERS AND SETTERS

    @Override
    public String getId() {return registerNumber;}

    public String getName() {
        return name;
    }

    public MessageManager getMessageManager() {return messageManager;}

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void changeActiveWeapon(Player player, LinkedList<Weapon> MyWeapons, String weapon) {
        player.getFighter().elegirArma(MyWeapons, weapon);
    }

    public void changeActiveArmor(Player player, LinkedList<Armor> MyArmor, int option) {
        player.getFighter().elegirArmadura(MyArmor, option);
    }

}
