package combate2000lasecuela.managers;

import combate2000lasecuela.CosasDeLuchador.TFighter;
import combate2000lasecuela.CosasDeLuchador.Fighter;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TFighterManager extends AbstractManager<TFighter> {
    public TFighterManager() {
        this.setElements(new HashMap<String, Map<String, TFighter>>());
        this.addCollection("TFighterMap", new LinkedHashMap<String, TFighter>());
    }
}
