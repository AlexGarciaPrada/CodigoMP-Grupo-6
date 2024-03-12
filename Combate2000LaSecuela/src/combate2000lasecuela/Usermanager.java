package combate2000lasecuela;

import java.util.HashMap;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Usermanager {
    private HashMap<String, User> users;

    public Usermanager() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getNick(), user);
    }

    public User deleteUser(String nick) {
        return users.remove(nick);
    }

    public static void saveUser(User user) {
            try {
                XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("./config/users/" + user.getNick() + ".xml")));
                encoder.writeObject(user);  //writeObject recibe un Object, por eso no hay que hacer el casting
                encoder.close();
            }
            catch (FileNotFoundException fileNotFoundException) {
                System.out.println("ERROR SAVING USER");
            }
        }

    public static User loadUsers(String usersFileName) {
        try {
            XMLDecoder decoder = new XMLDecoder(new FileInputStream("./config/users/" + usersFileName + ".xml"));    // Abre el archivo para lectura.
            User user = (User) decoder.readObject();  //readObject devuelve un Object, por eso hay que hacer el casting
            decoder.close();
            return user;
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR LOADING GAME");
            e.printStackTrace();
            return null;
        }
    }
}


