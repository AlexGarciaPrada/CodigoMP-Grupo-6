package combate2000lasecuela;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Queue;
import java.util.LinkedList;

public class Challengemanager {
    private Queue <Challenge> challenges;

    public Challengemanager(){ challenges = new LinkedList<Challenge>();}

    public void addChallenge(Challenge challenge){ challenges.add(challenge);}

    public Challenge deleteChallenge(){ return challenges.remove();}


}
