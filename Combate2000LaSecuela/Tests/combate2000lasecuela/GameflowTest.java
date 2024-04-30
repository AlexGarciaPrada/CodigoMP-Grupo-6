package combate2000lasecuela;

import combate2000lasecuela.CosasDeLuchador.*;
import combate2000lasecuela.managers.*;
import org.junit.jupiter.api.*;
import java.util.*;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class GameflowTest {
    @BeforeAll
    static void testSetUpSerialized() {
        Enviroment enviroment = new Enviroment();
        enviroment.setTesting(true);
        Database database = new Database();
        Player playerTester = new Player("pepe", "password123", "pepe");
        database.addPlayer(playerTester);
        Operator operatorTester = new Operator("op", "password123", "op");
        database.addOperator(operatorTester);
    }

    @Test
    void testRegister(){
        Database database = new Database();
        UserManager userManager = new UserManager();
        database.addPlayer(new Player("test","password123","test"));
        assertTrue(userManager.inMap("Player","test"));
    }

}