package combate2000lasecuela.CosasDeLuchador;


import combate2000lasecuela.Saveable;

public abstract class Minion implements Saveable {
    private String name;
    private int health;
    private String type;
    private int id;
    private String specialSkill;

    public Minion(String linea){
        String [] values = linea.split(";");
        this.id=Integer.parseInt(values[0]);
        this.name = values[1];
        this.type = values[2];
        this.health = Integer.parseInt(values[4].trim());
        this.setSpecialSkill(values[3]);
    }

    @Override
    public String getId() {
        return Integer.toString(this.id);
    }

    //------------------------ GETTERS & SETTERS
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
    public String getType() { return this.type;}
    public void setSpecialSkill(String skill){
        this.specialSkill = skill;
    }
    public abstract String getSpecialSkillName();
}
