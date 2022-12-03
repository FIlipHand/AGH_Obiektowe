package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private MoveDirection[] directions;

    private List<Animal> animalList;
    private IWorldMap gameMap;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] starting_positions) {
        this.directions = directions;
        this.animalList = new ArrayList<>();
        for (Vector2d position : starting_positions) {
            Animal new_animal = new Animal(map, position);
            // Nie jestem pewien czy to jest poprawne rozwiązanie, ponieważ nie mówiliśmy o takim castowaniu,
            // ale przynajmniej przy mojej implementacji zadań jest to (chyba) jedyne możliwe rozwiązanie...
            // I działa więc chyba nie trzeba się przejmować ¯\_(ツ)_/¯
            new_animal.addObserver(map);
            animalList.add(new_animal);
            map.place(new_animal);
        }
        this.gameMap = map;
    }

    @Override
    public void run() {
        System.out.println(gameMap);
        for (int i = 0; i < directions.length; ++i) {
            animalList.get(i % animalList.size()).move(directions[i]);
            System.out.println(gameMap);
        }
    }

}
