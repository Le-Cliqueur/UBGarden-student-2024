package fr.ubx.poo.ugarden.go.decor.ground;

import fr.ubx.poo.ugarden.game.Position;

public class Land extends Ground {
    public Land(Position position) {
        super(position);
    }

    public int energyConsumptionWalk() {
        return 2;
    }
}


