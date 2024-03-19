package combate2000lasecuela.screen;
import combate2000lasecuela.screen.MessageManager;
import combate2000lasecuela.screen.Textterminal;

import static combate2000lasecuela.Constants.*;

public class MessageManager {
    private Terminal t;

    public MessageManager() {
        t = new Textterminal();
    }

    //Pantallas concretas con caja
    public int showInitMenu(){
        showContent(initMenuText);
        return (t.read(3));
    }
    public int showUserType(){
        showContent(userTypeMenuText);
        return (t.read(3));
    }
    public void showNickUsed(){
        showContent(nickUsedText);
    }
    public void showUserNotFound(){
        showContent(userNotFoundText);
    }
    public void showWrongPassword(){
        showContent(wrongPasswordText);
    }
    public void showNotCoincidencePassword() {
        showContent(notCoincidencePasswordText);
    }
    public void showUserRegistered(String user) {
        String[] content = {userRegistered1+user+userRegistered2};
        showContent(content);
    }

    //Pantallas sin caja
    public void showLogInMenu(){
    t.showln(logInText);
    t.showln(exitAdvice);
    }
    public String showReadPassword(){
        t.show(passwordText);
        return t.readString();
    }
    public String showReadNick(){
        t.show(nickText);
        return t.readString();
    }
    public String showReadName(){
        t.show(nameText);
        return t.readString();
    }
    public String showReadConfirmPassword(){
        t.show(confirmPasswordText);
        return t.readString();
    }

    public void showRegisterMenu(){
        t.showln(registerText);
        t.showln(exitAdvice);
    }

    //Funcionamiento interno de las pantallas
    private int getMaxLine(String[] content) {
        int max = 0;
        for (String line : content) {
            if (line.length() > max) {
                max = line.length();
            }
        }
        return max + 12; // Añade 12 para tener en cuenta los espacios a cada lado
    }

    private void showContent(String[] content) {
        int wide = getMaxLine(content);
        //Tapa de la caja
        t.showln("|" + "-".repeat(wide) + "|");
        //Contenido de la caja
        for (String line : content) {
            t.showf("|%-" + (wide - 1) + "s|\n", " ".repeat(6) + line + " ".repeat(wide - line.length() - 6));
            t.showln("|" + " ".repeat(wide) + "|");
        }
        // Tapa inferior
        t.showln("|" + "-".repeat(wide) + "|");
    }


}
