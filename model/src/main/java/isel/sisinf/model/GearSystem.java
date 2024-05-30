package isel.sisinf.model;

public enum GearSystem {
    UMA(1),
    SEIS(6),
    DEZOITO(18),
    VINTE_QUATRO(24);

    private final int gearSystem;

    GearSystem(int value) {
        this.gearSystem = value;
    }
}
