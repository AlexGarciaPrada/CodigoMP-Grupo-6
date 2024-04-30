package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class PendingChallengesTest {
    private static PendingChallenges pc;
    private static Challenge challenge;

    @BeforeEach
    public void setUp() {
        //creamos la cola
        pc = new PendingChallenges();
        //creamos un desafio
        Player challenger = new Player("pepa", "password123", "pepa");
        Player challenged = new Player("chall", "password123", "chall");
        challenge = new Challenge(challenger, challenged, 40);
    }

    @Test
    public void addChallengeTest() {
        pc.addChallenge(challenge);
        assertNotNull(pc);
    }

    @Test
    public void getFirstChallengeTest() {
        pc.addChallenge(challenge);
        Player challenger = new Player("1", "password123", "1");
        Player challenged = new Player("2", "password123", "2");
        Challenge challenge2 = new Challenge(challenger, challenged, 40);
        pc.addChallenge(challenge2);
        pc.getFirstChallenge();
        assertEquals(challenge2, pc.getFirstChallenge());
    }

    @Test
    public void deleteChallengeTest() {
        pc.addChallenge(challenge);
        pc.deleteChallenge();
        assertTrue(pc.isEmpty());
    }

}