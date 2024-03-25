package combate2000lasecuela.managers;

import combate2000lasecuela.Challenge;
import combate2000lasecuela.managers.AbstractManager;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChallengeManager extends AbstractManager<Challenge> {

    public ChallengeManager() {
        this.setElements(new LinkedHashMap<String, Map<String, Challenge>>());
    }
}