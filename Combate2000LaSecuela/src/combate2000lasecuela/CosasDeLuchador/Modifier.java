package combate2000lasecuela.CosasDeLuchador;


import combate2000lasecuela.Saveable;

public abstract class Modifier implements Saveable {
    private String name;
    private String id;
    private int value;

    public Modifier(String line) {
        String [] parts = line.split(";");
        this.id = parts[0];
        this.name = parts[1];
        this.value = Integer.parseInt(parts[2].trim());
    }

    public String getId() {return id;}
}



