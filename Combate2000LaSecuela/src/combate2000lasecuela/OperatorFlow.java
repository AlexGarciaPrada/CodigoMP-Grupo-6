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
        setOperatorlogin(true);
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
                setEditfighter(true);
                break;
            case 2: //Editar equipo,esbirros,modificadores
                setEditEquipment(true);
                break;
            case 3: //Validar desafios
                setVchallenge(true);
                break;
            case 4: //Bloquear Usuario
                setBlock(true);
                break;
            case 5: //Desbloquear Usuario
                setUnblock(true);
                break;
            case 6: //Cerrar Sesion
                setOperatorlogin(false);
                break;
            case 7: //Borrar Usuario
                setEraseoperator(true);
                break;
        }
    }

    // ------------------------ BLOCK AND UNBLOCK USER
    public static void blockUser(Operator operator,Database database,MessageManager messageManager) {
        setBlock(false);
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
        setUnblock(false);
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
        setEraseoperator(false);
        if (option == 1){
            messageManager.showContent(userCorrectlyErasedText);
            database.eraseOperator(operator);
            operatorlogin =false;
        }
    }
    private static void validateChallenge (Operator operator,Database database, MessageManager messageManager){
        if (database.isEmptyChallengeManager()){
            setVchallenge(false);
            messageManager.showContent(notChallengeToValidate);
            return;
        }
        Challenge challenge = database.getChallenge();
        messageManager.showContent(challenge.getChallenger().getFighter().generateFighterForOperatorState());
        messageManager.showContent(challenge.getChallenged().getFighter().generateFighterForOperatorState());
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
        setVchallenge(false);
    }

    // ------------------------ EDIT FIGHTER CHARACTERISTICS
    private static void editFighter(Database database, MessageManager messageManager){
        setEditfighter(false);
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
                        int opttype =messageManager.showReadableBox(database.getTFighterText(),database.getTFighterText().length-1);
                        TFighter type = database.getLoader().gettFighterManager().getCollection("TFighterMap").get(opttype-1);
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
        setEditEquipment(false);
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
                        editWeapon(operator, player, database, messageManager);
                        break;
                    case 2:
                        editArmor(operator, player, database, messageManager);
                        break;
                    case 3:
                        editMinions(operator, player, database, messageManager);
                        break;
                }
            }
        }else{
            messageManager.showContent(userNotFoundText);
        }
    }

    private static void editWeapon(Operator operator, Player player, Database database, MessageManager messageManager) {
        int option = messageManager.showReadableBox(editMenu,2);
        switch (option) {
            case 1:
                messageManager.showContent(player.getFighter().generateWeaponsText());
                if (player.getFighter().getMyWeapon().size() > 1) {
                    int elementId = messageManager.showReadableBox(elementText, 28);
                    boolean done = database.deleteWeapon(operator, player, elementId);
                    if (!done) {
                        messageManager.showContent(elementNotEquipped);
                    } else {
                        messageManager.showContent(elementDeleted);
                    }
                    break;
                } else {
                    messageManager.showContent(OneElementOnly);
                    break;
                }
            case 2:
                messageManager.showContent(database.generateWeaponText());
                int elemId = messageManager.showReadableBox(newElementText, 28);
                boolean check = database.addWeapon(operator, player, elemId);
                if (!check) {
                    messageManager.showContent(elementAlreadyEquipped);
                } else {
                    messageManager.showContent(elementAdded);
                }
        }
    }

    private static void editArmor(Operator operator, Player player, Database database, MessageManager messageManager) {
        int option = messageManager.showReadableBox(editMenu,2);
        switch (option) {
            case 1:
                messageManager.showContent(player.getFighter().generateArmorText());
                if (player.getFighter().getMyArmor().size() > 1) {
                    int elementId = messageManager.showReadableBox(elementText, 28);
                    boolean done = database.deleteArmor(operator, player, elementId);
                    if (!done) {
                        messageManager.showContent(elementNotEquipped);
                    } else {
                        messageManager.showContent(elementDeleted);
                    }
                    break;
                } else {
                    messageManager.showContent(OneElementOnly);
                    break;
                }
            case 2:
                messageManager.showContent(database.generateArmorText());
                int elemId = messageManager.showReadableBox(newElementText, 28);
                boolean check = database.addArmor(operator, player, elemId);
                if (!check) {
                    messageManager.showContent(elementAlreadyEquipped);
                } else {
                    messageManager.showContent(elementAdded);
                }
        }
    }

    private static void editMinions(Operator operator, Player player, Database database, MessageManager messageManager) {
        int option = messageManager.showReadableBox(editMenu,2);
        switch (option) {
            case 1:
                if (!player.getFighter().getMyMinions().isEmpty()) {
                    messageManager.showContent(player.getFighter().getAllMinionText());
                    int elementId = messageManager.showReadableBox(elementText, 100);
                    boolean done2 = database.deleteMinion(operator, player, elementId);
                    if (!done2) {
                        messageManager.showContent(elementNotEquipped);
                    } else {
                        messageManager.showContent(elementDeleted);
                    }
                    break;
                } else {
                    messageManager.showContent(NoMinions);
                    break;
                }
            case 2:
                messageManager.showContent(database.generateMinionText());
                int elemId = messageManager.showReadableBox(newElementText, 100);
                boolean check = database.addMinion(operator, player, elemId);
                if (!check) {
                    messageManager.showContent(elementAlreadyEquipped);
                } else {
                    messageManager.showContent(elementAdded);
                }
        }
    }

    // ------------------------ GETTERS & SETTERS

    public static boolean isOperatorlogin() {
        return operatorlogin;
    }

    public static void setOperatorlogin(boolean operatorlogin) {
        OperatorFlow.operatorlogin = operatorlogin;
    }

    public static boolean isEditfighter() {
        return editfighter;
    }

    public static void setEditfighter(boolean editfighter) {
        OperatorFlow.editfighter = editfighter;
    }

    public static boolean isEraseoperator() {
        return eraseoperator;
    }

    public static void setEraseoperator(boolean eraseoperator) {
        OperatorFlow.eraseoperator = eraseoperator;
    }

    public static boolean isBlock() {
        return block;
    }

    public static void setBlock(boolean block) {
        OperatorFlow.block = block;
    }

    public static boolean isUnblock() {
        return unblock;
    }

    public static void setUnblock(boolean unblock) {
        OperatorFlow.unblock = unblock;
    }

    public static boolean isVchallenge() {
        return vchallenge;
    }

    public static void setVchallenge(boolean vchallenge) {
        OperatorFlow.vchallenge = vchallenge;
    }

    public static boolean isEditEquipment() {
        return editEquipment;
    }

    public static void setEditEquipment(boolean editEquipment) {
        OperatorFlow.editEquipment = editEquipment;
    }
}
