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
        return "\u001b[35;1m\tPlant #" + id + ":\u001b[0m\n"
                + "\u001b[32;1m\t\tname:\u001b[0m\t\t" + name
                + "\u001b[32;1m\n\t\tsoil:\u001b[0m\t\t" + soil.toString()
                + "\u001b[32;1m\n\t\torigin:\u001b[0m\t\t" + origin
                + "\u001b[32;1m\n\t\tvisual parameters:\u001b[0m\n" + visualParameters.toString()
                + "\u001b[32;1m\n\t\tgrowing tips:\u001b[0m\n" + growingTips.toString()
                + "\u001b[32;1m\t\tbreeding:\u001b[0m\t" + breeding.toString()
                + '\n';
    }
}
