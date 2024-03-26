package combate2000lasecuela.screen;

import java.io.Serializable;

public interface Terminal{
    public  int read(int max); //Lee un integer que debe estar en un intervalo
    public  void show(String s);
    public  void showln(String s);
    public  void showf(String f, String s);
    public String readString();

}
