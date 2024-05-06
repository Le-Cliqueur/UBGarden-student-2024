package fr.ubx.poo.ugarden.launcher;

import fr.ubx.poo.ugarden.game.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GameLauncher {

    private int levels;
    private MapLevel[] mapLevels;

    private GameLauncher() {
    }

    public GameLauncher (int levels, MapLevel[] mapLevels) {
        this.levels = levels;
        this.mapLevels = mapLevels;
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
        Properties properties = new Properties();
        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
            properties.load(in);

            String levelEntityCode = properties.getProperty("level1");

            String comp = properties.getProperty("compression");

            int width = 0;
            int height = 0;

            MapLevel mapLevel = new MapLevel(width, height);

            if (comp.equals("false")) {
                for (int j = 0; j < levelEntityCode.length()-1; j++) {
                    if (levelEntityCode.charAt(j) != 'x') {
                        height++;
                    } else {
                        width++;
                        height = 0;
                    }
                }

                mapLevel = new MapLevel(height, width+1);

                int onTheLine = 0;
                int onTheCol = 0;

                for (int j = 0; j < levelEntityCode.length(); j++) {
                    if (levelEntityCode.charAt(j) != 'x') {
                        mapLevel.set(onTheLine, onTheCol, MapEntity.fromCode(levelEntityCode.charAt(j)));
                        onTheLine++;

                    } else {
                        onTheCol++;
                        onTheLine = 0;
                    }
                }
            } else {

                for (int j = 0; j < levelEntityCode.length()-1; j++) {
                    if (levelEntityCode.charAt(j) != 'x') {
                        if (Character.isDigit(levelEntityCode.charAt(j))) {
                            height--;
                            height += (((int) (levelEntityCode.charAt(j))) - 48);
                        } else {
                            height++;
                        }
                    } else {
                        width++;
                        height = 0;
                    }
                }

                mapLevel = new MapLevel(height, width+1);

                int onTheLine = 0;
                int onTheCol = 0;

                for (int i = 0; i < levelEntityCode.length(); i++) {
                    if (levelEntityCode.charAt(i) != 'x') {
                        if (Character.isDigit(levelEntityCode.charAt(i))) {
                            int val = (int) levelEntityCode.charAt(i) - 48;
                            for (int j = 0; j < val; j++) {
                                mapLevel.set(onTheLine-1+j, onTheCol, MapEntity.fromCode(levelEntityCode.charAt(i-1)));
                            }
                            onTheLine += val - 1;
                        } else {
                            mapLevel.set(onTheLine, onTheCol, MapEntity.fromCode(levelEntityCode.charAt(i)));
                            onTheLine++;
                        }

                    } else {
                        onTheCol++;
                        onTheLine = 0;
                    }
                }
            }

            Position gardenerPosition = mapLevel.getGardenerPosition();
            if (gardenerPosition == null)
                throw new RuntimeException("Gardener not found");
            Configuration configuration = getConfiguration(properties);
            World world = new World(Integer.parseInt(properties.getProperty("levels")));
            Game game = new Game(world, configuration, gardenerPosition);
            Map level = new Level(game, 1, mapLevel);
            world.put(1, level);
            return game;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Game load() {
        Properties emptyConfig = new Properties();
        MapLevel mapLevel = new MapLevelDefault();
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
