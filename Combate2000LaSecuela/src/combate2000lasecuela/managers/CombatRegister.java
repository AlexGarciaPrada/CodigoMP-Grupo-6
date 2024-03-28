package combate2000lasecuela.managers;

import combate2000lasecuela.Combat;

import java.util.HashMap;
import java.util.Map;

public class CombatRegister extends AbstractManager<Combat> {

    public CombatRegister() {
        this.loadElement("Combat");
        if (this.elements==null){
            this.setElements(new HashMap<String, Map<String,Combat>>());
            this.addCollection("CombatMap",new HashMap<String,Combat>());
        }

    }





}


