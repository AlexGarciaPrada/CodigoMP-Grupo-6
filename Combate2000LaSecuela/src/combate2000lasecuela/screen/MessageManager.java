package combate2000lasecuela.screen;
import combate2000lasecuela.screen.MessageManager;

public class MessageManager {
    private Terminal t;

    public MessageManager() {
        t = new Textterminal();
    }

    public void showInitMenu(){
        String [] content ={"Bienvenido a Combate2000","1.Iniciar Sesion","2. Registro","3.Salir"};
        showContent(content);
    }

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
