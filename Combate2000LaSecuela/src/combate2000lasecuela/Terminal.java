package combate2000lasecuela;

import java.io.Serializable;

public interface Terminal extends Serializable{
    public abstract int read(int max); //Lee un integer que debe estar en un intervalo

    public abstract void show(String s);
    public abstract void showln(String s);

}
