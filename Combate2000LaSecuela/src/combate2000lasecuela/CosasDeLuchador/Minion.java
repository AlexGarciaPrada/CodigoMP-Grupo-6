package combate2000lasecuela.CosasDeLuchador;


import combate2000lasecuela.Saveable;

public abstract class Minion implements Saveable {
    private String name;
    private int health;
    private String tipo;
    private int id;

    public Minion(String linea){
        String [] valores = linea.split(";");
        this.id=Integer.parseInt(valores[0]);
        this.name = valores[1];
        this.tipo = valores[2];
    }

    @Override
    public String getId() {
        return Integer.toString(this.id);
    }

    //------------------------ GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract int getHealth();

    public void setHealth(int health) {
        this.health = health;
    }

    public String getTipo() { return this.tipo;}

}
