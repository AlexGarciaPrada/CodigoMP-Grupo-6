package combate2000lasecuela.managers;

import combate2000lasecuela.*;
import combate2000lasecuela.CosasDeLuchador.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class Database {
    private UserManager usermanager;
    private Loader loader;
    private ChallengeManager challengeManager;
    private CombatRegister combatregister;

    private ModifierManager modifierManager;


    public Database() {
        this.usermanager = new UserManager();
        this.loader = new Loader();
        this.challengeManager = new ChallengeManager();
        this.combatregister = new CombatRegister();
    }

    public void addFighter(Player player, Fighter fighter) {
        player.createFighter(fighter);
        updateUsers();

    }

    public void eraseFighter(Player player) {
        player.deleteFighter();
        ;
        updateUsers();

    }

    public void updateUsers() {
        usermanager.saveCollection("User");
    }

    public void updateChallenges() {
        challengeManager.saveCollection("Challenge");
    }

    public void addPendingChallenge(Player challenged, Challenge challenge) {
        challenged.addPendingChallenge(challenge);
        updateUsers();
    }

    public void loadUsers() {
        usermanager.loadElement("User");
    }

    public void addPlayer(Player player) {
        usermanager.addElement("Player", player.getNick(), player);
        updateUsers();
    }

    public boolean isAPlayer(String nick) {
        return usermanager.inMap("Player", nick);
    }

    public void addOperator(Operator operator) {
        usermanager.addElement("Operator", operator.getNick(), operator);
        usermanager.saveCollection("User");
    }

    public void erasePlayer(Player player) {
        usermanager.getElements().get("Player").remove(player.getNick());
        usermanager.saveCollection("User");
    }

    public void eraseOperator(Operator operator) {
        usermanager.getElements().get("Operator").remove(operator.getNick());
        usermanager.saveCollection("User");
    }

    public boolean isNickUsed(String nick) {
        if ((usermanager.inMap("Player", nick)) || (usermanager.inMap("Operator", nick))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPasswordCorrect(String nick, String password) {
        if ((usermanager.inMap("Player", nick))) {
            return (usermanager.getElements().get("Player").get(nick).getPassword().equals(password));
        } else {
            return (usermanager.getElements().get("Operator").get(nick).getPassword().equals(password));
        }
    }

    public User getUser(String nick) {
        if ((usermanager.inMap("Player", nick))) {
            return usermanager.getElements().get("Player").get(nick);
        } else {
            return usermanager.getElements().get("Operator").get(nick);
        }
    }

    private ArrayList<Player> mapToNotOrderedRanking(Map<String, User> map) {
        ArrayList<Player> playerList = new ArrayList<>();
        for (User user : map.values()) {
            playerList.add((Player) user); //Tengo que añadirlos uno a uno para castearlos
        }
        return playerList;
    }

    public ArrayList<String> getRanking() {
        ArrayList<String> ranking = new ArrayList<>();
        ArrayList<Player> playerList = mapToNotOrderedRanking(usermanager.getElements().get("Player"));
        Collections.sort(playerList, Comparator.comparing(Player::getVictories));
        for (Player player : playerList) {
            ranking.add(String.valueOf(playerList.indexOf(player) + 1) + ". " + player.getNick() + " Victorias: " + player.getVictories());
        }
        return ranking;
    }

    public Stack<Minion> randomMinions(int suerte, boolean esVampiro, int tope) {
        Random random = new Random();
        Stack<Minion> myMinions = new Stack<>();
        Minion esclavo;
        int numero = random.nextInt(80) + 1 + suerte;
        for (Integer i = 0; i <= numero; i++) {
            esclavo = loader.getMm().getElements().get("MinionMap").get(i.toString());
            if (!(esVampiro) || !(esclavo instanceof Human)) {
                myMinions.push(esclavo);
                if ((esclavo instanceof Demon) && (tope <= 3)) { //que no se meta en bucle continuo, capo a los demonios
                    tope += 1;
                    ((Demon) esclavo).setPilaDemoniaca(randomMinionDemon(tope));
                }
            }
        }
        return myMinions;
    } //no estoy muy seguro de que esto este bien, pero no veo problemas de primeras

    public Stack<Minion> randomMinionDemon(int tope) {
        if (tope <= 3) {
            return randomMinions(0, false, tope + 1);
        }
        return null;
    }

    public TFighter getTFighter() {
        return null;
    }

    public LinkedList<Weapon> randomWeapons(int suerte) {
        Random random = new Random();
        LinkedList<Weapon> myWeapon = new LinkedList<>();
        Weapon weapon;
        int number = random.nextInt(28) + 1 + suerte;
        for (Integer i = 1; i <= number; i++) {
            weapon = (Weapon) loader.getIm().getElements().get("WeaponMap").get(i.toString());
            if (i == 1) {
                weapon.setEquipped(true);
            }
            myWeapon.add(weapon);
        }
        return myWeapon;
    }

    public LinkedList<Armor> randomArmor(int suerte) {
        Random random = new Random();
        Armor armor;
        LinkedList<Armor> myArmor = new LinkedList<>();
        int numero = random.nextInt(loader.getIm().getCollection("ArmorMap").size()) + 1 + suerte;
        for (int i = 1; i <= numero; i++) {
            armor = (Armor) loader.getIm().getElements().get("ArmorMap").get(Integer.toString(i));
            if (i == 1) {
                armor.setEquipped(true);
            }
            myArmor.add(armor);
        }
        return myArmor;
    }

    public String[] getTFighterText(ArrayList<TFighter> tFightersList) {
        ArrayList<String> text = new ArrayList<>();
        text.add("Elige el tipo de personaje que deseas crear: ");
        int i = 1;
        for (TFighter tfighter : tFightersList) {
            text.add(i + ". " + tfighter.getName() + " Esbirros: +" + tfighter.getSuerteM() + " Armaduras: +" + tfighter.getSuerteA() + " Armas: +" + tfighter.getSuerteW());
            i++;
        }
        // Convertir ArrayList a Array de Strings
        return text.toArray(new String[text.size()]);
    }

    public ArrayList<TFighter> managerToListTFighter() {
        ArrayList<TFighter> result = new ArrayList<>();
        Map<String, TFighter> tFighterManager = loader.getTfm().getCollection("TFighterMap");
        for (TFighter tFighter : tFighterManager.values()) {
            result.add(tFighter);
        }
        return result;
    }

    public LinkedList<Strength> getStrengths() {
        LinkedList<Strength> MyStrength = new LinkedList<>();
        Map<String, Modifier> modifierMap = loader.getMom().getElements().get("StrengthMap");

        for (String key : modifierMap.keySet()) {
            Modifier modifier = modifierMap.get(key);

            if (modifier instanceof Strength) {
                Strength strength = (Strength) modifier;
                MyStrength.add(strength);
            }
        }
        return MyStrength;
    }

    public LinkedList<Weakness> getWeaknesses() {
        LinkedList<Weakness> MyWeakness = new LinkedList<>();
        Map<String, Modifier> modifierMap = loader.getMom().getElements().get("WeaknessMap");

        for (String key : modifierMap.keySet()) {
            Modifier modifier = modifierMap.get(key);

            if (modifier instanceof Weakness) {
                Weakness weakness = (Weakness) modifier;
                MyWeakness.add(weakness);
            }
        }
        return MyWeakness;
    }

    //He tenido que hacer una pseudocola, es decir, una pepsi.
    public Challenge getChallenge() {
        for (Map.Entry<String, Challenge> entry : this.challengeManager.getCollection("ChallengeMap").entrySet()) {
            return entry.getValue(); //Esto debería devolver el primer desafio insertado
        }
        return null; //Esto en principio no se usa nunca
    }

    public void eraseChallenge() {
        for (Map.Entry<String, Challenge> entry : this.challengeManager.getCollection("ChallengeMap").entrySet()) {
            this.challengeManager.getCollection("ChallengeMap").remove(entry.getKey()); //Esto debería devolver el primer desafio insertado
            updateChallenges();
            return; //Para salir en la primera iteracion
        }

    }

    public void addChallenge(Challenge challenge) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(date);
        challengeManager.getCollection("ChallengeMap").put(formattedDate, challenge);
        updateChallenges();
    }

    public boolean isEmptyChallengeManager() {
        return (challengeManager.getCollection("ChallengeMap").isEmpty());
    }

    public String[] getCombatHistory(Player player) {
        ArrayList<String> combattext = new ArrayList<>();
        for (Map.Entry <String,Combat> entry: combatregister.getCollection("CombatMap").entrySet()) {
            Combat combat =entry.getValue();
            if (combat.getChallenger().equals(player.getFighter()) || combat.getChallenged().equals(player.getFighter())) {
                combattext.add("Fecha de combate: " + combat.getDate()+ " Resultado de combate: " + combat.Result()+ " Oro ganado/perdido: " + player.whogetsGold(combat));

            }
        }
        if (combattext.isEmpty()){
            return null;
        }
       String [] data =  combattext.toArray(new String[combattext.size()]);
        return data;
    }
    public boolean isCombatRegisterEmpty(){
        return combatregister.getCollection("CombatMap").isEmpty();
    }
}