/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package combate2000lasecuela;

/**
 *
 * @author alexg
 */
public class MessageManager {
    private Textterminal t;

    public MessageManager() {
        t = new Textterminal();
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
