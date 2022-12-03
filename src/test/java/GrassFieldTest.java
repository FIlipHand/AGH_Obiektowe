import agh.ics.oop.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    // Nie do końca wiem, jak tutaj te testy powinny wyglądać, skoro położenia trawy są niedeterministyczne,
    // a nie mamy setterów, aby ręcznie ustalać jej pozycje,
    // ale coś tam testy sprawdzają
    @Test
    public void canMoveTo_test() {
        IWorldMap map = new GrassField(10);

        assertFalse(map.canMoveTo(new Vector2d(-2, -4)));
        assertTrue(map.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void objectAt_test() {
        IWorldMap map = new GrassField(10);
        map.place(new Animal(map, new Vector2d(2, 2)));
        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(-1, -10))));
        assertNotNull(map.objectAt(new Vector2d(2, 2)));
        assertEquals(map.objectAt(new Vector2d(2,2)).getClass(), Animal.class);
    }

    @Test
    public void getMapBoundaries_test() {
        int n = 10;
        GrassField map = new GrassField(n);
        Pair<Vector2d, Vector2d> pair = map.getMapBoundaries();
        assertTrue(new Vector2d((int) Math.sqrt(n * 10), (int) Math.sqrt(n * 10)).precedes(pair.getSecond()));
    }
}
