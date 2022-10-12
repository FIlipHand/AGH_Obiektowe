package agh.ics.oop;

public class World {
    public static void run(Direction[] directions) {
        System.out.println("Start");
        String message;
        for (Direction direction : directions) {
            switch (direction) {
                case FORWARDS:
                    message = "do przodu";
                    break;
                case BACKWARDS:
                    message = "do tyłu";
                    break;
                case RIGHT:
                    message = "w prawo";
                    break;
                case LEFT:
                    message = "w lewo";
                    break;
                default:
                    continue;
            }
            System.out.println("Zwierzak idzie " + message);
        }
        System.out.println("Stop");
    }

    public static Direction[] changeArguments(String[] args) {
        Direction[] newDirs = new Direction[args.length];
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "l" -> newDirs[i] = Direction.LEFT;
                case "r" -> newDirs[i] = Direction.RIGHT;
                case "b" -> newDirs[i] = Direction.BACKWARDS;
                case "f" -> newDirs[i] = Direction.FORWARDS;
            }
        }
        return newDirs;
    }

    public static void main(String[] args) {
        System.out.println("system wystartował");
        Direction[] directions = changeArguments(args);
        run(directions);
        System.out.println("system zakończył działanie");
    }
}
