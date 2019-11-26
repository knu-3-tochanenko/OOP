package Helpers;

public enum Tags {
    NONE {
        @Override
        public String toString() {
            return "none";
        }
    },
    PLANT {
        @Override
        public String toString() {
            return "plant";
        }
    },
    NAME {
        @Override
        public String toString() {
            return "name";
        }
    },
    SOIL {
        @Override
        public String toString() {
            return "soil";
        }
    },
    ORIGIN {
        @Override
        public String toString() {
            return "origin";
        }
    },
    VISUAL_PARAMETERS {
        @Override
        public String toString() {
            return "visual-parameters";
        }
    },
    STEM_COLOR {
        @Override
        public String toString() {
            return "stem-color";
        }
    },
    LEAF_COLOR {
        @Override
        public String toString() {
            return "leaf-color";
        }
    },
    AVERAGE_SIZE {
        @Override
        public String toString() {
            return "average-size";
        }
    },
    GROWING_TIPS {
        @Override
        public String toString() {
            return "growing-tips";
        }
    },
    TEMPERATURE {
        @Override
        public String toString() {
            return "temperature";
        }
    },
    NEEDS_LIGHT {
        @Override
        public String toString() {
            return "needs-light";
        }
    },
    WATERING {
        @Override
        public String toString() {
            return "watering";
        }
    },
    BREEDING {
        @Override
        public String toString() {
            return "breeding";
        }
    };


    public static Tags value(String string) {
        switch (string) {
            case "plant":
                return PLANT;
            case "name":
                return NAME;
            case "soil":
                return SOIL;
            case "origin":
                return ORIGIN;
            case "visual-parameters":
                return VISUAL_PARAMETERS;
            case "stem-color":
                return STEM_COLOR;
            case "leaf-color":
                return LEAF_COLOR;
            case "average-size":
                return AVERAGE_SIZE;
            case "growing-tips":
                return GROWING_TIPS;
            case "temperature":
                return TEMPERATURE;
            case "needs-light":
                return NEEDS_LIGHT;
            case "watering":
                return WATERING;
            case "breeding":
                return BREEDING;
            default:
                return NONE;
        }
    }
}
