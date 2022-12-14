package agh.ics.oop;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private Set<Vector2d> grassPositions;
    private Map<Vector2d, Grass> grassMap;

    public GrassField(int n) {
        this.grassPositions = new HashSet<>();
        this.grassMap = new HashMap<>();
        Random random = new Random();
        while (grassPositions.size() < n) {
            Vector2d grassPosition = new Vector2d(random.nextInt((int) Math.sqrt(n * 10)),
                    random.nextInt((int) Math.sqrt(n * 10)));
            grassPositions.add(grassPosition);
        }
        grassMap = grassPositions.stream().collect(Collectors.toMap(vector2d -> vector2d, Grass::new));
        grassPositions.forEach(e -> this.mapBoundary.addElementToBoundary(grassMap.get(e)));
    }


//    public Pair<Vector2d, Vector2d> getMapBoundaries() {
//        Supplier<Stream<Vector2d>> streamSupplier = () ->
//                Stream.concat(grassPositions.stream(), this.animalMap.keySet().stream());
//
//        Vector2d minPosition = new Vector2d(streamSupplier.get().mapToInt(Vector2d::getX).min().orElse(0),
//                streamSupplier.get().mapToInt(Vector2d::getY).min().orElse(0));
//
//        Vector2d maxPosition = new Vector2d(streamSupplier.get().mapToInt(Vector2d::getX).max().orElse(0),
//                streamSupplier.get().mapToInt(Vector2d::getY).max().orElse(0));
//
//        return new Pair<>(minPosition, maxPosition);
//    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        // to nie jest dobre rozwiązanie, ale działa
        return !isOccupied(position) || objectAt(position) instanceof Grass;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (this.animalMap.containsKey(position)) {
            return this.animalMap.get(position);
        }
        return this.grassMap.get(position);
    }

}
