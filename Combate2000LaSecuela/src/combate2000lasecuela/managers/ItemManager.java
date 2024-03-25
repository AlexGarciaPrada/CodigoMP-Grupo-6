package combate2000lasecuela.managers;

import combate2000lasecuela.CosasDeLuchador.Item;
import combate2000lasecuela.CosasDeLuchador.Weapon;
import combate2000lasecuela.CosasDeLuchador.Armor;

import java.util.HashMap;
import java.util.Map;
import java.lang.Integer;

public class ItemManager extends AbstractManager<Item> {
    public ItemManager() {
        this.setElements(new HashMap<String, Map<String, Item>>());

        this.addCollection("WeaponMap",new HashMap<String,Weapon>());
        this.addCollection("ArmorMap",new HashMap<String,Armor>());
    }
}
