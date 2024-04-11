package fr.ubx.poo.ugarden.go;

import fr.ubx.poo.ugarden.go.decor.Decor;
import fr.ubx.poo.ugarden.go.decor.Tree;

public interface WalkVisitor {
    default boolean canWalkOn(Decor decor) {
        return true;
    }

    default boolean canWalkOn(Tree tree) {
        return false;
    }

    // TODO
}
