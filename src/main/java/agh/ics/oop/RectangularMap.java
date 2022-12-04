package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return new Vector2d(width, height).precedes(position) && Vector2d.VEC_0_0.follows(position) && !isOccupied(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
//        return animalList.stream().filter(animal -> animal.isAt(position)).findFirst().orElse(null);
        return animalMap.get(position);
    }

//    public Pair<Vector2d, Vector2d> getMapBoundaries() {
//        return new Pair<>(Vector2d.VEC_0_0, new Vector2d(width, height));
//    }
}
