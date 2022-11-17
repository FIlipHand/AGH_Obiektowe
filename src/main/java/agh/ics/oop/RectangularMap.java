package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    private final int width;
    private final int height;

    // Nie jestem zadowolony z tego rozwiązania, ale było ono jedynym, które udało się zaimplementować.
    // Wcześniej była tutaj HashMap <Vector2d, Animal>, jednak bez sensu usuwać/zmieniać klucze jak zwierze się ruszy.
    // Następnie była tutaj Animal[][]. Niestety w momencie, w którym zwierze się ruszało, nie dało się go
    // wynullować ze starej pozycji, ponieważ interface nie udostępniał takiej metody, więc bez naruszania IWorldMap
    // takie rozwiązania nie były możliwe ;_;

    // Jeśli edycja IWorldMap była możliwa to rozwiązanie tego zadania byłoby na pewno bardziej eleganckie

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
//        for (Animal animal : this.animalList) {
//            if (animal.getPosition().equals(position))
//                return animal;
//        }
//        return null;
        return animalList.stream().filter(animal -> animal.isAt(position)).findFirst().orElse(null);
    }

    public Pair<Vector2d, Vector2d> getMapBoundaries() {
        return new Pair<>(Vector2d.VEC_0_0, new Vector2d(width, height));
    }
}
