package combate2000lasecuela;

import static combate2000lasecuela.Constants.*;
import static combate2000lasecuela.Constants.userNotFoundText;

import combate2000lasecuela.CosasDeLuchador.Item;
import combate2000lasecuela.CosasDeLuchador.TFighter;
import combate2000lasecuela.screen.MessageManager;
import combate2000lasecuela.managers.Database;

import java.util.ArrayList;

public class  OperatorFlow {
    private static boolean operatorlogin =true;
    private static boolean editfighter = false;
    private static boolean eraseoperator = false;
    private static boolean block = false;
    private static boolean unblock = false;
    private static boolean vchallenge=false;
    private static boolean editEquipment = false;

    public static void operatorMachine(Operator operator,Database database,MessageManager messageManager) {
        operatorlogin =true;
        while (operatorlogin) {
            if (block) {
                blockUser(operator, database, messageManager);
            } else if (unblock) {
                unblockUser(operator, database, messageManager);
            } else if (vchallenge) {
                validateChallenge(operator, database, messageManager);
            } else if (editfighter) {
                editFighter(database, messageManager);
            } else if (editEquipment) {
                editEquipment(operator, database, messageManager);
            } else if (eraseoperator) {
                eraseOperator(operator, database, messageManager);
            }else{
                operatorLogin(operator,messageManager);
            }

        }
    }

    public static void operatorLogin(Operator operator,MessageManager messageManager){
        int option = messageManager.showOperatorMenu(operator.getName());
        switch(option){
            case 1: //Editar personaje
                editfighter=true;
                break;
            case 2: //Editar equipo,esbirros,modificadores
                editEquipment = true;
                break;
            case 3: //Validar desafios
                vchallenge=true;
                break;
            case 4: //Bloquear Usuario
                block=true;
                break;
            case 5: //Desbloquear Usuario
                unblock=true;
                break;
            case 6: //Cerrar Sesion
                operatorlogin =false;
                break;
            case 7: //Borrar Usuario
                eraseoperator =true;
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

    private static void eraseOperator(Operator operator,Database database,MessageManager messageManager){
        int option = messageManager.showEraseUser(operator.getNick());
        eraseoperator =false;
        if (option == 1){
            messageManager.showContent(userCorrectlyErasedText);
            database.eraseOperator(operator);
            operatorlogin =false;
        }
    }
    private static void validateChallenge (Operator operator,Database database, MessageManager messageManager){
        if (database.isEmptyChallengeManager()){
            vchallenge=false;
            messageManager.showContent(notChallengeToValidate);
            return;
        }
        Challenge challenge = database.getChallenge();
        messageManager.showContent(challenge.getChallengeData());
        int option = messageManager.showReadableBox(validateChallengeText,3);
        switch(option){
            case 1:
                database.getChallenge().getChallenged().addPendingChallenge(challenge);
                database.eraseChallenge();
                break;
            case 2:
                database.eraseChallenge();
                break;
            case 3:
                break;
        }
        vchallenge=false;
    }
    private static void editFighter(Database database, MessageManager messageManager){
        editfighter=false;
        String nick = messageManager.showEditFighterMenu();
        if (nick.equals("SALIR")){
            return;
        }
        if (database.isAPlayer(nick)){
            Player player = (Player) database.getUser(nick);
            if (player.getFighter()==null){
                messageManager.showContent(thisPlayerNotFighter);
            }else{
                messageManager.showContent(player.getFighter().generateFighterState());
                int option = messageManager.showReadableBox(editFighterMenu,4);
                switch (option){
                    case 1: //Nombre
                        String name = messageManager.showReadString(nameText);
                        database.changeFighterName(player,name);
                        break;
                    case 2: //Raza
                        int race = messageManager.showReadableBox(fighterTypesText,3);
                         database.changeFighterRace(player,race);
                        break;
                    case 3: //Tipo
                        ArrayList<TFighter> TFighters = database.managerToListTFighter();
                        int opttype =messageManager.showReadableBox(database.getTFighterText(TFighters),database.getTFighterText(TFighters).length-1);
                        TFighter type = TFighters.get(opttype-1);
                        database.changeFighterType(player,type);
                        break;
                    case 4: //Salir
                        break;
                }
            }
        }else{
            messageManager.showContent(userNotFoundText);
        }
    }

    private static void editEquipment(Operator operator, Database database, MessageManager messageManager) {
        editEquipment=false;
        String nick = messageManager.showEditFighterMenu();
        if (nick.equals("SALIR")){
            return;
        }
        if (database.isAPlayer(nick)){
            Player player = (Player) database.getUser(nick);
            if (player.getFighter()==null){
                messageManager.showContent(thisPlayerNotFighter);
            }else{
                    int option = messageManager.showReadableBox(editEquipmentMenu,3);
                switch (option){
                    case 1:
                        editArmorWeapon(operator, player, database, messageManager);
                        break;
                    case 2:
                        editMinions(operator, player, database, messageManager);
                        break;
                    case 3: //definir fortalezas y debilidades
                        break;
                }
            }
        }else{
            messageManager.showContent(userNotFoundText);
        }
    }

    private static void editArmorWeapon(Operator operator, Player player, Database database, MessageManager messageManager) {
        messageManager.showContent(database.generateEquipmentText(player));
        int option = messageManager.showReadableBox(editArmorWeaponMenu,2);
        switch (option) {
            case 1:
                String elementName = messageManager.showReadString(elementText);
                String newElemName = messageManager.showReadString(newElementText);
                boolean done = database.deleteElement(operator, player, elementName);
                if (!done) {
                    messageManager.showContent(elementNotEquipped);
                } else {
                    boolean check = database.addElement(operator, player, newElemName);
                    if (!check) {
                        messageManager.showContent(elementAlreadyEquipped);
                    } else {
                        messageManager.showContent(elementEquipped);
                    }
                }
                break;
            case 2:
                String elem = messageManager.showReadString(newElementText);
                boolean check = database.addElement(operator, player, elem);
                if (!check) {
                    messageManager.showContent(elementAlreadyEquipped);
                } else {
                    messageManager.showContent(elementEquipped);
                }
        }
    }

    private static void editMinions(Operator operator, Player player, Database database, MessageManager messageManager) {
        messageManager.showContent(database.generateMinionText(player));
        int option = messageManager.showReadableBox(editMinionsMenu,2);
        switch (option) {
            case 1:
                String minionName = messageManager.showReadString(elementText);
                String newMinionName = messageManager.showReadString(newElementText);
                boolean done2 = database.deleteMinion(operator, player, minionName);
                if (!done2) {
                    messageManager.showContent(elementNotEquipped);
                } else {
                    boolean check2 = database.addMinion(operator, player, newMinionName);
                    if (!check2) {
                        messageManager.showContent(elementAlreadyEquipped);
                    } else {
                        messageManager.showContent(elementEquipped);
                    }
                }
                break;
            case 2:
                String minion = messageManager.showReadString(newElementText);
                boolean check = database.addMinion(operator, player, minion);
                if (!check) {
                    messageManager.showContent(elementAlreadyEquipped);
                } else {
                    messageManager.showContent(elementEquipped);
                }
        }
    }



}
