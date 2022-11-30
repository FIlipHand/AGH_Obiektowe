package agh.ics.oop;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap implements IWorldMap {

    private int nGrassFields;
    private Set<Vector2d> grassPositions;
    private List<Grass> grassList;

    public GrassField(int n) {
        this.nGrassFields = n;
        this.grassPositions = new HashSet<>();
        Random random = new Random();
        while (grassPositions.size() < nGrassFields) {
            Vector2d grassPosition = new Vector2d(random.nextInt((int) Math.sqrt(nGrassFields * 10)),
                    random.nextInt((int) Math.sqrt(nGrassFields * 10)));
            grassPositions.add(grassPosition);
        }
        grassList = grassPositions.stream().map(Grass::new).collect(Collectors.toList());
    }


    public Pair<Vector2d, Vector2d> getMapBoundaries() {
        Supplier<Stream<Vector2d>> streamSupplier = () ->
                Stream.concat(grassPositions.stream(), this.animalList.stream().map(Animal::getPosition));

        Vector2d minPosition = new Vector2d(streamSupplier.get().mapToInt(Vector2d::getX).min().orElse(0),
                streamSupplier.get().mapToInt(Vector2d::getY).min().orElse(0));

        Vector2d maxPosition = new Vector2d(streamSupplier.get().mapToInt(Vector2d::getX).max().orElse(0),
                streamSupplier.get().mapToInt(Vector2d::getY).max().orElse(0));

        return new Pair<>(minPosition, maxPosition);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        // to nie jest dobre rozwiązanie, ale działa
        return new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE).precedes(position) &&
                Vector2d.VEC_0_0.follows(position) && (!isOccupied(position) || objectAt(position).getClass() == Grass.class);
    }

    @Override
    public Object objectAt(Vector2d position) {
//        było podejście do fajnego streama ale niestety klasy obiektów sie nie zgadzają
//        return animalList.stream().filter(animal -> animal.getPosition().equals(position))
//                .findFirst()
//                .orElse(grassList.stream().filter(grass -> grass.getPosition().equals(position)).findFirst().orElse(null));


        for (Animal animal : this.animalList) {
            if (animal.getPosition().equals(position))
                return animal;
        }

        for (Grass grass : this.grassList) {
            if (grass.getPosition().equals(position))
                return grass;
        }
        return null;


    }

}
