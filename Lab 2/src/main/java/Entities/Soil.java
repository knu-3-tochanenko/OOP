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
    },
    NONE;

    public static Soil value(String string) {
        switch (string) {
            case "podzolic":
                return PODZOLIC;
            case "unpaved":
                return UNPAVED;
            case "sod podzolic":
                return SOD_PODZOLIC;
            default:
                return NONE;
        }
    }
}
