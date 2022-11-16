package agh.ics.oop;

import java.util.ArrayList;

public class OptionsParser {

    public MoveDirection[] parse(String[] args) {
        ArrayList<MoveDirection> array = new ArrayList<>();
        for (String arg : args) {
            switch (arg) {
                case "f", "forward" -> array.add(MoveDirection.FORWARD);
                case "b", "backward" -> array.add(MoveDirection.BACKWARD);
                case "r", "right" -> array.add(MoveDirection.RIGHT);
                case "l", "left" -> array.add(MoveDirection.LEFT);
                default -> {

                }
            }
        }

        return array.toArray(new MoveDirection[0]);
    }
}
