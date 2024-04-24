/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ugarden.go.personage;

import fr.ubx.poo.ugarden.engine.Timer;
import fr.ubx.poo.ugarden.game.Direction;
import fr.ubx.poo.ugarden.game.Game;
import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.GameObject;
import fr.ubx.poo.ugarden.go.Movable;
import fr.ubx.poo.ugarden.go.TakeVisitor;
import fr.ubx.poo.ugarden.go.WalkVisitor;
import fr.ubx.poo.ugarden.go.bonus.Key;
import fr.ubx.poo.ugarden.go.decor.Decor;
import fr.ubx.poo.ugarden.go.decor.Flowers;
import fr.ubx.poo.ugarden.go.decor.ground.Grass;
import fr.ubx.poo.ugarden.launcher.MapEntity;
import fr.ubx.poo.ugarden.launcher.MapEntity.*;

public class Gardener extends GameObject implements Movable, TakeVisitor, WalkVisitor {

    private int energy;
    private Direction direction;
    private boolean moveRequested = false;
    private int nbKey;
    private int diseaseLevel;

    public Gardener(Game game, Position position) {

        super(game, position);
        this.direction = Direction.DOWN;
        this.energy = game.configuration().gardenerEnergy();
        this.nbKey = 0;
        this.diseaseLevel = 1;
    }

    @Override
    public void take(Key key) {
// TODO
        this.nbKey++;
        key.remove();
        System.out.println("I am taking the key, I should do something ...");

    }

    public int getNbKey() {
        return this.nbKey;
    }

    public int getNbDisease() {
        return this.diseaseLevel;
    }



    public int getEnergy() {
        return this.energy;
    }


    public void requestMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
            setModified(true);
        }
        moveRequested = true;
    }

    @Override
    public final boolean canMove(Direction direction) {
        // TO UPDATE

        int mapWidth = this.game.world().getGrid().width() - 1;
        int mapHeight = this.game.world().getGrid().height() - 1;
        int newX = direction.nextPosition(this.getPosition()).x();
        int newY = direction.nextPosition(this.getPosition()).y();

        if (newX < 0 || newX > mapWidth || newY < 0 || newY > mapHeight || !(game.world().getGrid().get(direction.nextPosition(getPosition())).walkableBy(this))) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void doMove(Direction direction) {
        // Restart the timer
        this.game.getTimer().stop();
        this.game.getTimer().start();
        Position nextPos = direction.nextPosition(getPosition());
        Decor next = game.world().getGrid().get(nextPos);
        setPosition(nextPos);
        if (next != null) {
            next.takenBy(this);
        }
    }


    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
                this.diseaseLevel = this.game.world().getGrid().get(this.getPosition()).energyConsumptionWalk();
                this.hurt(this.game.world().getGrid().get(this.getPosition()).energyConsumptionWalk());
            }
        }
        moveRequested = false;
    }

    public void hurt(int damage) {
        this.energy -= damage;
    }

    public void hurt() {
        hurt(20);
    }

    public Direction getDirection() {
        return direction;
    }


}
