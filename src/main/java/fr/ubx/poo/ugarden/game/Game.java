package fr.ubx.poo.ugarden.game;

import fr.ubx.poo.ugarden.engine.Input;
import fr.ubx.poo.ugarden.engine.Timer;
import fr.ubx.poo.ugarden.go.personage.Gardener;
import fr.ubx.poo.ugarden.go.personage.Hornet;


public class Game {

    private final Configuration configuration;
    private final World world;
    private final Gardener gardener;
    int index;
    private Hornet[] hornets;
    private boolean switchLevelRequested = false;
    private int switchLevel;
    private Position nestPosition;
    private Timer timer;
    private Timer timerBis;
    private Timer hornetTimer;

    public Game(World world, Configuration configuration, Position gardenerPosition, Position nestPosition) {
        this.configuration = configuration;
        this.world = world;
        gardener = new Gardener(this, gardenerPosition);
        this.nestPosition = nestPosition;
        index = 0;
        hornets = new Hornet[100];
        this.timer = new Timer(1);
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

    public Position getNestPosition() {
        return this.nestPosition;
    }

    public Hornet[] getHornets() {
        return this.hornets;
    }
    public int getIndex() {
        return this.index;
    }

    public void addHornet() {
        this.hornets[index] = new Hornet(this, nestPosition);
        index++;
        hornetTimer.stop();
        hornetTimer.start();
    }

    public void deleteHornet(int idx) {
        Hornet[] nh = new Hornet[100];

        for (int i = 0; i < index; i++) {
            if (i < idx) {
                nh[i] = hornets[i];
            } else {
                nh[i] = hornets[i+1];
            }
        }

        this.index--;
        this.hornets = nh;
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
