package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap {
    protected List<Animal> animalList = new ArrayList<>();

    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (this.canMoveTo(position)) {
            this.animalList.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public abstract Pair<Vector2d, Vector2d> getMapBoundaries();
    @Override
    public String toString() {
        Pair<Vector2d, Vector2d> mapBoundaries = this.getMapBoundaries();
        return new MapVisualizer(this).draw(mapBoundaries.getFirst(), mapBoundaries.getSecond());
    }
}
