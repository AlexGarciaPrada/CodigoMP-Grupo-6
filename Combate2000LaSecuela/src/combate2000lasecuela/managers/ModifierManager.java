package combate2000lasecuela.managers;


import combate2000lasecuela.CosasDeLuchador.Modifier;
import combate2000lasecuela.CosasDeLuchador.Strength;
import combate2000lasecuela.CosasDeLuchador.Weakness;

import java.util.HashMap;
import java.util.Map;

public class ModifierManager extends AbstractManager<Modifier> {
    public ModifierManager() {
        this.setElements(new HashMap<String, Map<String, Modifier>>());

        this.addCollection("StrengthMap",new HashMap<String, Strength>());
        this.addCollection("WeaknessMap",new HashMap<String, Weakness>());
    }

}



