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
//        IntStream.range(0, directions.length).forEach(i -> animalList.get(i % animalList.size()).move(directions[i]));
    }

}
