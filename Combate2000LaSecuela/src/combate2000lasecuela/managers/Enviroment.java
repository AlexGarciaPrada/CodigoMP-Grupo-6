package combate2000lasecuela.managers;

public class Enviroment {
    private static boolean testing;

    public Enviroment() {
        testing = getTesting();
    }

    public static boolean getTesting() {
        return testing;
    }

    public static void setTesting(boolean testing) {
        Enviroment.testing = testing;
    }
}
