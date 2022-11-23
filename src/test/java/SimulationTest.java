import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {

    @Test
    public void testSimulation() {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);

        // Check if arguments are parsed properly
        assertArrayEquals(directions, new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD});
        // it might have been an overkill

        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};

        IEngine engine = new SimulationEngine(directions, map, positions);

        // check if animals are created on proper tiles
        assertEquals(map.objectAt(new Vector2d(2, 2)).getClass(), Animal.class);
        assertEquals(map.objectAt(new Vector2d(3, 4)).getClass(), Animal.class);

        engine.run();

        // check if animals moved and map is empty in this places
        assertNull(map.objectAt(new Vector2d(2, 2)));
        assertNull(map.objectAt(new Vector2d(3, 4)));

        // check if animals are in places where they are supposed to be
        assertEquals(map.objectAt(new Vector2d(3, 5)).getClass(), Animal.class);
        assertEquals(map.objectAt(new Vector2d(2, 0)).getClass(), Animal.class);
    }
}
