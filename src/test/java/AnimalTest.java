import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
    @Test
    public void test_orientation() {
        Animal szczur = new Animal();
        // test if animal is created with proper orientation
        assertEquals(szczur.getOrientation(), MapDirection.NORTH);

        // test if move left or right changes orientation
        szczur.move(MoveDirection.RIGHT);
        assertEquals(szczur.getOrientation(), MapDirection.EAST);
        szczur.move(MoveDirection.RIGHT);
        assertEquals(szczur.getOrientation(), MapDirection.SOUTH);
        szczur.move(MoveDirection.LEFT);
        assertEquals(szczur.getOrientation(), MapDirection.EAST);
        szczur.move(MoveDirection.LEFT);
        assertEquals(szczur.getOrientation(), MapDirection.NORTH);

    }

    @Test
    public void test_position() {
        Animal krowa = new Animal();
        // test if animal is created with proper position
        assertEquals(krowa.getPosition(), new Vector2d(2, 2));

        // test if animal can move properly
        krowa.move(MoveDirection.FORWARD);
        assertEquals(krowa.getPosition(), new Vector2d(2, 3));
        krowa.move(MoveDirection.BACKWARD);
        krowa.move(MoveDirection.BACKWARD);
        assertEquals(krowa.getPosition(), new Vector2d(2, 1));

        // test if animal can turn and move in that direction
        krowa.move(MoveDirection.RIGHT);
        krowa.move(MoveDirection.FORWARD);
        assertEquals(krowa.getPosition(), new Vector2d(3, 1));
        krowa.move(MoveDirection.LEFT);
        krowa.move(MoveDirection.BACKWARD);
        assertEquals(krowa.getPosition(), new Vector2d(3, 0));

        // test if animal can move out of boundary
        krowa.move(MoveDirection.BACKWARD);
        assertEquals(krowa.getPosition(), new Vector2d(3, 0));

    }

    @Test
    public void check_command_line_args() {
        Animal krewetka_modliszkowa = new Animal();
        String[] args = {"f", "forward", "r", "l", "left", "b", "O_O"};
        OptionsParser parser = new OptionsParser();
        MoveDirection[] array = parser.parse(args);
        for (MoveDirection direction : array) {
            krewetka_modliszkowa.move(direction);
        }
        assertEquals(krewetka_modliszkowa.getPosition(), new Vector2d(3, 4));
    }
}
