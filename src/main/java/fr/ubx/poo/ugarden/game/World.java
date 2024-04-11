package fr.ubx.poo.ugarden.game;

import java.util.*;

public class World {
    private final java.util.Map<Integer, Map> grids = new HashMap<>();
    private int currentLevel = 1;

    public World(int levels) {
        if (levels < 1) throw new IllegalArgumentException("Levels must be greater than 1");
    }


    public int currentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Map getGrid(int level) {
        return grids.get(level);
    }

    public Map getGrid() {
        return getGrid(currentLevel);
    }

    public void put(int level, Map grid) {
        this.grids.put(level, grid);
    }

}
