package fr.ubx.poo.ugarden.launcher;

import fr.ubx.poo.ugarden.game.*;

import java.io.File;
import java.util.Properties;

public class GameLauncher {

    private int levels;
    private MapLevel[] mapLevels;

    private GameLauncher() {
    }

    public static GameLauncher getInstance() {
        return LoadSingleton.INSTANCE;
    }

    private int integerProperty(Properties properties, String name, int defaultValue) {
        return Integer.parseInt(properties.getProperty(name, Integer.toString(defaultValue)));
    }

    private boolean booleanProperty(Properties properties, String name, boolean defaultValue) {
        return Boolean.parseBoolean(properties.getProperty(name, Boolean.toString(defaultValue)));
    }

    private Configuration getConfiguration(Properties properties) {

        // Load parameters
        int hornetMoveFrequency = integerProperty(properties, "hornetMoveFrequency", 1);
        int gardenerEnergy = integerProperty(properties, "gardenerEnergy", 100);
        int energyBoost = integerProperty(properties, "energyBoost", 50);
        int energyRecoverDuration = integerProperty(properties, "energyRecoverDuration", 1);
        int diseaseDuration = integerProperty(properties, "diseaseDuration", 5);

        return new Configuration(gardenerEnergy, energyBoost, hornetMoveFrequency, energyRecoverDuration, diseaseDuration);
    }

    public Game load(File file) {
        return null;
    }

    public Game load() {
        Properties emptyConfig = new Properties();
        MapLevel mapLevel = new MapLevelDefaultStart();
        Position gardenerPosition = mapLevel.getGardenerPosition();
        if (gardenerPosition == null)
            throw new RuntimeException("Gardener not found");
        Configuration configuration = getConfiguration(emptyConfig);
        World world = new World(1);
        Game game = new Game(world, configuration, gardenerPosition);
        Map level = new Level(game, 1, mapLevel);
        world.put(1, level);
        return game;
    }

    private static class LoadSingleton {
        static final GameLauncher INSTANCE = new GameLauncher();
    }

}
