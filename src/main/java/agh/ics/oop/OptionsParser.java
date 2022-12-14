package agh.ics.oop;

import java.util.stream.Stream;

public class OptionsParser {

    public MoveDirection[] parse(String[] args) {
        return Stream.of(args)
                .map(instruction -> switch (instruction) {
                    case "f", "forward" -> (MoveDirection.FORWARD);
                    case "b", "backward" -> (MoveDirection.BACKWARD);
                    case "r", "right" -> (MoveDirection.RIGHT);
                    case "l", "left" -> (MoveDirection.LEFT);
                    default -> throw new IllegalArgumentException(instruction + " is not legal move specification");
                })
                .toArray(MoveDirection[]::new);
    }
}
