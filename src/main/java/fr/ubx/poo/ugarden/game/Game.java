package fr.ubx.poo.ugarden.game;

import fr.ubx.poo.ugarden.engine.Input;
import fr.ubx.poo.ugarden.engine.Timer;
import fr.ubx.poo.ugarden.go.personage.Gardener;


public class Game {

    private final Configuration configuration;
    private final World world;
    private final Gardener gardener;
    private boolean switchLevelRequested = false;
    private int switchLevel;
    private Timer timer;

    public Game(World world, Configuration configuration, Position gardenerPosition) {
        this.configuration = configuration;
        this.world = world;
        gardener = new Gardener(this, gardenerPosition);
        this.timer = new Timer(1);
    }

    public Configuration configuration() {
        return configuration;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public Gardener getGardener() {
        return this.gardener;
    }

    public World world() {
        return world;
    }

    public boolean isSwitchLevelRequested() {
        return switchLevelRequested;
    }

    public int getSwitchLevel() {
        return switchLevel;
    }

    public void requestSwitchLevel(int level) {
        this.switchLevel = level;
        switchLevelRequested = true;
    }

    public void clearSwitchLevel() {
        switchLevelRequested = false;
    }

}
