package combate2000lasecuela;

import combate2000lasecuela.managers.Database;
import combate2000lasecuela.screen.MessageManager;

import static combate2000lasecuela.Constants.*;

public class Gameflow  {

    private MessageManager messageManager;

    private Database database;
    private User user;

    //Señales
    private boolean register;
    private boolean login;
    private boolean playerlogin;
    private boolean operatorlogin;



    
    public Gameflow() {
        messageManager = new MessageManager();
        database = new Database();
        register=false;
        login=false;
        playerlogin=false;
        operatorlogin=false;
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
        PlayerFlow.playerMachine(player,database,messageManager);
        playerlogin=false;
    }
    private void operatorMachine(Operator operator){
        OperatorFlow.operatorMachine(operator,database,messageManager);
        operatorlogin=false;
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


}
