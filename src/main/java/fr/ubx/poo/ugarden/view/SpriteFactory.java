/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ugarden.view;

import fr.ubx.poo.ugarden.go.GameObject;
import fr.ubx.poo.ugarden.go.bonus.Apple;
import fr.ubx.poo.ugarden.go.bonus.Key;
import fr.ubx.poo.ugarden.go.bonus.Nest;
import fr.ubx.poo.ugarden.go.bonus.PoisonedApple;
import fr.ubx.poo.ugarden.go.decor.*;
import fr.ubx.poo.ugarden.go.decor.ground.Carrots;
import fr.ubx.poo.ugarden.go.decor.ground.Grass;
import fr.ubx.poo.ugarden.go.decor.ground.Land;
import fr.ubx.poo.ugarden.go.personage.Hedgehog;
import javafx.scene.layout.Pane;

import static fr.ubx.poo.ugarden.view.ImageResource.*;


public final class SpriteFactory {

    public static Sprite create(Pane layer, GameObject gameObject) {
        ImageResourceFactory factory = ImageResourceFactory.getInstance();
        if (gameObject instanceof PoisonedApple)
            return new Sprite(layer, factory.get(POISONED_APPLE), gameObject);
        if (gameObject instanceof Apple)
            return new Sprite(layer, factory.get(APPLE), gameObject);
        if (gameObject instanceof Carrots)
            return new Sprite(layer, factory.get(CARROTS), gameObject);
        if (gameObject instanceof Flowers)
            return new Sprite(layer, factory.get(FLOWERS), gameObject);
        if (gameObject instanceof Grass)
            return new Sprite(layer, factory.get(GRASS), gameObject);
        if (gameObject instanceof Land)
            return new Sprite(layer, factory.get(LAND), gameObject);
        if (gameObject instanceof Tree)
            return new Sprite(layer, factory.get(TREE), gameObject);
        if (gameObject instanceof Hedgehog)
            return new Sprite(layer, factory.get(HEDGEHOG), gameObject);
        if (gameObject instanceof Key)
            return new Sprite(layer, factory.get(KEY), gameObject);
        if (gameObject instanceof DoorPrevOpened)
            return new Sprite(layer, factory.get(DOOR_OPENED), gameObject);
        if (gameObject instanceof DoorNextOpened)
            return new Sprite(layer, factory.get(DOOR_OPENED), gameObject);
        if (gameObject instanceof DoorNextClosed)
            return new Sprite(layer, factory.get(DOOR_CLOSED), gameObject);
        if (gameObject instanceof Nest)
            return new Sprite(layer, factory.get(NEST), gameObject);
        throw new RuntimeException("Unsupported sprite for decor " + gameObject);
    }
}
