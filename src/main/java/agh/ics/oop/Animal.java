package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Animal implements IMapElement {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);
    private IWorldMap map;
    private List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map) {
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initial_position) {
        this(map);
        this.position = initial_position;
    }

    public Animal() {
        this(new RectangularMap(4, 4));
    }


    @Override
    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return switch (orientation) {
            case NORTH -> "^";
            case SOUTH -> "v";
            case WEST -> "<";
            case EAST -> ">";
        };
    }

    public boolean isAt(Vector2d other_position) {
        return Objects.equals(this.position, other_position);
    }

    public void move(MoveDirection direction) {
        Vector2d new_position;
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                new_position = this.position.add(this.orientation.toUnitVector());
                if (this.map.canMoveTo(new_position)) {
                    positionChanged(this.position, new_position);
                    this.position = new_position;
                }
            }
            case BACKWARD -> {
                new_position = this.position.add(this.orientation.toUnitVector().opposite());
                if (this.map.canMoveTo(new_position)) {
                    positionChanged(this.position, new_position);
                    this.position = new_position;
                }
            }
            default -> {
            }
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.observers.forEach(e -> e.positionChanged(oldPosition, newPosition));
    }
}
