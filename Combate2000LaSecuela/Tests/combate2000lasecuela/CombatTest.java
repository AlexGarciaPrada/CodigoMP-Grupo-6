package combate2000lasecuela;

import combate2000lasecuela.*;
import combate2000lasecuela.managers.Enviroment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombatTest {
    @BeforeAll
    static void setUp() {
    Enviroment enviroment = new Enviroment();
    enviroment.setTesting(true);
    }

    @Test
    void result() {

    }
}