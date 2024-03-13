package combate2000lasecuela;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Iterator;

public class AbstractManager <T>{  // T es el tipo de dato (challenges, users...)
    private Collection<T> elements;

    public AbstractManager() {};

    public void addElement(T element){elements.add(element);}

    public void deleteElement(T element){elements.remove(element);}

    /*
    public Collection<T> saveElements(T element){
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("./config/%s/" + element.get + ".xml")));
            encoder.writeObject(element);  //writeObject recibe un Object, por eso no hay que hacer el casting
            encoder.close();
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("ERROR SAVING");
        }
    }
*/

}
