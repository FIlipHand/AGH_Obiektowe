import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    @Test
    public void canMoveTo_test() {
        IWorldMap map = new RectangularMap(4, 4);

        assertFalse(map.canMoveTo(new Vector2d(5, 1)));
        assertTrue(map.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void objectAt_test() {
        IWorldMap map = new RectangularMap(5, 5);
        map.place(new Animal(map, new Vector2d(2, 2)));
        assertNotNull(map.objectAt(new Vector2d(2, 2)));
        assertEquals(map.objectAt(new Vector2d(2,2)).getClass(), Animal.class);
        assertNull(map.objectAt(new Vector2d(4, 2)));

    }

    @Test
    public void getMapBoundaries_test() {
        RectangularMap map = new RectangularMap(5, 5);
        Pair<Vector2d, Vector2d> pair = map.getMapBoundaries();
        assertEquals(pair.getFirst(), Vector2d.VEC_0_0);
        assertEquals(pair.getSecond(), new Vector2d(5, 5));
    }
}
