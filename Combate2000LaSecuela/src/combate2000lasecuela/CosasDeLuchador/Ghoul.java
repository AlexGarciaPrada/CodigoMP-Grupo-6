package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;

import static java.lang.Integer.valueOf;

public class Ghoul extends Minion implements Serializable {
    int health;
    int lealtad;

    private MinionAttributes addedAttribute = MinionAttributes.Dependencia;

    public Ghoul(String linea) {
        super(linea, MinionAttributes.Dependencia);
            String [] valores = linea.split(";");
            this.addedAttribute.setValue(valores[3].trim());
            this.health=Integer.parseInt(valores[4].trim());
            setHealth(health);
        }

        @Override
        public int getHealth() {
            return health;
        }

        public MinionAttributes getAddedAttribute() {
            return addedAttribute;
        }

        public void setAddedAttribute(MinionAttributes addedAttribute) {
            this.addedAttribute = addedAttribute;
        }
    }
