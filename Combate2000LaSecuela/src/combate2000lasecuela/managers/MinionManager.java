package combate2000lasecuela.managers;

import combate2000lasecuela.CosasDeLuchador.Minion;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.lang.Integer;
public class MinionManager extends AbstractManager<Minion> {

    public MinionManager() {
        this.setElements(new HashMap<String, Map<String,Minion>>());
        this.addCollection("MinionMap",new LinkedHashMap<String, Minion>());
    }
}
    

