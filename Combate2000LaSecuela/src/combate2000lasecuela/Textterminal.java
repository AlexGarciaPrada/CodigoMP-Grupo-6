package combate2000lasecuela;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Textterminal implements Terminal {
    Scanner sc;

    public Textterminal() {
        sc = new Scanner(System.in);
    }

    @Override
        public int read(int max) {
            int result = 0;
            while ((result <= 0) || (result > max)) {
                try {
                    result = sc.nextInt();
                } catch (InputMismatchException e) {
                    sc.nextLine();
                    result = 0;
                }
                if (result > max) {
                    result=0;
                }
            }
            return result;
        }


    @Override
    public void show(String s) {
        System.out.print(s);
    }
    @Override
    public void showln(String s) {
        System.out.println(s);
    }

    private void showf(String f,String s){
        System.out.printf(f,s);
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String readString() {
        String linea = sc.next();
        return linea;
    }
    private int getMaxLine(String [] content){
        int max = 0;
        for (String line : content) {
            if (line.length() > max) {
                max =line.length();
            }
        }
        return max;
    }
    public void showContent(String[] content) {
        int wide = getMaxLine(content) + 4;
        int height = content.length + 2;
        //Tapa de la caja
        showln("|" + "-".repeat(wide - 2) + "|");
        //Contenido de la caja
        for (String line : content) {
            showf("| %-" + (wide - 4) + "s |\n", "");
            showf("| %-" + (wide - 4) + "s |\n", line);

        }
        // Tapa inferior
        showln("|" + "-".repeat(wide - 2) + "|");
    }

}

