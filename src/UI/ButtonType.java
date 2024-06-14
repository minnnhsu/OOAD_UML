package UI;

public enum ButtonType {
    SELECTION("select"),
    ASSOCIATION("association"),
    GENERALIZATION("generalization"),
    COMPOSITION("composition"),
    CLASS("class"),
    USECASE("usecase");

    private String iconPath;

    private ButtonType(String fileName) {
        iconPath = "/image/" + fileName + ".png";
    }

    public String getIconPath() {
        return iconPath;
    }
}