package Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowingTipsTest {

    private GrowingTips growingTips = new GrowingTips(25, true, 100);
    private GrowingTips growingTipsEmpty = new GrowingTips();

    @Test
    void getTemperature() {
        assertEquals(growingTips.getTemperature(), 25);
    }

    @Test
    void getWatering() {
        assertEquals(growingTips.getWatering(), 100);
    }

    @Test
    void isNeedsLight() {
        assertTrue(growingTips.isNeedsLight());
    }

    @Test
    void testToString() {
        assertEquals(growingTips.toString(), "\u001b[36;1m\t\t\ttemperature:\u001b[0m\t25" +
                "\u001b[36;1m\n\t\t\tneeds light:\u001b[0m\ttrue" +
                "\u001b[36;1m\n\t\t\twatering:\u001b[0m\t\t100 (ml)\n");
    }

    @Test
    void setFunctions() {
        growingTipsEmpty.setWatering(50);
        growingTipsEmpty.setNeedsLight(false);
        growingTipsEmpty.setTemperature(45);

        assertEquals(growingTipsEmpty.toString(), "\u001b[36;1m\t\t\ttemperature:\u001b[0m\t45" +
                "\u001b[36;1m\n\t\t\tneeds light:\u001b[0m\tfalse" +
                "\u001b[36;1m\n\t\t\twatering:\u001b[0m\t\t50 (ml)\n");
    }

    @Test
    void equalsTest() {
        assertNotEquals(growingTips, 2019);
        assertEquals(growingTips, growingTips);
        assertNotEquals(growingTips, new GrowingTips(30, true, 100));
        assertNotEquals(growingTips, new GrowingTips(25, false, 100));
        assertNotEquals(growingTips, new GrowingTips(25, true, 200));
    }
}