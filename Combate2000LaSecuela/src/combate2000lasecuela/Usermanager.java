package combate2000lasecuela;

import java.util.HashMap;

public class Usermanager {
    private HashMap<String, User> users;

    public Usermanager() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getNick(), user);
    }

    public User deleteUser(String nick) {
        return users.remove(nick);
    }

    public User saveUser(User user) {

    }
}
