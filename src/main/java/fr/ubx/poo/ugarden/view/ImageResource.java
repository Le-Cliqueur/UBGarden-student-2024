package fr.ubx.poo.ugarden.view;

public enum ImageResource {
    // Decor elements
    GRASS("grass.jpg"),
    LAND("land.jpg"),
    CARROTS("carrots.jpg"),
    FLOWERS("flowers.jpg"),

    ENERGY("energy.png"),
    TREE("tree.jpg"),


    // Bonus
    POISONED_APPLE("poisonedApple.png"),
    APPLE("apple.jpg"),
    KEY("key.png"),


    GROUND("ground.png"),

    DOOR_OPENED("door_opened.png"),
    DOOR_CLOSED("door_closed.png"),


    // Gardener and hornets
    GARDENER_UP("gardener_up.png"),
    GARDENER_RIGHT("gardener_right.png"),
    GARDENER_DOWN("gardener_down.png"),
    GARDENER_LEFT("gardener_left.png"),

    HORNET_UP("hornet_up.png"),
    HORNET_RIGHT("hornet_right.png"),
    HORNET_DOWN("hornet_down.png"),
    HORNET_LEFT("hornet_left.png"),

    HEDGEHOG("hedgehog.png"),

    INSECTICIDE("insecticide.png"),
    NEST("nest.png"),

    // Status bar

    DIGIT_0("banner_0.jpg"),
    DIGIT_1("banner_1.jpg"),
    DIGIT_2("banner_2.jpg"),
    DIGIT_3("banner_3.jpg"),
    DIGIT_4("banner_4.jpg"),
    DIGIT_5("banner_5.jpg"),
    DIGIT_6("banner_6.jpg"),
    DIGIT_7("banner_7.jpg"),
    DIGIT_8("banner_8.jpg"),
    DIGIT_9("banner_9.jpg"),

    ;


    public static final int size = 40;
    private final String fileName;

    ImageResource(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}

