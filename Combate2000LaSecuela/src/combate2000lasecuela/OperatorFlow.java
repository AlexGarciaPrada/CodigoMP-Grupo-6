package combate2000lasecuela;

import static combate2000lasecuela.Constants.*;
import static combate2000lasecuela.Constants.userNotFoundText;
import combate2000lasecuela.screen.MessageManager;
import combate2000lasecuela.managers.Database;

public class OperatorFlow {
    private static boolean operatorLogin =true;
    private static boolean eraseUser = false;
    private static boolean block = false;
    private static boolean unblock = false;

    public static void operatorMachine(Operator operator,Database database,MessageManager messageManager){
        if (block) {
            blockUser(operator,database,messageManager);
        } else if (unblock) {
            unblockUser(operator,database,messageManager);
        } else if (operatorLogin)
            operatorLogin(operator,messageManager);
        else if (eraseUser) {
            eraseOperator(operator,database,messageManager);
        }

    }

    public static void operatorLogin(Operator operator,MessageManager messageManager){
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
                operatorLogin =false;
                break;
            case 7: //Borrar Usuario
                eraseUser =true;
                break;
        }
    }

    // ------------------------ BLOCK AND UNBLOCK USER
    public static void blockUser(Operator operator,Database database,MessageManager messageManager) {
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
    public static void unblockUser(Operator operator,Database database,MessageManager messageManager) {
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

    private static void eraseOperator(User user,Database database,MessageManager messageManager){
        int option = messageManager.showEraseUser(user.getNick());
        eraseUser =false;
        if (option == 1){
            messageManager.showContent(userCorrectlyErasedText);
            database.erasePlayer((Player) user);
            operatorLogin =false;
        }
    }

}
