package Entities;

public class Plant {
    private String name;
    private Soil soil;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private Breeding breeding;

    Plant(
            String name,
            Soil soil,
            String origin,
            VisualParameters visualParameters,
            GrowingTips growingTips,
            Breeding breeding
    ) {
        this.name = name;
        this.soil = soil;
        this.origin = origin;
        this.visualParameters = visualParameters;
        this.growingTips = growingTips;
        this.breeding = breeding;
    }
}
