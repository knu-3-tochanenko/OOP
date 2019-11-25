package Helpers;

import Entities.*;

public class GreenhouseXMLBuilder implements XMLBuilder<Greenhouse> {
    public Tags currentTag = Tags.NONE;

    private Greenhouse greenhouse = new Greenhouse();
    private Plant plant = null;
    private VisualParameters visualParameters = new VisualParameters();
    private GrowingTips growingTips = new GrowingTips();

    @Override
    public XMLBuilder<Greenhouse> addOpenTag(String tag) {
        currentTag = Tags.value(tag);
        switch (currentTag) {
            case PLANT:
                plant = new Plant();
                break;
            case VISUAL_PARAMETERS:
                visualParameters = new VisualParameters();
                break;
            case GROWING_TIPS:
                growingTips = new GrowingTips();
                break;
            default:
                break;
        }
        return this;
    }

    @Override
    public XMLBuilder<Greenhouse> addAttribute(String name, String value) {
        if (name == null)
            return this;
        if (value == null)
            value = "";
        if (name.equalsIgnoreCase("id"))
            plant.setId(Integer.parseInt(value));
        return this;
    }

    @Override
    public XMLBuilder<Greenhouse> addData(String data) {
        switch (currentTag) {
            case NAME:
                plant.setName(data);
                break;
            case SOIL:
                try {
                    plant.setSoil(Soil.value(data));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case ORIGIN:
                plant.setOrigin(data);
                break;
            case STEM_COLOR:
                visualParameters.setStemColor(data);
                break;
            case LEAF_COLOR:
                visualParameters.setLeafColor(data);
                break;
            case AVERAGE_SIZE:
                visualParameters.setAverageSize(Float.parseFloat(data));
                break;
            case TEMPERATURE:
                growingTips.setTemperature(Integer.parseInt(data));
                break;
            case NEEDS_LIGHT:
                growingTips.setNeedsLight(Boolean.parseBoolean(data));
                break;
            case WATERING:
                growingTips.setWatering(Integer.parseInt(data));
                break;
            case BREEDING:
                try {
                    plant.setBreeding(Breeding.value(data));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            default:
                break;
        }
        return this;
    }

    @Override
    public XMLBuilder<Greenhouse> addCloseTag(String tag) {
        Tags currentTag = Tags.value(tag);
        switch (currentTag) {
            case PLANT:
                greenhouse.add(plant);
                break;
            case VISUAL_PARAMETERS:
                plant.setVisualParameters(visualParameters);
                break;
            case GROWING_TIPS:
                plant.setGrowingTips(growingTips);
                break;
            default:
                break;
        }
        return this;
    }

    @Override
    public Greenhouse getRoot() {
        return greenhouse;
    }
}
