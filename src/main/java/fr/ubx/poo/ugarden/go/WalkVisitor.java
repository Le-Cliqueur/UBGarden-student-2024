package fr.ubx.poo.ugarden.go;

import fr.ubx.poo.ugarden.go.decor.Decor;
import fr.ubx.poo.ugarden.go.decor.Tree;
import fr.ubx.poo.ugarden.go.decor.ground.Carrots;

public interface WalkVisitor {
    default boolean canWalkOn(Decor decor) {
        return true;
    }

    default boolean canWalkOn(Tree tree) {
        return false;
    }

    default boolean canWalkOn(Carrots carrots) {
        return false;
    }

    // TODO
}
