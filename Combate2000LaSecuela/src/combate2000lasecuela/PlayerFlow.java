package combate2000lasecuela;

import combate2000lasecuela.screen.MessageManager;
import combate2000lasecuela.CosasDeLuchador.Hunter;
import combate2000lasecuela.CosasDeLuchador.Lycanthrope;
import combate2000lasecuela.CosasDeLuchador.TFighter;
import combate2000lasecuela.CosasDeLuchador.Vampire;
import combate2000lasecuela.managers.Database;

import java.util.ArrayList;

import static combate2000lasecuela.Constants.*;

public class PlayerFlow extends Gameflow {

    private static boolean playerlogin =true;
    private static boolean eraseplayer = false;
    private static boolean challengemode = false;
    private static boolean fighterstate = false;
    private static boolean createfighter = false;
    private static boolean challengeplayer = false;
    private static boolean erasefighter = false;
    private static boolean equipadmin = false;
    private static boolean ranking = false;
    private static boolean goldregister=false;


    public static void playerMachine(Player player,Database database,MessageManager messageManager) {
        playerlogin = true;
    while (playerlogin) {
        if (player.hasPendingChallenges()){
            challengemode =true;
        }
        if (challengemode) {
            challengeMode(player, database,messageManager);
        } else if (fighterstate) {
            fighterState(player,messageManager);
        } else if (createfighter) {
            createFighter(player, database,messageManager);
        } else if (challengeplayer) {
            challengePlayer(player, database,messageManager);
        } else if (erasefighter) {
            eraseFighter(player, database,messageManager);
        } else if (equipadmin) {
            adminEquipment(player, messageManager);
        } else if (ranking) {
            playersRanking(database,messageManager);
        } else if (goldregister) {
            goldRegister(player,database,messageManager);
        } else if (eraseplayer) {
            erasePlayer(player,database,messageManager);

        } else {
            playerLogin(player, messageManager);
        }
    }
    }

    private static void playerLogin(Player player, MessageManager messageManager) {
        int option = messageManager.showPlayerMenu(player.getName());
        switch(option){
            case 1: //Crear personaje
                createfighter =true;
                break;
            case 2: //Borrar personaje
                erasefighter =true;
                break;
            case 3: //Administrar equipo personaje
                equipadmin =true;
                break;
            case 4: //Desafiar a otro jugador
                challengeplayer =true;
                break;
            case 5: //Consultar registro de oro
                goldregister=true;
                break;
            case 6: //Ver Ranking
                ranking=true;
                break;
            case 7: //Ver el estado del Fighter
                fighterstate =true;
                break;
            case 8: //Cerrar Sesion
                playerlogin =false;
                break;
            case 9: //Borrar Usuario
                eraseplayer =true;
                break;
        }
    }

    private static void challengeMode(Player player, Database database, MessageManager messageManager){
        Challenge challenge = player.getFighter().getPendingChallenges().getFirstChallenge();
        int gold = challenge.getGold();
        String [] challengeData= challenge.getChallengeData();
        int option = messageManager.showReadableBox(challengeData,2);
        if (option ==1){ //Desafio aceptado
            player.Fight(challenge.getChallenger(),gold);  //TODO
            database.updateCombats(); // TODO
            //messageManager.showContent(loser);
        }else{ //Desafio rechazado
            challenge.getChallenger().rejectingChallenge(-gold);
            player.rejectingChallenge(gold);
        }
        player.deletePendingChallenge();
        database.updateUsers();
        if (!(player.hasPendingChallenges())) {
            challengemode = false;
        }
    }
    private static void adminEquipment(Player player, MessageManager messageManager){
        equipadmin =false;
        if (player.getFighter()==null){
            messageManager.showContent(notFighterText);
            return;
        }
        int option = messageManager.showReadableBox(player.getFighter().generateEquipment(),(player.getFighter().generateEquipment().length-2));
        ///Aquí habría que hacer cosas
    }
    private static void challengePlayer(Player player, Database database, MessageManager messageManager){
        challengeplayer =false;
        if (player.getFighter()==null){
            messageManager.showContent(notFighterText);
            return;
        }
        messageManager.showContent(challengeInstructionText);
        String user = messageManager.showReadString(nickText);
        if ((database.isAPlayer(user))){
            Player challenged = (Player) database.getUser(user);
            if (challenged.getFighter()!=null){
                int gold = messageManager.showReadGold(player.getFighter().getGold());
                Challenge challenge = player.challengePlayer((Player) database.getUser(user),gold);
                database.addChallenge(challenge);
            }
            else{
                messageManager.showContent(notFighterChallenged);
            }

        }else{
            messageManager.showContent(userNotFoundText);
        }
    }
    private static void fighterState(Player player, MessageManager messageManager){
        fighterstate =false;
        if (player.getFighter()==null){
            messageManager.showContent(notFighterText);
            return;
        }
        String [] fighterState= player.getFighter().generateFighterState();
        messageManager.showContent(fighterState);
    }

    private static void playersRanking(Database database, MessageManager messageManager){
        messageManager.showRanking(database.getRanking());
        ranking=false;
    }
    private static void createFighter(Player player, Database database, MessageManager messageManager){
        createfighter =false;
        if (player.getFighter()!=null){
            messageManager.showContent(alreadyFighterText);
        }else{
            int option = messageManager.showReadableBox(fighterTypesText,3);
            String name =messageManager.showReadString(nameText);
            ArrayList<TFighter> TFighters = database.managerToListTFighter();
            int opttype =messageManager.showReadableBox(database.getTFighterText(TFighters),database.getTFighterText(TFighters).length-1);
            TFighter type = TFighters.get(opttype-1);
            switch(option){
                case 1:     //Vampiro
                    database.addFighter(player,new Vampire(name,type,database.randomMinions(type.getSuerteM(),false,0),database.randomArmor(type.getSuerteA()),database.randomWeapons(type.getSuerteW())));
                    break;
                case 2:     //Licántropo
                    database.addFighter(player,new Lycanthrope(name,type,database.randomMinions(type.getSuerteM(),false,0),database.randomArmor(type.getSuerteA()),database.randomWeapons(type.getSuerteW())));
                    break;
                case 3:     //Cazador
                    database.addFighter(player,new Hunter(name,type ,database.randomMinions(type.getSuerteM(),false,0),database.randomArmor(type.getSuerteA()),database.randomWeapons(type.getSuerteW())));
                    break;
            }
        }
    }
    private static void eraseFighter(Player player, Database database, MessageManager messageManager){
        erasefighter =false;
        if (player.getFighter() == null){
            messageManager.showContent(notFighterText);
        }else{
            int option = messageManager.showReadableBox(eraseConfirmationText,2);
            if (option == 1){
                return;
            }else{
                database.eraseFighter(player);
            }
        }
    }
    private static void erasePlayer(Player player,Database database,MessageManager messageManager){
        int option = messageManager.showEraseUser(player.getNick());
        eraseplayer =false;
        if (option == 1){
                messageManager.showContent(userCorrectlyErasedText);
                database.erasePlayer(player);
                playerlogin =false;
            }

    }
    private static void goldRegister(Player player, Database database,MessageManager messageManager){
        goldregister=false;
        if (database.isCombatRegisterEmpty()){
            messageManager.showContent(noCombatsText);
        }else{


        String [] content = database.getCombatHistory(player);
        if (content==null){
            messageManager.showContent(noCombatsText);
        } else{
            messageManager.showContent(database.getCombatHistory(player));
        }
        }
    }


}
