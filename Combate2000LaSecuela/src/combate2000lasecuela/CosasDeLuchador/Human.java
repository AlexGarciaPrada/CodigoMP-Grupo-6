package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;

import static java.lang.Integer.valueOf;

import static java.lang.Integer.valueOf;
public class Human extends Minion implements Serializable {
    int health;
    String lealtad;
    private MinionAttributes addedAttribute = MinionAttributes.Lealtad;

    public Human(String linea) {
        super(linea,MinionAttributes.Lealtad);
        String [] valores = linea.split(";");
        this.addedAttribute.setValue(valores[3]);
        this.health=Integer.parseInt(valores[4].trim());
        setHealth(health);
    }

    @Override
    public int getHealth() {
        return health;
    }

    public String getLealtad() {return this.addedAttribute.getValue();}


}
