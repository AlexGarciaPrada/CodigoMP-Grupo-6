package combate2000lasecuela.CosasDeLuchador;

import java.io.Serializable;
import java.util.ArrayList;
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

    public String [] getDemonListText(){
        ArrayList<String> demonText = new ArrayList<>();
        demonText.add("Esbirros de "+ this.getName()+" :");
        if ((listaDemoniaca==null) || (listaDemoniaca.isEmpty())  ){
            demonText.add("No tiene");
            return demonText.toArray(new String [demonText.size()]);
        }
        for (Minion minion: listaDemoniaca){
            if (minion != null) {
                demonText.add(minion.getName() + " Tipo: " + minion.getType() + " "  + minion.getSpecialSkillName() + ":" + minion.getSpecialSkill()+ " Salud: " + minion.getHealth());
                if (minion instanceof Demon){
                    String [] littleDemonText = ((Demon) minion).getDemonListText();
                    for (String text: littleDemonText){
                        demonText.add(text);
                    }
                }
            }
        }
        return demonText.toArray(new String [demonText.size()]);
    }



}
