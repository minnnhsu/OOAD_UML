package mode;

public enum ModeType {
    SELECTION("selection", SelectMode.class),
    ASSOCIATION("association", LineMode.class),
    GENERALIZATION("generalization", LineMode.class),
    COMPOSITION("composition", LineMode.class),
    CLASS("class", ObjMode.class),
    USECASE("usecase", ObjMode.class);

    private final String modeName;
    private final Class<? extends Mode> modeClass;

    ModeType(String modeName, Class<? extends Mode> modeClass) {
        this.modeName = modeName;
        this.modeClass = modeClass;
    }

    public String getModeName() {
        return modeName;
    }

    public Class<? extends Mode> getModeClass() {
        return modeClass;
    }
}
