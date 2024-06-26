package combate2000lasecuela.managers;

import combate2000lasecuela.Challenge;
import combate2000lasecuela.managers.AbstractManager;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChallengeManager extends AbstractManager<Challenge> {

    public ChallengeManager() {
        loadElement("Challenge");
        if (this.elements==null){
        this.setElements(new HashMap<String, Map<String, Challenge>>());
        this.addCollection("ChallengeMap", new LinkedHashMap<String,Challenge>());
        }
    }
}