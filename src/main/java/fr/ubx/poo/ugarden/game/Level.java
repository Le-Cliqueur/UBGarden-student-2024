package fr.ubx.poo.ugarden.game;

import fr.ubx.poo.ugarden.go.bonus.Apple;
import fr.ubx.poo.ugarden.go.bonus.Key;
import fr.ubx.poo.ugarden.go.bonus.Nest;
import fr.ubx.poo.ugarden.go.bonus.PoisonedApple;
import fr.ubx.poo.ugarden.go.decor.*;
import fr.ubx.poo.ugarden.go.decor.ground.Carrots;
import fr.ubx.poo.ugarden.go.decor.ground.Grass;
import fr.ubx.poo.ugarden.go.decor.ground.Land;
import fr.ubx.poo.ugarden.go.personage.Hedgehog;
import fr.ubx.poo.ugarden.launcher.MapEntity;
import fr.ubx.poo.ugarden.launcher.MapLevel;

import java.util.Collection;
import java.util.HashMap;

public class Level implements Map {

    private final int level;
    private final int width;

    private final int height;

    private final java.util.Map<Position, Decor> decors = new HashMap<>();

    public Level(Game game, int level, MapLevel entities) {
        this.level = level;
        this.width = entities.width();
        this.height = entities.height();

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                Position position = new Position(level, i, j);
                MapEntity mapEntity = entities.get(i, j);
                switch (mapEntity) {
                    // Add cases to different entity
                    case PoisonedApple: {
                        Decor grass = new Grass(position);
                        grass.setBonus(new PoisonedApple(position, grass));
                        decors.put(position, grass);
                        break;
                    }
                    case Apple: {
                        Decor grass = new Grass(position);
                        grass.setBonus(new Apple(position, grass));
                        decors.put(position, grass);
                        break;
                    }
                    case Carrots:
                        decors.put(position, new Carrots(position));
                        break;
                    case Flowers:
                        decors.put(position, new Flowers(position));
                        break;
                    case Grass:
                        decors.put(position, new Grass(position));
                        break;
                    case Land:
                        decors.put(position, new Land(position));
                        break;
                    case Tree:
                        decors.put(position, new Tree(position));
                        break;
                    case Hedgehog:
                        decors.put(position, new Hedgehog(position));
                        break;
                    case Key: {
                        Decor grass = new Grass(position);
                        grass.setBonus(new Key(position, grass));
                        decors.put(position, grass);
                        break;
                    }
                    case DoorPrevOpened:
                        decors.put(position, new DoorPrevOpened(position));
                        break;
                    case DoorNextOpened:
                        decors.put(position, new DoorNextOpened(position));
                        break;
                    case DoorNextClosed:
                        decors.put(position, new DoorNextClosed(position));
                        break;
                    case Nest: {
                        Decor grass = new Grass(position);
                        grass.setBonus(new Nest(position, grass));
                        decors.put(position, grass);
                        break;
                    }

                    default:
                        throw new RuntimeException("EntityCode " + mapEntity.name() + " not processed");
                }
            }
    }

    @Override
    public int width() {
        return this.width;
    }

    @Override
    public int height() {
        return this.height;
    }

    public Decor get(Position position) {
        return decors.get(position);
    }

    @Override
    public void remove(Position position) {
        decors.remove(position);
    }

    public Collection<Decor> values() {
        return decors.values();
    }


    @Override
    public boolean inside(Position position) {
        return true;
    }

    @Override
    public void set(Position position, Decor decor) {
        if (!inside(position))
            throw new IllegalArgumentException("Illegal Position");
        if (decor != null)
            decors.put(position, decor);
    }


}
