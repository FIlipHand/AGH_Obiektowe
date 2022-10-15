import agh.ics.oop.MapDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
    private final MapDirection south = MapDirection.SOUTH;
    private final MapDirection north = MapDirection.NORTH;
    private final MapDirection west = MapDirection.WEST;
    private final MapDirection east = MapDirection.EAST;

    @Test
    public void test_next() {
        assertEquals(south.next(), MapDirection.WEST);
        assertEquals(north.next(), MapDirection.EAST);
        assertEquals(west.next(), MapDirection.NORTH);
        assertEquals(east.next(), MapDirection.SOUTH);
    }

    @Test
    public void test_previous() {
        assertEquals(south.previous(), MapDirection.EAST);
        assertEquals(north.previous(), MapDirection.WEST);
        assertEquals(west.previous(), MapDirection.SOUTH);
        assertEquals(east.previous(), MapDirection.NORTH);
    }
}
