package fr.ubx.poo.ugarden.launcher;


import static fr.ubx.poo.ugarden.launcher.MapEntity.*;

public class MapLevelDefault extends MapLevel {
    private final static int width = 18;
    private final static int height = 8;
    private final MapEntity[][] level1 = {
            {Grass, Grass, Grass, Grass, Grass, Land, Grass, Land, Flowers, Key, Key, Grass, Key, Carrots, Grass, Grass, Grass, Grass},
            {Grass, Gardener, Grass, Grass, Grass, Land, Land, Land, Apple, Tree, Grass, Grass, Tree, Tree, Apple, Carrots, Grass, Grass},
            {Grass, Grass, Grass, Grass, Grass, Land, Land, Land, Flowers, Flowers, Flowers, Grass, Grass, Tree, Carrots, Carrots, Carrots, Carrots},
            {Grass, Grass, Grass, Grass, Grass, Land, Land, Land, Carrots, Carrots, Carrots, Carrots, Grass, Tree, Flowers, Carrots, Carrots, Flowers},
            {PoisonedApple, Tree, Grass, Tree, Grass, Grass, Flowers, Flowers, Carrots, Carrots, Carrots, Carrots, Grass, Tree, Flowers, Carrots, Nest, Flowers},
            {Grass, Tree, Tree, Tree, PoisonedApple, Flowers, Grass, Grass, Carrots, Carrots, Carrots, Carrots, Grass, Grass, Flowers, Flowers, Flowers, Flowers},
            {Grass, Grass, Grass, PoisonedApple, Grass, Grass, Grass, Grass, Carrots, Carrots, Carrots, Carrots, Grass, Grass, Grass, Grass, Hedgehog, Grass},
            {Grass, Tree, Grass, Tree, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass}
    };

    public MapLevelDefault() {
        super(width, height);
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                set(i, j, level1[j][i]);
    }
}
