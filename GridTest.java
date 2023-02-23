package polygon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void from() {

         IndexPair r = new IndexPair(2,3);
        IndexPair z = new IndexPair(2,7);

        IndexPair x = new IndexPair(4,7);
        IndexPair x2 = new IndexPair(1,12);

        assertEquals(-1, r.compareTo(z));

        assertEquals(1, x.compareTo(x2));



        //test direction move
        assertEquals(4, r.increment(Direction.TOP).yIndex());
        assertEquals(1, r.increment(Direction.LEFT).xIndex());

        Rectangle<Integer> rectangle = Rectangle.of(1,5,5,2);

        Grid grid1 = Grid.from(rectangle);

       assertEquals("[(1, 2), (1, 3), " +
                       "(1, 4), (2, 2), (2, 3), (2, 4), " +
                       "(3, 2), (3, 3), (3, 4), (4, 2), (4, 3), (4, 4)]",

            grid1.toString());


    }
}