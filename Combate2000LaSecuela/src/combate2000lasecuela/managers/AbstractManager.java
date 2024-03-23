package combate2000lasecuela.managers;

import combate2000lasecuela.Saveable;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
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
    public void saveCollection(String className, Map<String,Map<String,T>> bigMap){
        String filePath = String.format("./config/%s.ser", className);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(bigMap);        // Serializa el HashMap llamado 'users'
            System.out.println("Datos de usuarios serializados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map loadElement(String fileName) {
        try {
            String route = String.format("./config/%s.ser", fileName);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(route));
            Map element = (Map) ois.readObject();
            ois.close();
            return element;
        }
        catch (IOException e) {
            System.out.println("ERROR LOADING");
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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

    public Map<String, T> getCollection(String type) {return elements.get(type);}

    //------------------------------------ PARA SABER SI UN ELEMENTO ESTÁ EN EL MAPA
    public boolean isInTheMap(String submap, String key){
         if (this.elements.get(submap) == null){
             return false;
         }
         return this.elements.get(submap).containsKey(key);
    }

}
