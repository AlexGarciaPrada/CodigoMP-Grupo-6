package combate2000lasecuela.managers;

import combate2000lasecuela.CosasDeLuchador.Item;
import combate2000lasecuela.CosasDeLuchador.Armor;
import combate2000lasecuela.CosasDeLuchador.Weapon;
import combate2000lasecuela.CosasDeLuchador.Minion;
import java.util.HashMap;
import java.util.Map;
import java.lang.Integer;
public class ItemManager extends AbstractManager<Item> {
    public static HashMap<Integer, Armor> armorMap;
    public static HashMap<Integer, Weapon> weaponMap;
    public ItemManager() {
        this.setElements(new HashMap<String, Map<String,Item>>());

    }
}
