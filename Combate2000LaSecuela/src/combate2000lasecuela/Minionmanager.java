package combate2000lasecuela;

import java.util.Collection;
import java.util.HashMap;

public class Minionmanager extends AbstractManager<User>{
    public Minionmanager() {
        this.setElements((Collection<User>) new HashMap<String,User>());
    }

    public void addMinion(Minion minion) {
    }
    public User deleteUser(String nick) {return ((HashMap<String,User>)this.elements).remove(nick);}

}
    

