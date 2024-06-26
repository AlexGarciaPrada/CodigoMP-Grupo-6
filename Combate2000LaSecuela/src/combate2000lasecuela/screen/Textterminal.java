package combate2000lasecuela.screen;

import combate2000lasecuela.screen.Terminal;
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
            while (result <= 0) {
                try {
                    show("Opcion: ");
                    result = sc.nextInt();
                    if (result==0){
                        showln("Introduce una opcion valida");
                    }
                } catch (InputMismatchException e) {
                    showln("ERROR: debes insertar un numero entero.");
                    sc.nextLine();
                    result = 0;
                }
                if (result > max) {
                    showln("Introduce una opcion valida");
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
    @Override
    public void showf(String format,String s) {
        System.out.printf(format,s);
    }


    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    @Override
    public String readString() {
        String linea = sc.next();
        return linea;
    }

}

