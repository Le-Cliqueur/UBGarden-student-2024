package fr.ubx.poo.ugarden.launcher;


import static fr.ubx.poo.ugarden.launcher.MapEntity.*;

public class MapLevelDefaultStart extends MapLevel {


    private final static int width = 18;
    private final static int height = 8;
    private final MapEntity[][] level1 = {
            {Nest, Grass, Grass, Grass, Grass, Grass, Apple, Apple, Apple, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass},
            {Grass, Gardener, Grass, Hedgehog, Grass, Grass, Grass, Grass, Grass, Tree, Nest, Grass, Tree, Tree, Grass, Grass, Grass, Grass},
            {Grass, Grass, Grass, PoisonedApple, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Tree, Grass, Grass, Grass, Grass},
            {Grass, Grass, Grass, Grass, Grass, Grass, Hedgehog, Grass, Grass, Grass, Grass, Grass, Grass, Tree, Grass, Grass, Grass, Grass},
            {Grass, Tree, Grass, Tree, Grass, Grass, Grass, Grass, Grass, Flowers, Grass, Grass, Grass, Tree, Grass, Grass, Key, Grass},
            {Grass, Tree, Tree, Tree, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass},
            {Grass, Grass, Grass, Grass, Land, Grass, Grass, DoorPrevOpened, DoorNextClosed, DoorNextOpened, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass},
            {Grass, Tree, Grass, Tree, Grass, Grass, Grass, Carrots, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass}
    };

    public MapLevelDefaultStart() {
        super(width, height);
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                set(i, j, level1[j][i]);
    }


}
