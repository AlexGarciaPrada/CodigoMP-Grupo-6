package combate2000lasecuela;

import java.io.Serializable;

public class Operator extends User {

    public Operator(String name, String password, String nick) {
        super(name, password, nick);
    }

    @Override
    public String getId() {
        return null;
    }
}
