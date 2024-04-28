package fr.ubx.poo.ugarden.go.bonus;

import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.decor.Decor;
import fr.ubx.poo.ugarden.go.personage.Gardener;

public class Apple extends Bonus{
    public Apple(Position position, Decor decor) {
        super(position, decor);
    }

    public void takenBy(Gardener gardener) {
        gardener.take(this);
    }
}
