public class Dummy {
    private String name;
    private MODE mode;

    public Dummy(String name, String mode) {
        this.name = name;
        this.mode = MODE.fromString(mode);
    }

    public void setMode(MODE mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public String whatsUp() {
        return mode.toString();
    }

    private enum MODE {
        MANUAL("manual"), AI("ai"), IDLE("idle");

        String name;

        MODE(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }

        public static MODE fromString(String string) {
            switch (string) {
                case "manual":
                    return MANUAL;
                case "ai":
                    return AI;
                default:
                    return IDLE;
            }
        }
    }
}
