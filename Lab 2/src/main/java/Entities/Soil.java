package Entities;

public enum Soil {
    PODZOLIC {
        @Override
        public String toString() {
            return "podzolic";
        }
    },
    UNPAVED {
        @Override
        public String toString() {
            return "unpaved";
        }
    },
    SOD_PODZOLIC {
        @Override
        public String toString() {
            return "sod podzolic";
        }
    }
}
