package Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisualParametersTest {
    private VisualParameters visualParameters = new VisualParameters(
            "#112233",
            "#00FF00",
            12.3f
    );
    private VisualParameters visualParametersEmpty = new VisualParameters();

    @Test
    void setFunctions() {
        visualParametersEmpty.setStemColor("#009988");
        visualParametersEmpty.setLeafColor("#776655");
        visualParametersEmpty.setAverageSize(8.0f);
        assertEquals(visualParametersEmpty.toString(),
                "\u001b[36;1m\t\t\tstem color:\u001b[0m\t\t#009988"
                        + "\u001b[36;1m\n\t\t\tleaf color:\u001b[0m\t\t#776655"
                        + "\u001b[36;1m\n\t\t\taverage size:\u001b[0m\t8.00");
    }

    @Test
    void toStringTest() {
        assertEquals(visualParameters.toString(),
                "\u001b[36;1m\t\t\tstem color:\u001b[0m\t\t#112233"
                        + "\u001b[36;1m\n\t\t\tleaf color:\u001b[0m\t\t#00FF00"
                        + "\u001b[36;1m\n\t\t\taverage size:\u001b[0m\t12.30");
    }

    @Test
    void getFunctions() {
        assertEquals(visualParameters.getAverageSize(), 12.3f);
        assertEquals(visualParameters.getStemColor(), "#112233");
        assertEquals(visualParameters.getLeafColor(), "#00FF00");
    }

}