/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ugarden.go.decor.ground;

import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.bonus.Bonus;
import fr.ubx.poo.ugarden.go.personage.Gardener;

public class Grass extends Ground {
    public Grass(Position position) {
        super(position);
    }

    @Override
    public void takenBy(Gardener gardener) {
        Bonus bonus = getBonus();
        if (bonus != null) {
            bonus.takenBy(gardener);
        }
    }

    @Override
    public int energyConsumptionWalk() {
        return 1;
    }

}
