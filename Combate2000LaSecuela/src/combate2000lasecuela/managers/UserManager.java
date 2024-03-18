package combate2000lasecuela.managers;

import combate2000lasecuela.Player;
import combate2000lasecuela.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager extends AbstractManager<User> {
    public UserManager() {
        this.setElements(new HashMap<String, Map<String,User>>());

        this.addCollection("Player",new HashMap<String, Player>());
        this.addCollection("Operator",new HashMap<String,Player>());
    }

}