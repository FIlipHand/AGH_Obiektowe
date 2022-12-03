package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animalMap = new HashMap<>();

    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (this.canMoveTo(position)) {
            animalMap.put(position, animal);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        Pair<Vector2d, Vector2d> mapBoundaries = this.getMapBoundaries();
        return new MapVisualizer(this).draw(mapBoundaries.getFirst(), mapBoundaries.getSecond());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = this.animalMap.get(oldPosition);
        this.animalMap.remove(oldPosition);
        this.animalMap.put(newPosition, animal);
    }
}
