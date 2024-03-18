package combate2000lasecuela.screen;
import combate2000lasecuela.screen.MessageManager;
import combate2000lasecuela.screen.Textterminal;
public class MessageManager {
    private Terminal t;

    public MessageManager() {
        t = new Textterminal();
    }

    //Pantallas concretas con caja
    public int showInitMenu(){
        String [] content ={"Bienvenido a Combate2000","1.Iniciar Sesion","2. Registro","3. Salir"};
        showContent(content);
        return (t.read(3));
    }
    public void showNickUsed(){
        String [] content ={"El Nick escogido esta en uso por otro usuario"};
        showContent(content);
    }
    public void showUserNotFound(){
        String[] content = {"No existe ningun usuario con el nick introducido","Comprueba que esta bien escrito o ve a la opcion de registro"};
        showContent(content);
    }
    public void showWrongPassword(){
        String[] content = {"Password incorrecta"};
        showContent(content);
    }
    public void showNotCoincidencePassword() {
        String[] content = {"Las password no coinciden", "Vuelve a intentarlo"};
        showContent(content);
    }
    public void showUserRegistered(String user) {
        String[] content = {"El usuario "+user+" ha sido registrado correctamente"};
        showContent(content);
    }

    //Pantallas sin caja
    public void showLogInMenu(){
    t.showln("Inicio de Sesion");
    t.showln("(En caso de querer volver al menu anterior introduce SALIR en cualquiera de los campos)");
    }
    public String showReadPassword(){
        t.show("Password: ");
        return t.readString();
    }
    public String showReadNick(){
        t.show("Nick: ");
        return t.readString();
    }
    public String showReadConfirmPassword(){
        t.show("Confirm Password: ");
        return t.readString();
    }

    public void showRegisterMenu(){
        t.showln("Registro");
        t.showln("(En caso de querer volver al menu anterior introduce SALIR en cualquiera de los campos)");
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


    public String challengeMenu() {
        t.showln("Write the nick of the player you would like to challenge: ");
        return t.readString();
    }

    public int selectGold() {
        int max = 10000;
        t.showln("Write the amount of the gold you would like to bet: ");
        return t.read(max);
    }

}
