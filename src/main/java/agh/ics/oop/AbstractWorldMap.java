package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animalMap = new HashMap<>();
    protected MapBoundary mapBoundary = new MapBoundary();

    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (this.canMoveTo(position)) {
            animalMap.put(position, animal);
            return true;
        }
        throw new IllegalArgumentException("Animal cannot be place on this field.");
    }

    @Override
    public String toString() {
//        Pair<Vector2d, Vector2d> mapBoundaries = this.getMapBoundaries();
        Pair<Vector2d, Vector2d> mapBoundaries = getMapBoundaries();
        return new MapVisualizer(this).draw(mapBoundaries.getFirst(), mapBoundaries.getSecond());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = this.animalMap.get(oldPosition);
        this.animalMap.remove(oldPosition);
        this.animalMap.put(newPosition, animal);
    }

    @Override
    public Pair<Vector2d, Vector2d> getMapBoundaries() {
        return this.mapBoundary.getMapBoundaries();
    }

    @Override
    public MapBoundary getMapBoundaryInstance() {
        return this.mapBoundary;
    }
}
