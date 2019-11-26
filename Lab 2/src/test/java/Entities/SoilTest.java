package Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoilTest {

    @Test
    void value() throws Exception {
        assertEquals(Soil.value("podzolic"), Soil.PODZOLIC);
        assertEquals(Soil.value("unpaved"), Soil.UNPAVED);
        assertEquals(Soil.value("sod podzolic"), Soil.SOD_PODZOLIC);
        assertEquals(Soil.value("something strange"), Soil.NONE);
    }

    @Test
    void testToString() {
        assertEquals(Soil.PODZOLIC.toString(), "podzolic");
        assertEquals(Soil.UNPAVED.toString(), "unpaved");
        assertEquals(Soil.SOD_PODZOLIC.toString(), "sod podzolic");
    }
}