package combate2000lasecuela.managers;

import combate2000lasecuela.Item;
import combate2000lasecuela.managers.AbstractManager;

import java.util.HashMap;
import java.util.Map;

public class ItemManager extends AbstractManager<Item> {
    public ItemManager() {
        this.setElements(new HashMap<String, Map<String,Item>>());

    }
}
