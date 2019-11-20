package Entities;

public class Plant implements Comparable<Plant> {
    private int id;
    private String name;
    private Soil soil;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private Breeding breeding;

    Plant(
            int id,
            String name,
            Soil soil,
            String origin,
            VisualParameters visualParameters,
            GrowingTips growingTips,
            Breeding breeding
    ) {
        this.id = id;
        this.name = name;
        this.soil = soil;
        this.origin = origin;
        this.visualParameters = visualParameters;
        this.growingTips = growingTips;
        this.breeding = breeding;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Soil getSoil() {
        return soil;
    }

    public String getOrigin() {
        return origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public Breeding getBreeding() {
        return breeding;
    }

    @Override
    public int compareTo(Plant plant) {
        if (this.id == plant.id)
            return 0;
        return this.id > plant.id ? 1 : -1;
    }

    public String toString() {
        return "\tPlant #" + id + ":\n"
                + "\t\tname:\t" + name
                + "\t\tsoil:\t" + soil.toString()
                + "\t\torigin:\t" + origin
                + "\t\tvisual parameters:\n" + visualParameters.toString()
                + "\t\tgrowing tips:\n" + growingTips.toString()
                + "\t\tbreeding:\t" + breeding.toString()
                + '\n';
    }
}
