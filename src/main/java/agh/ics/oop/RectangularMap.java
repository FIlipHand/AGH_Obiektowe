package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class RectangularMap implements IWorldMap {
    private final int width;
    private final int height;

    // Nie jestem zadaowolony z tego rozwiązania, ale było ono jedynym, które udało się zaimplementować.
    // Wcześniej była tutaj HashMap <Vector2d, Animal>, jednak bez sensu usuwać/zmieniać klucze jak zwierze się ruszy.
    // Kolejnie była tutaj Animal[][]. Niestety w momencie, w którym zwierze się ruszało, nie dało się go
    // wynullować ze starej pozycji, ponieważ interface nie udostepniał takiej metody, więc bez naruszania IWorldMap
    // takie rozwiązania nie były możliwe ;_;

    // Jeśli edycja IWorldMap była możliwa to rozwiązanie tego zadania byłoby na pewno bardziej elegantne

    private List<Animal> animalList;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.animalList = new LinkedList<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return new Vector2d(width, height).precedes(position) && Vector2d.VEC_0_0.follows(position) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            this.animalList.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : this.animalList) {
            if (position.equals(animal.getPosition()))
                return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : this.animalList) {
//            System.out.println(animal.getPosition());
//            System.out.println(position);
            if (animal.getPosition().equals(position))
                return animal;
        }
        return null;
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(Vector2d.VEC_0_0, new Vector2d(width, height));
    }
}
