package combate2000lasecuela;

import combate2000lasecuela.managers.Database;
import combate2000lasecuela.screen.MessageManager;

public class Gameflow {

    private MessageManager messageManager;

    private Database database;

    //Señales para la maquina de estados
    private boolean register;
    private boolean login;



    public Gameflow() {
        messageManager = new MessageManager();
        database = new Database();
        register=false;
        login=false;

    }

    public void startMenu() {
        int option = messageManager.showInitMenu();
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
        } else {
            startMenu();
        }

    }}
    private void login(){
        login = false;
        messageManager.showLogInMenu();
        String nick = messageManager.showReadNick();
        if (nick.equals("SALIR")){
            return;
        }else{
            String password = messageManager.showReadPassword();
            if (password.equals("SALIR")){
                return;
            }else{
               if (database.isNickUsed(nick)){
                   
               }
            }
        }

    }
    private void register(){
        register =false;
        int type = messageManager.showUserType();
        if (type == 3){
            return;
        }

        messageManager.showRegisterMenu();
        String name = messageManager.showReadName();

        String nick = messageManager.showReadNick();
        if (nick.equals("SALIR")){
            return;
        }
        else if (database.isNickUsed(nick)){
            messageManager.showNickUsed();
            return;
        }
        else{
            String password = messageManager.showReadPassword();
            if (password.equals("SALIR")){
                return;
            }else{
                String confirmpassword = messageManager.showReadConfirmPassword();
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
                        messageManager.showNotCoincidencePassword();
                    }

                }
            }
        }
    }

}
