/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ugarden.go;


import fr.ubx.poo.ugarden.go.personage.Gardener;

public interface Takeable {
    default void takenBy(Gardener gardener) {
    }
}
