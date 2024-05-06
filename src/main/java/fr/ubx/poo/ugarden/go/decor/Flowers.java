package fr.ubx.poo.ugarden.go.decor;

import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.personage.Gardener;

public class Flowers extends Decor{
    public Flowers(Position position) {
        super(position);
    }

    @Override
    public boolean walkableBy(Gardener gardener) {
        return gardener.canWalkOn(this);
    }
}
