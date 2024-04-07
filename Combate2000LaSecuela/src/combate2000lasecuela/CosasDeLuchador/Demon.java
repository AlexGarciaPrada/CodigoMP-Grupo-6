package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.LinkedList;

public class Demon extends Minion implements Serializable {
    private LinkedList<Minion> listaDemoniaca;
//    private MinionAttributes addedAttribute = MinionAttributes.Pacto;

    public Demon(String linea) {
        super(linea);
        String [] valores = linea.split(";");
        this.listaDemoniaca=null;
    }

    @Override
    public String getSpecialSkillName() {
        return "Pacto";
    }

    public void setDemonList(LinkedList<Minion> listaDemoniaca) {
        this.listaDemoniaca = listaDemoniaca;
    }
    public LinkedList<Minion> getDemonList() {return this.listaDemoniaca;}



}
