package Entities;

public class Plant implements Comparable<Plant> {
    private int id;
    private String name;
    private Soil soil;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private Breeding breeding;

    public Plant(
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

    public Plant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public Breeding getBreeding() {
        return breeding;
    }

    public void setBreeding(Breeding breeding) {
        this.breeding = breeding;
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
