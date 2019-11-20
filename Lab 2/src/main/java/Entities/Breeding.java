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
    }
}
