package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import combate2000lasecuela.Saveable;

public abstract class User implements Saveable {
        private String name;
        private String password;
        private String nick;
        private String registerNumber;

        public User(String name, String password, String nick) {
            this.name = name;
            this.password = password;
            this.nick = nick;
        }

        // --------------------------------------   GETTERS AND SETTERS

    @Override
    public String getId() {return this.registerNumber;}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

}
