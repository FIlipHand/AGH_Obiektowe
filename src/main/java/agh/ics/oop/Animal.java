package agh.ics.oop;

import java.util.Objects;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);
    private IWorldMap map;

    public Animal(IWorldMap map) {
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initial_position) {
        this.map = map;
        this.position = initial_position;
    }

    public Animal() {
    }


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
                    this.position = new_position;
                }
            }
            case BACKWARD -> {
                new_position = this.position.add(this.orientation.toUnitVector().opposite());
                if (this.map.canMoveTo(new_position)) {
                    this.position = new_position;
                }
            }
            default -> {
            }
        }
    }
    // ZADANIE 10
    // Jednym z rozwiązań tego problemu skojarzyło mi sie ze wzorcem projektowym singleton, który
    // pozwala na stowrzenie ściśle okreslonej liczby obiektów danego typu. Nie jest to może identyczny przykład,
    // ale idea jest podpobna.
    // Za każdym razem jak tworzymy obiekt Animal sprawdzamy czy w systemie nie ma już innego obiektu na tej pozycji.
    // W przypadku wzorca posiadalismy metodę statyczna, która zwracała referencje. Tutaj możemy przechowywać
    // w strukturze wszystkieobiekty tworzone przez konstruktor, i za kazdym razem sprawdzać czy nie ma już zwierzęcia,
    // które istnieje na danym polu.
}
