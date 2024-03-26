package combate2000lasecuela.managers;

import combate2000lasecuela.Combat;
import combate2000lasecuela.Player;
import combate2000lasecuela.managers.AbstractManager;

import java.util.HashMap;
import java.util.Map;

public class CombatResgister extends AbstractManager<Combat> {

    public CombatResgister () {
        this.setElements(new HashMap<String, Map<String,Combat>>());
    }

    public void showCombatHistory(Player player) {
        Map<String, Map<String, Combat>> elements = this.getElements();

        if (elements.isEmpty()) {
            String [] data = {"No hay combates anteriores"};
        }
        else {
            for (Map<String, Combat> combatMap: elements.values()) {
                for (Combat combat: combatMap.values()) {
                    if (combat.getChallenger().equals(player.getFighter()) || combat.getChallenged().equals(player.getFighter())) {
                        String [] data = {"Fecha de combate: " + combat.getDate(), "Resultado de combate: " + combat.Result(), "Oro ganado/perdido: " + player.whogetsGold(combat)};
                    }

                }
            }
        }
    }



}


