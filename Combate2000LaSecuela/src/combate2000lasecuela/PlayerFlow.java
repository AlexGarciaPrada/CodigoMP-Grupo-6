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

    private static boolean playerLogin =true;
    private static boolean erasePlayer = false;
    private static boolean challengeMode = false;
    private static boolean fighterState = false;
    private static boolean createFighter = false;
    private static boolean challengePlayer = false;
    private static boolean editFighter = false;
    private static boolean equipmAdmin = false;
    private static boolean ranking = false;


    public static void playerMachine(Player player,Database database,MessageManager messageManager) {
    while (playerLogin) {
        if (player.hasPendingChallenges()){
            challengeMode =true;
        }
        if (challengeMode) {
            challengeMode(player, database,messageManager);
        } else if (fighterState) {
            fighterState(player,messageManager);
        } else if (createFighter) {
            createFighter(player, database,messageManager);
        } else if (challengePlayer) {
            challengePlayer(player, database,messageManager);
        } else if (editFighter) {
            eraseFighter(player, database,messageManager);
        } else if (equipmAdmin) {
            adminEquipment(player, messageManager);
        } else if (ranking) {
            playersRanking(database,messageManager);
        } else if (erasePlayer) {
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
                createFighter =true;
                break;
            case 2: //Borrar personaje
                editFighter =true;
                break;
            case 3: //Administrar equipo personaje
                equipmAdmin =true;
                break;
            case 4: //Desafiar a otro jugador
                challengePlayer =true;
                break;
            case 5: //Consultar registro de oro
                break;
            case 6: //Ver Ranking
                ranking=true;
                break;
            case 7: //Ver el estado del Fighter
                fighterState =true;
                break;
            case 8: //Cerrar Sesion
                playerLogin =false;
                break;
            case 9: //Borrar Usuario
                erasePlayer=true;
                break;
        }
    }

    private static void challengeMode(Player player, Database database, MessageManager messageManager){
        Challenge challenge = player.getFighter().getPendingChallenges().getFirstChallenge();
        int gold = challenge.getGold();
        String [] challengeData= challenge.getChallengeData();
        int option = messageManager.showReadableBox(challengeData,2);
        if (option ==1){ //Desafio aceptado
            player.Fight(challenge.getChallenger(),gold);
        }else{ //Desafio rechazado
            challenge.getChallenger().rejectingChallenge(-gold);
            player.rejectingChallenge(gold);
        }
        player.deletePendingChallenge();
        database.updateUsers();
        if (!(player.hasPendingChallenges())) {
            challengeMode = false;
        }
    }
    private static void adminEquipment(Player player, MessageManager messageManager){
        equipmAdmin =false;
        if (player.getFighter()==null){
            messageManager.showContent(notFighterText);
            return;
        }
        int option = messageManager.showReadableBox(player.getFighter().generateWeaponsText(),(player.getFighter().generateWeaponsText().length));
        ///Aquí habría que hacer cosas
    }
    private static void challengePlayer(Player player, Database database, MessageManager messageManager){
        challengePlayer =false;
        if (player.getFighter()==null){
            return;
        }
        messageManager.showContent(challengeInstructionText);
        String user = messageManager.showReadString(nickText);
        if ((database.isAPlayer(user))){
            Player challenged = (Player) database.getUser(user);
            if (challenged.getFighter()!=null){
                int gold = messageManager.showReadGold(player.getFighter().getGold());
                Challenge challenge = player.challengePlayer((Player) database.getUser(user),gold);
                database.addPendingChallenge(challenged,challenge);
            }
            else{
                messageManager.showContent(notFighterChallenged);
            }

        }else{
            messageManager.showContent(userNotFoundText);
        }
    }
    private static void fighterState(Player player, MessageManager messageManager){
        fighterState =false;
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
        createFighter =false;
        if (player.getFighter()!=null){
            messageManager.showContent(alreadyFighterText);
        }else{
            int option = messageManager.showReadableBox(fighterTypesText,3);
            String name =messageManager.showReadString(nameText);
            ArrayList<TFighter> TFighters = database.managerToListTFighter();
            int opttype =messageManager.showReadableBox(database.getTFighterText(TFighters),database.getTFighterText(TFighters).length+1);
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
        editFighter =false;
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
    private static void erasePlayer(User user,Database database,MessageManager messageManager){
        int option = messageManager.showEraseUser(user.getNick());
        erasePlayer=false;
        if (option == 1){
                messageManager.showContent(userCorrectlyErasedText);
                database.erasePlayer((Player) user);
                playerLogin =false;
            }

    }


}
