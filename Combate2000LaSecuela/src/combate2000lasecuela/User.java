package combate2000lasecuela;

import java.io.Serializable;
import java.util.LinkedList;

import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.CosasDeLuchador.Weapon;
import combate2000lasecuela.Saveable;
import combate2000lasecuela.screen.MessageManager;

public abstract class User implements Saveable {
        private final String name;
        private final String password;
        private final String nick;

        public User(String name, String password, String nick) {
            this.name = name;
            this.password = password;
            this.nick = nick;
        }

        // --------------------------------------   GETTERS AND SETTERS

    @Override
    public String getId() {return null}

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getNick() {
        return nick;
    }




}
