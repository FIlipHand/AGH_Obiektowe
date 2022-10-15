import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Vector2dTest {
    private final Vector2d vector2d = new Vector2d(2, 3);

    @Test
    public void equals_test() {
        assertEquals(vector2d, new Vector2d(2, 3));
    }

    @Test
    public void toString_test() {
        assertEquals(vector2d.toString(), "(2, 3)");
    }

    @Test
    public void precedes_test() {
        assertTrue(vector2d.precedes(new Vector2d(0, -4)));
    }

    @Test
    public void follows_test() {
        assertTrue(vector2d.follows(new Vector2d(4, 4)));
    }

    @Test
    public void upperRight_test() {
        assertEquals(vector2d.upperRight(new Vector2d(1, 7)), new Vector2d(2, 7));
    }

    @Test
    public void lowerLeft_test() {
        assertEquals(vector2d.lowerLeft(new Vector2d(7, 1)), new Vector2d(2, 1));
    }

    @Test
    public void add_test() {
        assertEquals(vector2d.add(new Vector2d(-1, 4)), new Vector2d(1, 7));
    }

    @Test
    public void subtract_test() {
        assertEquals(vector2d.subtract(new Vector2d(-1, 6)), new Vector2d(3, -3));
    }

    @Test
    public void opposite_test() {
        assertEquals(vector2d.opposite(), new Vector2d(-2, -3));
    }
}
