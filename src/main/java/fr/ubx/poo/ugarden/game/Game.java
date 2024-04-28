package fr.ubx.poo.ugarden.game;

import fr.ubx.poo.ugarden.engine.Input;
import fr.ubx.poo.ugarden.engine.Timer;
import fr.ubx.poo.ugarden.go.personage.Gardener;
import fr.ubx.poo.ugarden.go.personage.Hornet;


public class Game {

    private final Configuration configuration;
    private final World world;
    private final Gardener gardener;
    private boolean switchLevelRequested = false;
    private int switchLevel;
    private Timer timer;
    private Timer timerBis;
    private Timer hornetTimer;

    public Game(World world, Configuration configuration, Position gardenerPosition) {
        this.configuration = configuration;
        this.world = world;
        gardener = new Gardener(this, gardenerPosition);
        this.timer = new Timer(configuration.energyRecoverDuration());
        this.timerBis = new Timer(1);
        this.hornetTimer = new Timer(10);
    }
    public Configuration configuration() {
        return configuration;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public Timer getTimerBis() {
        return this.timerBis;
    }

    public Timer getHornetTimer() {
        return this.hornetTimer;
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
