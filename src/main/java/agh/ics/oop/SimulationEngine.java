package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private MoveDirection[] directions;

    private List<Animal> animalList;
    private IWorldMap gameMap;

    private ISimulationObserver observer;

    @Override
    public void setDirections(String[] arguments){
        this.directions = new OptionsParser().parse(arguments);
    }

    public SimulationEngine(IWorldMap map, Vector2d[] starting_positions) {
        this.animalList = new ArrayList<>();
        for (Vector2d position : starting_positions) {
            Animal new_animal = new Animal(map, position);
            new_animal.addObserver(map);
            new_animal.addObserver(map.getMapBoundaryInstance());
            animalList.add(new_animal);
            map.place(new_animal);
        }
        this.gameMap = map;
    }

    @Override
    public void run() {
        for (int i = 0; i < directions.length; ++i) {
            animalList.get(i % animalList.size()).move(directions[i]);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            observer.objectPositionChanged();
        }
    }

    @Override
    public void addObserver(ISimulationObserver simulationObserver) {
        this.observer = simulationObserver;
    }
}
