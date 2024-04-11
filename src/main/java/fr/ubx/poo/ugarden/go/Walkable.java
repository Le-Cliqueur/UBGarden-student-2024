package fr.ubx.poo.ugarden.go;


import fr.ubx.poo.ugarden.go.personage.Gardener;

public interface Walkable {
    boolean walkableBy(Gardener gardener);

    default int energyConsumptionWalk() {
        return 0;
    }
}
