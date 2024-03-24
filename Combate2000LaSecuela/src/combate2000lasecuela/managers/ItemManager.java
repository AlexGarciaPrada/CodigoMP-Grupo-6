package combate2000lasecuela.managers;

import combate2000lasecuela.CosasDeLuchador.Item;
import combate2000lasecuela.CosasDeLuchador.Weapon;
import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.Player;
import combate2000lasecuela.User;


import java.util.HashMap;
import java.util.Map;
import java.lang.Integer;
public class ItemManager extends AbstractManager<Item> {
    public ItemManager() {
        this.setElements(new HashMap<String, Map<String, Item>>());

        this.addCollection("Weapon",new HashMap<String, Player>());
        this.addCollection("Armor",new HashMap<String,Player>());
    }
}
