package combate2000lasecuela;

import combate2000lasecuela.managers.Database;
import combate2000lasecuela.screen.MessageManager;

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
        } else if (playerlogin) {
            playerMachine((Player) user);
        } else if (operatorlogin) {
            operatorMachine((Operator) user);
        } else {
            startMenu();
        }

    }}
    private void playerMachine(Player player){
        if (eraseuser){
            eraseUser(user);
        } else if (ranking) {
            playersRanking();
        } else{
            playerLogin(player);
        }
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
                   if (database.isPasswordCorrect(nick,password)){
                       user = database.getUser(nick);
                       if (user instanceof Player){
                           if (!((Player) user).isBlocked()){
                               playerlogin = true;
                           }else{
                               messageManager.showPlayerBlocked();
                               }

                       }else{
                           operatorlogin=true;
                       }
                   }else{
                       messageManager.showWrongPassword();
                   }
                   
               }else{
                   messageManager.showUserNotFound();
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

        if (name.equals("SALIR")){
            return;
        }
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
    private void playerLogin(Player player){
        int option = messageManager.showPlayerMenu(player.getName());
        switch(option){
            case 1: //Crear personaje
                break;
            case 2: //Borrar personaje
                break;
            case 3: //Administrar equipo personaje
                break;
            case 4: //Desafiar a otro jugador
                break;
            case 5: //Consultar registro de oro
                break;
            case 6: //Ver Ranking
                ranking=true;
                break;
            case 7: //Cerrar Sesion
                playerlogin=false;
                break;
            case 8: //Borrar Usuario
                eraseuser=true;
                break;
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
                database.erasePlayer((Player) user);
                playerlogin=false;
            }else{
                database.eraseOperator((Operator) user);
                operatorlogin=false;
            }
    }}
    private void playersRanking(){
        messageManager.showRanking(database.getRanking());
        ranking=false;
    }
    private void blockUser(Operator operator) {
        block = false;
        String nick = messageManager.showNickToBlock();
        if (database.isNickUsed(nick)) {
            User auxuser = database.getUser(nick);
            if (auxuser instanceof Player) {
                if (!((Player) auxuser).isBlocked()) {
                    operator.blockPlayer((Player) auxuser);
                }else{
                    messageManager.showAlreadyBlock();
                }
            } else {
                messageManager.showUserNotFound();
            }
        } else {
            messageManager.showUserNotFound();
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
                }else{
                    messageManager.showAlreadyUnblock();
                }
            } else {
                messageManager.showUserNotFound();
            }
        } else {
            messageManager.showUserNotFound();
        }
    }

}
