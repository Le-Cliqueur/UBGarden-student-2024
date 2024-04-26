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

            int width = 0;
            int height = 0;

            for (int j = 0; j < levelEntityCode.length()-1; j++) {
                if (levelEntityCode.charAt(j) != 'x') {
                    height++;
                } else {
                    width++;
                    height = 0;
                }
            }

            MapLevel mapLevel = new MapLevel(width+1, height);
            System.out.println(width + "   " + height);

            int onTheLine = 0;
            int onTheCol = 0;

            for (int j = 0; j < levelEntityCode.length(); j++) {
                if (levelEntityCode.charAt(j) != 'x') {
                    mapLevel.set(onTheLine, onTheCol, MapEntity.fromCode(levelEntityCode.charAt(j)));
                    onTheCol++;

                } else {
                    onTheLine++;
                    onTheCol = 0;
                }
            }

            System.out.println("11");
            Position gardenerPosition = mapLevel.getGardenerPosition();
            Position nestPosition = mapLevel.getNestPosition();
            System.out.println("22");
            if (gardenerPosition == null)
                throw new RuntimeException("Gardener not found");
            System.out.println("33");
            Configuration configuration = getConfiguration(properties);
            System.out.println("44");
            World world = new World(Integer.parseInt(properties.getProperty("levels")));
            System.out.println("55");
            Game game = new Game(world, configuration, gardenerPosition, nestPosition);
            System.out.println("66");
            Map level = new Level(game, 1, mapLevel);
            System.out.println("77");
            world.put(1, level);
            System.out.println("88");
            return game;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Game load() {
        Properties emptyConfig = new Properties();
        MapLevel mapLevel = new MapLevelDefault();
        System.out.println("1");
        Position gardenerPosition = mapLevel.getGardenerPosition();
        Position nestPosition = mapLevel.getNestPosition();
        System.out.println("2");
        if (gardenerPosition == null)
            throw new RuntimeException("Gardener not found");
        System.out.println("3");
        Configuration configuration = getConfiguration(emptyConfig);
        System.out.println("4");
        World world = new World(1);
        System.out.println("5");
        Game game = new Game(world, configuration, gardenerPosition, nestPosition);
        System.out.println("6");
        Map level = new Level(game, 1, mapLevel);
        System.out.println("7");
        world.put(1, level);
        System.out.println("8");
        return game;
    }

    private static class LoadSingleton {
        static final GameLauncher INSTANCE = new GameLauncher();
    }

}
