package agh.ics.oop;

public record Vector2d(int x, int y) {

    @Override
    public String toString() {
        return "(%d, %d)".formatted(this.x, this.y);
    }

    public boolean precedes(Vector2d other) {
        return other.x <= this.x && other.y <= this.y;
    }

    public boolean follows(Vector2d other) {
        return other.x >= this.x && other.y >= this.y;
    }

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d opposite() {
        return new Vector2d(-1 * this.x, -1 * this.y);
    }

    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1, 2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2, 1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        System.out.println(position1.opposite());

        MapDirection west = MapDirection.WEST;
        System.out.println(west.next());
        System.out.println(west.previous());
        System.out.println(west.toUnitVector());
    }
}



