package combate2000lasecuela.CosasDeLuchador;

public enum MinionAttributes {
    Dependencia(), Lealtad(), Pacto();

    private String value;

    MinionAttributes() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getName(MinionAttributes attribute) {
        switch (attribute) {
            case Dependencia:
                return "Dependencia";
            case Lealtad:
                return "Lealtad";
            case Pacto:
                return "Pacto";
        }
        return null;
    }
}



