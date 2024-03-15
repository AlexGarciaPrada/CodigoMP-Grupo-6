package combate2000lasecuela.managers;

import combate2000lasecuela.Combat;
import combate2000lasecuela.managers.AbstractManager;

import java.util.HashMap;
import java.util.Map;

public class CombatResgister extends AbstractManager<Combat> {

    public CombatResgister () {
        this.setElements(new HashMap<String, Map<String,Combat>>());
    }


}


