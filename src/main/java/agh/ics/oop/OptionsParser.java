package agh.ics.oop;

import java.util.Objects;
import java.util.stream.Stream;

public class OptionsParser {

    public MoveDirection[] parse(String[] args) {
        // Use streams
        return Stream.of(args)
                .map(instruction -> switch (instruction) {
                    case "f", "forward" -> (MoveDirection.FORWARD);
                    case "b", "backward" -> (MoveDirection.BACKWARD);
                    case "r", "right" -> (MoveDirection.RIGHT);
                    case "l", "left" -> (MoveDirection.LEFT);
                    default -> null;
                })
                .filter(Objects::nonNull).toArray(MoveDirection[]::new);

//        ArrayList<MoveDirection> array = new ArrayList<>();
//        for (String arg : args) {
//            switch (arg) {
//                case "f", "forward" -> array.add(MoveDirection.FORWARD);
//                case "b", "backward" -> array.add(MoveDirection.BACKWARD);
//                case "r", "right" -> array.add(MoveDirection.RIGHT);
//                case "l", "left" -> array.add(MoveDirection.LEFT);
//                default -> {
//
//                }
//            }
//        }
//
//        return array.toArray(new MoveDirection[0]);
    }
}
