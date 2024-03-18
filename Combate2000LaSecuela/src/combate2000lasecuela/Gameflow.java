package combate2000lasecuela;

import combate2000lasecuela.screen.MessageManager;

public class Gameflow {

    private MessageManager messageManager;

    //Señales para la maquina de estados
    private boolean register;
    private boolean login;



    public Gameflow() {
        messageManager = new MessageManager();
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
        messageManager.showLogInMenu();
        String nick = messageManager.showReadNick();
        if (nick.equals("SALIR")){
            login = false;
        }else{
            String password = messageManager.showReadPassword();
            if (password.equals("SALIR")){
                login = false;
            }else{
                //Aquí manejando la DataBase habría que hacer cosicas
                login = false;
            }
        }

    }
    private void register(){
        register =false;
        messageManager.showRegisterMenu();
        String nick = messageManager.showReadNick();
        if (nick.equals("SALIR")){
            return;
        }else{
            String password = messageManager.showReadPassword();
            if (password.equals("SALIR")){
                return;
            }else{
                String confirmpassword = messageManager.showReadConfirmPassword();
                if (confirmpassword.equals("SALIR")){
                    return;
                }else{
                    if (confirmpassword.equals(password)){
                        //Aquí haríammos cosicas con la Database
                        messageManager.showUserRegistered(nick);
                    }else{
                        messageManager.showNotCoincidencePassword();
                    }

                }
            }
        }
    }

}
