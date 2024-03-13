package combate2000lasecuela;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;

public class AbstractManager <T extends Saveable>{  // T es el tipo de dato (challenges, users...)
    private Collection<T> elements;

    public AbstractManager() {};

    public void addElement(T element){elements.add(element);}

    public void deleteElement(T element){elements.remove(element);}


    public void saveElements(T element){
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
    public T saveElements(String fileName) {
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

}
