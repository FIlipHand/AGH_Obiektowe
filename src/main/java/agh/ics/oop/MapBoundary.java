package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    // To nie jest dobre rozwiązanie, ponieważ mogą być dwa elementy o takich samych koordynatach
    // W innym rozwiązaniu możemy przechowywać np IWorldMap ale to wymaga sporych modifikacji w kodzie
    // więc niech będzie na razie tak...
    private TreeSet<Vector2d> sortedX = new TreeSet<>(Comparator.comparingInt(Vector2d::getX).thenComparing(Vector2d::getY));
    private TreeSet<Vector2d> sortedY = new TreeSet<>(Comparator.comparingInt(Vector2d::getY).thenComparing(Vector2d::getX));

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        sortedX.remove(oldPosition);
        sortedY.remove(oldPosition);

        sortedX.add(newPosition);
        sortedY.add(newPosition);
    }

    public void addElementToBoundary(IMapElement element) {
        sortedX.add(element.getPosition());
        sortedY.add(element.getPosition());
    }

    public Pair<Vector2d, Vector2d> getMapBoundaries() {
        return new Pair<>(new Vector2d(sortedX.first().x, sortedY.first().y), new Vector2d(sortedX.last().x, sortedY.last().y));
    }
}
