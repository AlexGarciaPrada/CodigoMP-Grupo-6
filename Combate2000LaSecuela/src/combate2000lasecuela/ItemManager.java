package combate2000lasecuela;

import java.io.Serializable;

public class ItemManager extends AbstractManager<Item>{
    public ItemManager() {
        this.setElements(new java.util.ArrayList<Item>());
    }

    //public Item deleteItem (Item itemName) {return elements.remove(itemName);}
    
}
