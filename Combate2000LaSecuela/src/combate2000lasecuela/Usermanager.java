package combate2000lasecuela;

import java.util.Collection;
import java.util.HashMap;

public class Usermanager extends AbstractManager<User>{
    public Usermanager() {
        this.setElements((Collection<User>) new HashMap<String,User>());
    }
    @Override
    public void addElement(User user) {
        ((HashMap<String,User>)this.elements).put(user.getNick(), user);
    }
    public User deleteUser(String nick) {
        return ((HashMap<String,User>)this.elements).remove(nick);
    }
}