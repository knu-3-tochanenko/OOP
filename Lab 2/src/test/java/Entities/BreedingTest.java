package Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BreedingTest {

    @Test
    void value() throws Exception {
        assertEquals(Breeding.value("flowers"), Breeding.FLOWERS);
        assertEquals(Breeding.value("stalk"), Breeding.STALK);
        assertEquals(Breeding.value("seeds"), Breeding.SEEDS);
        assertEquals(Breeding.value("another"), Breeding.NONE);
    }

    @Test
    void testToString() {
        assertEquals(Breeding.FLOWERS.toString(), "flowers");
        assertEquals(Breeding.STALK.toString(), "stalk");
        assertEquals(Breeding.SEEDS.toString(), "seeds");
    }
}