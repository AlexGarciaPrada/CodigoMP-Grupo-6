package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.Hunter;
import combate2000lasecuela.CosasDeLuchador.Lycanthrope;
import combate2000lasecuela.CosasDeLuchador.TFighter;
import combate2000lasecuela.CosasDeLuchador.Vampire;
import combate2000lasecuela.managers.Database;
import combate2000lasecuela.screen.MessageManager;

import java.util.ArrayList;

import static combate2000lasecuela.Constants.*;

public class Gameflow {

    private MessageManager messageManager;

    private Database database;
    private User user;
    

    //Señales para la maquina de estados
    private boolean register;
    private boolean login;
    private boolean playerlogin;
    private boolean operatorlogin;
    private boolean eraseuser;
    private boolean ranking;
    private boolean block;
    private boolean unblock;
    private boolean cfighter;
    private boolean efighter;
    private boolean eadmin;
    private boolean challengep;
    private boolean challengemode;
    private boolean fighterstate;

    
    public Gameflow() {
        messageManager = new MessageManager();
        database = new Database();
        register=false;
        login=false;
        playerlogin=false;
        operatorlogin=false;
        eraseuser=false;
        ranking=false;
        block=false;
        unblock=false;
        cfighter=false;
        efighter=false;
        eadmin= false;
        challengep=false;
        challengemode=false;
        fighterstate=false;
    }

    public void startMenu() {
        int option = messageManager.showReadableBox(initMenuText,3);
        switch (option) {
            case 1:
                login = true;
                break;
            case 2:
                register = true;
                break;
            case 3:
                System.exit(-1);
        }
    }
    public void finiteStateMachine() {
        while (true){
            if (login) {
                login();
            } else if (register) {
                register();
            } else if (playerlogin) {
                playerMachine((Player) user);
            } else if (operatorlogin) {
                operatorMachine((Operator) user);
            } else {
                startMenu();
            }
        }
    }

    // ------------------------ MACHINES
    private void playerMachine(Player player){
        UserFlow.playerMachine(player,database,messageManager);
        playerlogin=false;
    }
    private void operatorMachine(Operator operator){
        if (eraseuser){
            eraseUser(operator);
        } else if (block) {
            blockUser(operator);
        } else if (unblock) {
            unblockUser(operator);
        } else{
            operatorLogin(operator);
        }
    }
    // ------------------------ LOGIN
    private void login(){
        login = false;
        messageManager.showLogInMenu();
        String nick = messageManager.showReadString(nickText);
            if (nick.equals("SALIR")){
                return;
            }else{
                String password = messageManager.showReadString(passwordText);
                    if (password.equals("SALIR")){
                        return;
                    }else{
                       if (database.isNickUsed(nick)){
                           if (database.isPasswordCorrect(nick,password)){
                               user = database.getUser(nick);
                               if (user instanceof Player){
                                   if (!((Player) user).isBlocked()){
                                       playerlogin = true;
                                       if (((Player) user).hasPendingChallenges()){
                                            challengemode=true;
                                       }
                                   }else{
                                       messageManager.showContent(playerBlockedText);
                                   }
                               }else{
                                   operatorlogin=true;
                               }
                           }else{
                               messageManager.showContent(wrongPasswordText);
                           }

                       }else{
                           messageManager.showContent(userNotFoundText);
                       }
                    }
            }
    }
    // ------------------------ REGISTER
    private void register(){
        register =false;
        int type = messageManager.showReadableBox(userTypeMenuText,3);
            if (type == 3){
                return;
            }
        messageManager.showRegisterMenu();
        String name =messageManager.showReadString(nameText);
            if (name.equals("SALIR")){
                return;
            }
        String nick = messageManager.showReadString(nickText);
            if (nick.equals("SALIR")){
                return;
            }
            else if (database.isNickUsed(nick)){
                messageManager.showContent(nickUsedText);
                return;
            }
            else{
                String password = messageManager.showReadString(passwordText);
                if (password.equals("SALIR")){
                    return;
                }else{
                    String confirmpassword =messageManager.showReadString(confirmPasswordText);
                        if (confirmpassword.equals("SALIR")){
                            return;
                        }else{
                            if (confirmpassword.equals(password)){
                                if (type == 1){
                                    database.addPlayer(new Player(name,password,nick));
                                }else{
                                    database.addOperator(new Operator(name,password,nick));
                                }
                            messageManager.showUserRegistered(nick);
                            }else{
                            messageManager.showContent(notCoincidencePasswordText);
                            }
                        }
                }
            }
    }
    private void operatorLogin(Operator operator){
        int option = messageManager.showOperatorMenu(operator.getName());
        switch(option){
            case 1: //Editar personaje
                break;
            case 2: //Editar equipo,esbirros,modificadores
                break;
            case 3: //Validar desafios
                break;
            case 4: //Bloquear Usuario
                block=true;
                break;
            case 5: //Desbloquear Usuario
                unblock=true;
                break;
            case 6: //Cerrar Sesion
                operatorlogin=false;
                break;
            case 7: //Borrar Usuario
                eraseuser=true;
                break;
        }
    }


    private void eraseUser(User user){
        int option = messageManager.showEraseUser(user.getNick());
        eraseuser=false;
        if (option == 1){
            if (playerlogin){
                messageManager.showContent(userCorrectlyErasedText);
                database.erasePlayer((Player) user);
                playerlogin=false;
            }else{
                database.eraseOperator((Operator) user);

                operatorlogin=false;
            }
        }
    }

    // ------------------------ BLOCK AND UNBLOCK USER
    private void blockUser(Operator operator) {
        block = false;
        String nick = messageManager.showNickToBlock();
        if (database.isNickUsed(nick)) {
            User auxuser = database.getUser(nick);
            if (auxuser instanceof Player) {
                if (!((Player) auxuser).isBlocked()) {
                    operator.blockPlayer((Player) auxuser);
                    messageManager.showUserBlocked(auxuser.getNick());
                }else{
                    messageManager.showContent(alreadyBlockText);
                }
            } else {
                messageManager.showContent(userNotFoundText);
            }
        } else {
            messageManager.showContent(userNotFoundText);
        }
    }
    private void unblockUser(Operator operator) {
        unblock = false;
        String nick = messageManager.showNickToUnblock();
        if (database.isNickUsed(nick)) {
            User auxuser = database.getUser(nick);
            if (auxuser instanceof Player) {
                if (((Player) auxuser).isBlocked()) {
                    operator.unblockPlayer((Player) auxuser);
                    messageManager.showUserUnblocked(auxuser.getNick());
                }else{
                    messageManager.showContent(alreadyUnblockText);
                }
            } else {
                messageManager.showContent(userNotFoundText);
            }
        } else {
            messageManager.showContent(userNotFoundText);
        }
    }


}
