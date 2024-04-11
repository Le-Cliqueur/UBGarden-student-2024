package fr.ubx.poo.ugarden.launcher;


import static fr.ubx.poo.ugarden.launcher.MapEntity.*;

public class MapLevelDefaultStart extends MapLevel {


    private final static int width = 18;
    private final static int height = 8;
    private final MapEntity[][] level1 = {
            {Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass},
            {Grass, Gardener, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Tree, Grass, Grass, Tree, Tree, Grass, Grass, Grass, Grass},
            {Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Tree, Grass, Grass, Grass, Grass},
            {Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Tree, Grass, Grass, Grass, Grass},
            {Grass, Tree, Grass, Tree, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Tree, Grass, Grass, Key, Grass},
            {Grass, Tree, Tree, Tree, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass},
            {Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass},
            {Grass, Tree, Grass, Tree, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass, Grass}
    };

    public MapLevelDefaultStart() {
        super(width, height);
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                set(i, j, level1[j][i]);
    }


}
