package Entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GreenhouseTest {
    private Plant plant = new Plant(
            1,
            "rose",
            Soil.PODZOLIC,
            "Germany",
            new VisualParameters(
                    "#00FF00",
                    "#112233",
                    5.5f
            ),
            new GrowingTips(
                    21,
                    false,
                    50
            ),
            Breeding.FLOWERS
    );
    private Plant plant2 = new Plant(
            2,
            "turtle",
            Soil.SOD_PODZOLIC,
            "France",
            new VisualParameters(
                    "#00FF00",
                    "#112233",
                    5.5f
            ),
            new GrowingTips(
                    21,
                    false,
                    50
            ),
            Breeding.FLOWERS
    );

    private List<Plant> plants;

    private Greenhouse greenhouse;
    private Greenhouse greenhouseEmpty = new Greenhouse();

    {
        plants = new ArrayList<>();
        plants.add(plant);
        greenhouse = new Greenhouse(plants);
    }

    @Test
    void testGet() {
        assertEquals(greenhouse.getPlants().get(0).toString(), plant.toString());
    }

    @Test
    void addTest() {
        greenhouse = new Greenhouse();
        greenhouse.add(plant);
        greenhouse.add(plant2);
        assertEquals(greenhouse.getPlants().size(), 2);
    }

    @Test
    void sortTest() {
        greenhouse = new Greenhouse();
        greenhouse.add(plant);
        greenhouse.add(plant2);
        greenhouse.sort();
        boolean res = greenhouse.getPlants().get(0).getId()
                < greenhouse.getPlants().get(1).getId();
        assertTrue(res);
    }

    @Test
    void toStringTest() {
        greenhouse = new Greenhouse();
        greenhouse.add(plant);
        greenhouse.add(plant2);

        StringBuilder res = new StringBuilder("\u001b[31;1mGreenhouse:\u001b[0m\n");
        res.append(plant.toString()).append("\n").append(plant2.toString()).append("\n");

        assertEquals(greenhouse.toString(),res.toString());
    }

}