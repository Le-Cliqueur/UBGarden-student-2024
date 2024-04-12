package fr.ubx.poo.ugarden.go.decor.ground;

import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.personage.Gardener;

public class Carrots extends Ground{
    public Carrots(Position position) {
        super(position);
    }

    public boolean walkableBy(Gardener gardener) {
        return gardener.canWalkOn(this);
    }
}
