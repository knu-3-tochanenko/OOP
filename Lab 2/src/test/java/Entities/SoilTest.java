package Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoilTest {

    @Test
    void value() throws Exception {
        assertEquals(Soil.value("podzolic"), Soil.PODZOLIC);
        assertEquals(Soil.value("unpaved"), Soil.UNPAVED);
        assertEquals(Soil.value("sod podzolic"), Soil.SOD_PODZOLIC);
        assertThrows(Exception.class, () -> Soil.value("different"));
    }

    @Test
    void testToString() {
        assertEquals(Soil.PODZOLIC.toString(), "podzolic");
        assertEquals(Soil.UNPAVED.toString(), "unpaved");
        assertEquals(Soil.SOD_PODZOLIC.toString(), "sod podzolic");
    }
}