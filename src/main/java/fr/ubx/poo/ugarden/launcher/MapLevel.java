package fr.ubx.poo.ugarden.launcher;

import fr.ubx.poo.ugarden.game.Position;

import static fr.ubx.poo.ugarden.launcher.MapEntity.Gardener;
import static fr.ubx.poo.ugarden.launcher.MapEntity.Grass;

public class MapLevel {

    private final int width;
    private final int height;
    private final MapEntity[][] grid;


    private Position gardenerPosition = null;

    public MapLevel(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new MapEntity[height][width];
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public MapEntity get(int i, int j) {
        return grid[j][i];
    }

    public void set(int i, int j, MapEntity mapEntity) {
        grid[j][i] = mapEntity;
    }

    public Position getGardenerPosition() {
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                if (grid[j][i] == Gardener) {
                    if (gardenerPosition != null)
                        throw new RuntimeException("Multiple definition of gardener");
                    set(i, j, Grass);
                    // Gardener can be only on level 1
                    gardenerPosition = new Position(1, i, j);
                }
        return gardenerPosition;
    }

}
