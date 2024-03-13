package combate2000lasecuela;

import java.io.Serializable;

public abstract class User implements Serializable,Saveable {

        private String name;
        private String password;
        private String nick;

        public User(String name, String password, String nick) {
            this.name = name;
            this.password = password;
            this.nick = nick;
        }

        // --------------------------------------   GETTERS AND SETTERS

    public String getName() {
        return name;
    }

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

}
