package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;

import static java.lang.Integer.valueOf;

import static java.lang.Integer.valueOf;
public class Human extends Minion implements Serializable {
<<<<<<< Updated upstream
int health;
String lealtad;
=======

    int health;
    private MinionAttributes addedAttribute = MinionAttributes.Lealtad;
    String lealtad;
>>>>>>> Stashed changes
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
