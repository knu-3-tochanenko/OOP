package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Greenhouse {
    private List<Plant> plants;

    public Greenhouse() {
        plants = new ArrayList<>();
    }

    public Greenhouse(List<Plant> plants) {
        this.plants = plants;
    }

    public void add(Plant plant) {
        plants.add(plant);
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void sort() {
        Collections.sort(plants);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("\u001b[31;1mGreenhouse:\u001b[0m\n");
        for (Plant plant : plants) res.append(plant.toString()).append("\n");
        return res.toString();
    }
}
