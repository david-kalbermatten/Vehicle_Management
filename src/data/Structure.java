package data;

public enum Structure {
    SMALL("Kleinwagen"),
    LIMO("Limosine"),
    KOMBI("Kombi"),
    SUV("SUV"),
    CABRIO("Cabriolet");

    private final String structure;

    Structure(String structure) {
        this.structure = structure;
    }

    public String getStructure() {
        return structure;
    }
}
