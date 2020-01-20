package Helpers;

import Entities.Breeding;
import Entities.Plant;
import Entities.Soil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreenhouseXMLBuilderTest {

    private GreenhouseXMLBuilder builder = new GreenhouseXMLBuilder();

    @Test
    void addPlant() {
        builder.addOpenTag("plant").addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        assertEquals(builder.getRoot().getPlants().get(0), new Plant());
    }

    @Test
    void addName() {
        String name = "rose";
        builder.addOpenTag("plant")
                .addOpenTag("name")
                .addData(name)
                .addCloseTag("name")
                .addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        assertEquals(builder.getRoot().getPlants().get(0).getName(), name);
    }

    @Test
    void addSoil() {
        Soil soil = Soil.UNPAVED;
        builder.addOpenTag("plant")
                .addOpenTag("soil")
                .addData(soil.toString())
                .addCloseTag("soil")
                .addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        assertEquals(builder.getRoot().getPlants().get(0).getSoil(), soil);
    }

    @Test
    void addOrigin() {
        String origin = "Ukraine";
        builder.addOpenTag("plant")
                .addOpenTag("origin")
                .addData(origin)
                .addCloseTag("origin")
                .addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        assertEquals(builder.getRoot().getPlants().get(0).getOrigin(), origin);
    }

    @Test
    void addVPStemColor() {
        String color = "#889988";
        builder.addOpenTag("plant")
                .addOpenTag("visual-parameters").addOpenTag("stem-color")
                .addData(color)
                .addCloseTag("stem-color").addCloseTag("visual-parameters")
                .addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        assertEquals(builder.getRoot().getPlants().get(0).getVisualParameters().getStemColor(), color);
    }

    @Test
    void addVPLeafColor() {
        String color = "#889988";
        builder.addOpenTag("plant")
                .addOpenTag("visual-parameters").addOpenTag("leaf-color")
                .addData(color)
                .addCloseTag("leaf-color").addCloseTag("visual-parameters")
                .addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        assertEquals(builder.getRoot().getPlants().get(0).getVisualParameters().getLeafColor(), color);
    }

    @Test
    void addVPAverageSize() {
        float averageSize = 5.0f;
        builder.addOpenTag("plant")
                .addOpenTag("visual-parameters").addOpenTag("average-size")
                .addData(Float.toString(averageSize))
                .addCloseTag("average-size").addCloseTag("visual-parameters")
                .addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        assertEquals(builder.getRoot().getPlants().get(0).getVisualParameters().getAverageSize(), averageSize);
    }

    @Test
    void addGTTemperature() {
        int temperature = 30;
        builder.addOpenTag("plant")
                .addOpenTag("growing-tips").addOpenTag("temperature")
                .addData(Integer.toString(temperature))
                .addCloseTag("temperature").addCloseTag("growing-tips")
                .addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        assertEquals(builder.getRoot().getPlants().get(0).getGrowingTips().getTemperature(), temperature);
    }

    @Test
    void addGTWatering() {
        int watering = 30;
        builder.addOpenTag("plant")
                .addOpenTag("growing-tips").addOpenTag("watering")
                .addData(Integer.toString(watering))
                .addCloseTag("watering").addCloseTag("growing-tips")
                .addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        assertEquals(builder.getRoot().getPlants().get(0).getGrowingTips().getWatering(), watering);
    }

    @Test
    void addGTIsLightNeeded() {
        boolean isLightNeeded = false;
        builder.addOpenTag("plant")
                .addOpenTag("growing-tips").addOpenTag("needs-light")
                .addData(Boolean.toString(isLightNeeded))
                .addCloseTag("needs-light").addCloseTag("growing-tips")
                .addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        assertEquals(builder.getRoot().getPlants().get(0).getGrowingTips().isNeedsLight(), isLightNeeded);
    }

    @Test
    void addBreeding() {
        Breeding breeding = Breeding.FLOWERS;
        builder.addOpenTag("plant")
                .addOpenTag("breeding")
                .addData(breeding.toString())
                .addCloseTag("breeding")
                .addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        assertEquals(builder.getRoot().getPlants().get(0).getBreeding(), breeding);
    }

    @Test
    void addAttribute() {
        builder.addOpenTag("plant").addAttribute("id", "123").addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        Plant gem = builder.getRoot().getPlants().get(0);
        assertEquals(gem.getId(), 123);
    }

    @Test
    void addEmptyAttribute() {
        builder.addOpenTag("plant").addAttribute("id", null).addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        Plant gem = builder.getRoot().getPlants().get(0);
        assertEquals(gem.getId(), 0);
    }

    @Test
    void addNoAttribute() {
        builder.addOpenTag("plant").addAttribute(null, "12").addCloseTag("plant");
        assertNotNull(builder.getRoot());
        assertEquals(builder.getRoot().getPlants().size(), 1);
        Plant gem = builder.getRoot().getPlants().get(0);
        assertEquals(gem.getId(), 0);
    }

    @Test
    void addWrongAttribute() {
        assertThrows(Exception.class,
                () -> builder.addOpenTag("plant")
                        .addAttribute("id", "abc")
                        .addCloseTag("plant"));
    }
}