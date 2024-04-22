/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ugarden.go.personage;

import fr.ubx.poo.ugarden.engine.Timer;
import fr.ubx.poo.ugarden.game.Direction;
import fr.ubx.poo.ugarden.game.Game;
import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.*;
import fr.ubx.poo.ugarden.go.bonus.Key;
import fr.ubx.poo.ugarden.go.decor.Decor;
import fr.ubx.poo.ugarden.go.decor.Flowers;
import fr.ubx.poo.ugarden.go.decor.Tree;
import fr.ubx.poo.ugarden.go.decor.ground.Grass;
import fr.ubx.poo.ugarden.go.decor.ground.Land;
import fr.ubx.poo.ugarden.launcher.MapEntity;
import fr.ubx.poo.ugarden.launcher.MapEntity.*;

public class Hornet extends GameObject {

    private Direction direction;


    public Hornet(Game game, Position position) {

        super(game, position);
        this.direction = Direction.UP;

    }

    public final boolean canMove(Direction direction) {
        // TO UPDATE

        int mapWidth = this.game.world().getGrid().width() - 1;
        int mapHeight = this.game.world().getGrid().height() - 1;
        int newX = direction.nextPosition(this.getPosition()).x();
        int newY = direction.nextPosition(this.getPosition()).y();

        if (newX < 0 || newX > mapWidth || newY < 0 || newY > mapHeight) {
            return false;
        } else {
            return true;
        }
    }

    public void hornetMove() {
        game.getTimerBis().stop();
        game.getTimerBis().start();

        Direction newDirection = Direction.random();

        while (!canMove(newDirection) || game.world().getGrid().get(newDirection.nextPosition(getPosition())).getClass().equals(Tree.class) ) {
            newDirection = Direction.random();
        }

        this.direction = newDirection;
        Position nextPos = newDirection.nextPosition(getPosition());
        setPosition(nextPos);

    }


    public Direction getDirection() {
        return direction;
    }
 

}
