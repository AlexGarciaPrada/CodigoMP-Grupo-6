package combate2000lasecuela;

import combate2000lasecuela.screen.MessageManager;
import combate2000lasecuela.CosasDeLuchador.Hunter;
import combate2000lasecuela.CosasDeLuchador.Lycanthrope;
import combate2000lasecuela.CosasDeLuchador.TFighter;
import combate2000lasecuela.CosasDeLuchador.Vampire;
import combate2000lasecuela.managers.Database;
import combate2000lasecuela.screen.MessageManager;

import java.util.ArrayList;

import static combate2000lasecuela.Constants.*;

public class UserFlow extends Gameflow{



    private boolean eraseuser = false;
    private boolean challengemode = false;
    private boolean fighterstate = false;
    private boolean cfighter = false;
    private boolean challengep = false;
    private boolean efighter = false;
    private boolean eadmin = false;
    private boolean ranking = false;

    public UserFlow() {
        super();
    }

    private void playerMachine(Player player){
        if (challengemode) {
            challengeMode(player);
        } else if (fighterstate) {
            fighterState(player);
        } else if (cfighter) {
            createFighter(player);
        } else if (challengep) {
            challengePlayer(player);
        } else if (efighter) {
            eraseFighter(player);
        } else if (eadmin) {
            adminEquipment(player);
        } else if (ranking) {
            playersRanking();
        } else{
            playerLogin(player);
        }
    }

private void playerLogin(Player player){
        int option = 0;
        while (option != 9) {
            option = messageManager.showPlayerMenu(player.getNick());
            switch (option) {
                case 1:
                    challengemode = true;
                    break;
                case 2:
                    fighterstate = true;
                    break;
                case 3:
                    ranking = true;
                    break;
                case 4:
                    eraseuser = true;
                    break;
                case 5:
                    eadmin = true;
                    break;
                case 6:
                    player.setNick("");
                    break;
                case 7:
                    player.setNick("");
                    break;
                case 8:
                    player.setNick("");
                    break;
                case 9:
                    break;
            }
        }
    }

    private void challengeMode(Player player){
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
            challengemode = false;
        }
    }

    private void fighterState(Player player){
        int option = 0;
        while (option != 4) {
            option = messageManager.showReadableBox(Constants.fighterTypesText, 4);
            switch (option) {
                case 1:
                    cfighter = true;
                    break;
                case 2:
                    efighter = true;
                    break;
                case 3:
                    break;
                case 4:
                    fighterstate = false;
                    break;
            }
        }
    }



}
