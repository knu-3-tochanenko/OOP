package Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlantTest {

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

    private Plant plantEmpty = new Plant();

    @Test
    void setGetFunctions() {
        plantEmpty.setGrowingTips(
                new GrowingTips(20, true, 100)
        );
        plantEmpty.setVisualParameters(
                new VisualParameters(
                        "#556677",
                        "#000000",
                        12.5f
                )
        );
        plantEmpty.setOrigin("Germany");
        plantEmpty.setSoil(Soil.SOD_PODZOLIC);
        plantEmpty.setName("tulip");
        plantEmpty.setBreeding(Breeding.STALK);
        plantEmpty.setId(2);

        assertEquals(plantEmpty.getGrowingTips(), new GrowingTips(20, true, 100));
        assertEquals(plantEmpty.getVisualParameters(), new VisualParameters(
                "#556677",
                "#000000",
                12.5f
        ));
        assertEquals(plantEmpty.getOrigin(), "Germany");
        assertEquals(plantEmpty.getSoil(), Soil.SOD_PODZOLIC);
        assertEquals(plantEmpty.getName(), "tulip");
        assertEquals(plantEmpty.getBreeding(), Breeding.STALK);
        assertEquals(plantEmpty.getId(), 2);
    }

    @Test
    void toStringTest() {
        assertEquals(plant.toString(),
                "\u001b[35;1m\tPlant #1:\u001b[0m\n"
                        + "\u001b[32;1m\t\tname:\u001b[0m\t\trose"
                        + "\u001b[32;1m\n\t\tsoil:\u001b[0m\t\tpodzolic"
                        + "\u001b[32;1m\n\t\torigin:\u001b[0m\t\tGermany"
                        + "\u001b[32;1m\n\t\tvisual parameters:\u001b[0m\n" + new VisualParameters(
                        "#00FF00",
                        "#112233",
                        5.5f
                ).toString()
                        + "\u001b[32;1m\n\t\tgrowing tips:\u001b[0m\n" + new GrowingTips(
                        21,
                        false,
                        50
                ).toString()
                        + "\u001b[32;1m\t\tbreeding:\u001b[0m\tflowers"
                        + '\n');
    }

    @Test
    void compareTest() {
        assertEquals(plantEmpty.compareTo(plant), -1);
        assertEquals(plant.compareTo(plantEmpty), 1);
        assertEquals(plant.compareTo(plant), 0);
    }

}