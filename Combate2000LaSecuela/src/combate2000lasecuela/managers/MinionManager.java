package combate2000lasecuela.managers;

import combate2000lasecuela.Minion;
import combate2000lasecuela.managers.AbstractManager;

import java.util.HashMap;
import java.util.Map;

public class MinionManager extends AbstractManager<Minion> {
    public MinionManager() {
        this.setElements(new HashMap<String, Map<String,Minion>>());
    }
}
    

