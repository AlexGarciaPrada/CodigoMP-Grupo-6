package combate2000lasecuela;

import combate2000lasecuela.managers.Database;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeTest {
    @Test
    void testGetChallengeData(){
        Challenge challenge = new Challenge(new Player("challengertest","00","challengertest"), new Player("challengedtest","00","challengedtest"), 100);
        String [] data = challenge.getChallengeData();
        assertEquals("Has sido desafiado por el usuario challengertest", data[0]);
    }


}