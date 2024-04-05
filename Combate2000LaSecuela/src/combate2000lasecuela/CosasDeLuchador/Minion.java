package combate2000lasecuela.CosasDeLuchador;


import combate2000lasecuela.Saveable;

public abstract class Minion implements Saveable {
    private String name;
    private int health;
    private String tipo;
    private int id;
    private int minionId;
    private boolean equipped;
    private String specialSkill;

    public Minion(String linea){
        String [] valores = linea.split(";");
        this.id=Integer.parseInt(valores[0]);
        this.name = valores[1];
        this.tipo = valores[2];
        this.health = Integer.parseInt(valores[4].trim());
        this.setSpecialSkill(valores[3]);
    }

    @Override
    public String getId() {
        return Integer.toString(this.id);
    }

    public void setSpecialSkill(String skill){
        this.specialSkill = skill;
    }
    public abstract String getSpecialSkillName();

    //------------------------ GETTERS & SETTERS

    public void setHealth() {
        this.health = health;
    }
    public String getSpecialSkill() {
        return specialSkill;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth(){
        return this.health;
    }
    public String getTipo() { return this.tipo;}

}
