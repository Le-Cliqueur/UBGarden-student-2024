package fr.ubx.poo.ugarden.go;

import fr.ubx.poo.ugarden.go.bonus.Key;

public interface TakeVisitor {

    // Key
    default void take(Key key) {
    }

    // TODO
}
