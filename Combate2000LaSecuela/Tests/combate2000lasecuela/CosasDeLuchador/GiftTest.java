package combate2000lasecuela.CosasDeLuchador;

import static org.junit.jupiter.api.Assertions.*;

import combate2000lasecuela.managers.Enviroment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GiftTest {

    @BeforeAll
    static void setUp() {
        Enviroment enviroment = new Enviroment();
        enviroment.setTesting(true);
    } @Test
    public void testGetDamage_DefaultValue() {
        Gift gift = new Gift();
        assertEquals(2, gift.getDamage());
    }

    @Test
    public void testGetRageCost_RandomizedValue() {
        Gift gift = new Gift();
        int rageCost = gift.getRageCost();
        assertTrue(rageCost >= 1 && rageCost <= 3);
    }

    @Test
    public void testSetGiftDamage() {
        Gift gift = new Gift();
        gift.setGiftDamage(5);
        assertEquals(5, gift.getDamage());
    }
}
