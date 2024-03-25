package combate2000lasecuela;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;


public class PendingChallenges implements Serializable {
    private Queue<Challenge> pendingChallenges;

    public PendingChallenges() {
        this.pendingChallenges = new LinkedList<>();
    }

    public void addChallenge(Challenge c) {
        pendingChallenges.offer(c);
    }

    public Challenge getFirstChallenge() {
        return pendingChallenges.poll();
    }

    public boolean isEmpty() {return pendingChallenges.isEmpty();}

    
}
