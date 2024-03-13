package combate2000lasecuela;

import java.io.Serializable;

public interface Terminal extends Serializable{
    public abstract int read(); //y devuelve un int porque si da error devuelve -1

    public abstract void show(String s);

}
