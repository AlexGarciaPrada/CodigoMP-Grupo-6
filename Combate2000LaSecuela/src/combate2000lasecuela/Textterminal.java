package combate2000lasecuela;

import java.util.Scanner;

public class Textterminal implements Terminal {
    Scanner sc;

    public Textterminal() {
        super();
        sc = new Scanner(System.in);
        //new TranslatorManager();
    }

    @Override
    public int read() {
        String i = sc.next();
        return Integer.parseInt(i);
    }

    @Override
    public void show(String s) {
        System.out.println(s);
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String readString() {
        String linea = sc.next();
        return linea;
    }
}

