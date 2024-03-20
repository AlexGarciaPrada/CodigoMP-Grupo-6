package combate2000lasecuela.managers;

import combate2000lasecuela.Saveable;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

public class AbstractManager <T extends Saveable>{  // T es el tipo de dato (challenges, users...)
    protected Map<String,Map<String,T>> elements;

    public AbstractManager() {};

    // --------------------------------- METHODS TO MANAGE MAPS
    public void addCollection(String mapName, Map map){             //añadir mapas dentro de otro manager (mapa de players y mapa de operadores)
        this.elements.put(mapName,map);
    }

    public void addElement(String type, String mapKey, T element){      //PARA AÑADIR INSTANCIAS
        this.elements.get(type).put(mapKey,element);
    }

    public T deleteElement(String type, String mapKey){
        return this.elements.get(type).remove(mapKey);
    }

    public void addElementSubMap(String submap, String key, T element){
        this.elements.get(submap).put(key, element);
    }

    // --------------------------------- SERIALIZATION METHODS
    public void saveElement(T element){
        try {
            String route = String.format("./config/%s/", element.getClass());
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(route + element.getId() + ".xml")));
            encoder.writeObject(element);  //writeObject recibe un Object, por eso no hay que hacer el casting
            encoder.close();
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("ERROR SAVING");
        }
    }

    public T loadElement(String fileName) {
        try {
            String route = String.format("./config/%s/", this.getType());
            XMLDecoder decoder = new XMLDecoder(new FileInputStream(route+ fileName + ".xml"));    // Abre el archivo para lectura.
            T element = (T)decoder.readObject();  //readObject devuelve un Object, por eso hay que hacer el casting
            decoder.close();
            return element;
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR LOADING USER");
            return null;
        }
    }

    private String getType(){
        return String.valueOf((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    // --------------------------------- GETTERS AND SETTERS

    public Map<String, Map<String,T>> getElements() {
        return elements;
    }
    public void setElements(Map<String, Map<String,T>> elements) {
        this.elements = elements;
    }

    //------------------------------------ PARA SABER SI UN ELEMENTO ESTÁ EN EL MAPA
    public boolean isInTheMap(String submap, String key){
         if (this.elements.get(submap) == null){
             return false;
         }
         return this.elements.get(submap).containsKey(key);
    }
}
