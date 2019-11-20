package Entities;

public enum Breeding {
    FLOWERS {
        @Override
        public String toString() {
            return "flowers";
        }
    },
    STALK {
        @Override
        public String toString() {
            return "stalk";
        }
    },
    SEEDS {
        @Override
        public String toString() {
            return "seeds";
        }
    };

    public static Breeding value(String string) throws Exception {
        switch (string) {
            case "flowers":
                return FLOWERS;
            case "stalk":
                return STALK;
            case "seeds":
                return SEEDS;
            default:
                throw new Exception("Wrong argument");
        }
    }
}
