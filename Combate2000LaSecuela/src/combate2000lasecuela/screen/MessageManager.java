package combate2000lasecuela.screen;
import combate2000lasecuela.Player;
import combate2000lasecuela.screen.MessageManager;
import combate2000lasecuela.screen.Textterminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static combate2000lasecuela.Constants.*;

public class MessageManager {
    private Terminal t;

    public MessageManager() {
        t = new Textterminal();
    }

    //Pantallas concretas con caja
    public int showReadableBox(String [] content,int max){
        showContent(content);
        return t.read(max);
    }
    public void showUserRegistered(String user) {
        String[] content = {theUserText+user+userRegistered2};
        showContent(content);
    }
    public int showPlayerMenu(String name){
        t.showln("");
        t.showln(showWelcome+name);
        showContent(playerMenuText);
        return t.read(9);
    }
    public int showEraseUser(String nick){
        t.showln("");
        t.showln(adviceErasetext+nick);
        showContent(questionErasetext);
        return t.read(2);
    }
    public int showOperatorMenu(String name){
        t.showln("");
        t.showln(showWelcome+name);
        showContent(operatorMenuText);
        return t.read(7);
    }
    public void showRanking (ArrayList<String> ranking){
        t.showln(rankingText);
        showContent(ranking.toArray(new String[ranking.size()]));
    }
    public void showUserBlocked(String nick){
        String [] content={theUserText+nick,userBlock};
        showContent(content);
    }
    public void showUserUnblocked(String nick){
        String [] content={theUserText+nick,userUnblock};
        showContent(content);
    }
    //Pantallas sin caja
    public String showReadString (String info){
        t.show(info);
        return t.readString();
    }
    public void showLogInMenu(){
    t.showln(logInText);
    t.showln(exitAdvice);
    }
    public int showReadGold(int max){
        t.showln(gold);
        return t.read(max);
    }
    public String showNickToBlock(){
        t.showln(blockUserText);
        t.show(selectUserToBlockText);
        return t.readString();
    }
    public String showNickToUnblock(){
        t.showln(unblockUserText);
        t.show(selectUserToUnblockText);
        return t.readString();
    }

    public void showRegisterMenu(){
        t.showln(registerText);
        t.showln(exitAdvice);
    }
    //Funcionamiento interno de las pantallas
    private int getMaxLine(String[] content) {
        int max = 0;
        for (String line : content) {
            if (line.length() > max) {
                max = line.length();
            }
        }
        return max + 12; // Añade 12 para tener en cuenta los espacios a cada lado
    }

    public void showContent(String[] content) {
        int wide = getMaxLine(content);
        //Tapa de la caja
        t.showln("|" + "-".repeat(wide) + "|");
        //Contenido de la caja
        for (String line : content) {
            t.showf("|%-" + (wide - 1) + "s|\n", " ".repeat(6) + line + " ".repeat(wide - line.length() - 6));
            t.showln("|" + " ".repeat(wide) + "|");
        }
        // Tapa inferior
        t.showln("|" + "-".repeat(wide) + "|");
    }


   

}
