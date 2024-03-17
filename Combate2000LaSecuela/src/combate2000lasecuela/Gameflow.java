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
                System.exit(0);
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
        login = false;
    }
    private void register(){
        messageManager.showRegisterMenu();
        register=false;
    }

}
