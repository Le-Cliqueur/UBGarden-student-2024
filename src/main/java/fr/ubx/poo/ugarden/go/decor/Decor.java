package fr.ubx.poo.ugarden.go.decor;

import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.GameObject;
import fr.ubx.poo.ugarden.go.Takeable;
import fr.ubx.poo.ugarden.go.Walkable;
import fr.ubx.poo.ugarden.go.bonus.Bonus;
import fr.ubx.poo.ugarden.go.personage.Gardener;

public abstract class Decor extends GameObject implements Walkable, Takeable {

    private Bonus bonus;

    public Decor(Position position) {
        super(position);
    }

    public Decor(Position position, Bonus bonus) {
        super(position);
        this.bonus = bonus;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean walkableBy(Gardener gardener) {
        return gardener.canWalkOn(this);
    }

    @Override
    public void update(long now) {
        super.update(now);
        if (bonus != null) bonus.update(now);
    }

}