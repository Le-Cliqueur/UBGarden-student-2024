package fr.ubx.poo.ugarden.go.personage;

import fr.ubx.poo.ugarden.game.Direction;
import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.GameObject;
import fr.ubx.poo.ugarden.go.decor.Decor;

public class Hornet extends Decor {
    private Direction direction;

    public Hornet(Position position) {
        super(position);
        this.direction = Direction.DOWN;
    }

    public void changeDirection() {
        if (direction == Direction.DOWN) {
            this.direction = Direction.UP;
        } else {
            this.direction = Direction.DOWN;
        }
    }

    public int rdm() {
        return (int) (Math.random() * 5) + 1;
    }

    public void update(long now) {
        changeDirection();
    }

    public Direction getDirection() {
        return direction;
    }
}
