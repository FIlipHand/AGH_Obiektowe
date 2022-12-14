package agh.ics.oop;

import java.util.*;

public class MapBoundary implements IPositionChangeObserver {

    // To nie jest dobre rozwiązanie, ponieważ mogą być dwa elementy o takich samych koordynatach
    // W innym rozwiązaniu możemy przechowywać np IWorldMap ale to wymaga sporych modifikacji w kodzie
    // więc niech będzie na razie tak...
    private TreeSet<Vector2d> sortedX = new TreeSet<>((o1, o2) -> {
        int tmp = o1.x - o2.x;
        if (tmp == 0)
            return o1.y - o2.y;
        return tmp;
    });
    private TreeSet<Vector2d> sortedY = new TreeSet<>((o1, o2) -> {
        int tmp = o1.y - o2.y;
        if (tmp == 0)
            return o1.x - o2.x;
        return tmp;
    });

    public TreeSet<Vector2d> getSortedX() {
        return sortedX;
    }

    public TreeSet<Vector2d> getSortedY() {
        return sortedY;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        sortedX.remove(oldPosition);
        sortedY.remove(oldPosition);

        sortedX.add(newPosition);
        sortedY.add(newPosition);
    }

    public void addElementToBoundary(IMapElement element){
        sortedX.add(element.getPosition());
        sortedY.add(element.getPosition());
    }

    public Pair<Vector2d, Vector2d> getMapBoundaries(){
        return new Pair<>(new Vector2d(sortedX.first().x, sortedY.first().y), new Vector2d(sortedX.last().x, sortedY.last().y));
    }
}
