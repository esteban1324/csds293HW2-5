package polygon;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @org.junit.jupiter.api.Test
    void testRectangleClass() {

        //testing if values of rectangle are right, getters  */
        Rectangle<Integer> rect = Rectangle.of(1, 3, 18, 3);
        assertEquals(rect.left(), Integer.valueOf(1));
        assertEquals(rect.right(), Integer.valueOf(3));
        assertEquals(rect.top(), Integer.valueOf(18));
        assertEquals(rect.bottom(), Integer.valueOf(3));

        //testing different type
        Rectangle<Character> rect2 = Rectangle.of('a', 'c', 'x', 'b');
        assertEquals(rect2.left(), Character.valueOf('a'));
        assertEquals(rect2.right(), Character.valueOf('c'));
        assertEquals(rect2.top(), Character.valueOf('x'));
        assertEquals(rect2.bottom(), Character.valueOf('b'));

        //testing getBorder and getBorder(s) methods
        assertEquals(rect2.getBorder(Direction.LEFT),Character.valueOf('a'));
        assertEquals(rect.getBorder(Direction.LEFT), Integer.valueOf(1));
        assertEquals(rect2.getBorder(Direction.RIGHT),Character.valueOf('c'));
        assertEquals(rect.getBorder(Direction.RIGHT), Integer.valueOf(3));
        assertEquals(rect2.getBorder(Direction.TOP), Character.valueOf('x'));
        assertEquals(rect.getBorder(Direction.TOP), Integer.valueOf(18));
        assertEquals(rect2.getBorder(Direction.BOTTOM), Character.valueOf('b'));
        assertEquals(rect.getBorder(Direction.BOTTOM), Integer.valueOf(3));

        //get borders test testing to see if our rectangle created with it's borders
        //and calling the getBorders that returns an enum map, seeing if the values
        //return the correct value with respect to the key.
        Collection<Direction> collection = new ArrayList<>();
        collection.add(Direction.TOP);
        collection.add(Direction.BOTTOM);
        collection.add(Direction.TOP);
        collection.add(Direction.LEFT);
        collection.add(Direction.RIGHT);

        Rectangle<Integer> rect3 = Rectangle.of(3,9,12,2);
        assertEquals(3, rect3.getBorders(collection).get(Direction.LEFT));
        assertEquals(9, rect3.getBorders(collection).get(Direction.RIGHT));
       assertEquals(12, rect3.getBorders(collection).get(Direction.TOP));
       assertEquals(2, rect3.getBorders(collection).get(Direction.BOTTOM));



        //test of and get copy of methods, (valid rectangles) //invalid rectangles are tested below
        Rectangle.of(3,12,10,5);


        //testing of method, bounds
        assertEquals(3, Rectangle.of(3,12,10,5).left());
        assertEquals(12, Rectangle.of(3,12,10,5).right());
        assertEquals(10, Rectangle.of(3,12,10,5).top());
        assertEquals(5, Rectangle.of(3,12,10,5).bottom());


        assertEquals(3, Rectangle.copyOf(rect3).left());
        assertEquals(9, Rectangle.copyOf(rect3).right());
        assertEquals(12, Rectangle.copyOf(rect3).top());
        assertEquals(2, Rectangle.copyOf(rect3).bottom());




    }

    @org.junit.jupiter.api.Test
    void testExceptionsClass(){
        //testing methods within the exception class

        //testing invalid bounds first, bottom bound is greater than top
        Exception bottomGreaterThanTop = assertThrows(IllegalArgumentException.class,() -> Rectangle.of(3,12,10,20));
        assertTrue(bottomGreaterThanTop.getCause() instanceof RectangleException);
        RectangleException rectExceptionBottomTop = (RectangleException) bottomGreaterThanTop.getCause();

        assertEquals(RectangleException.Error.INVALIDBOUNDS, rectExceptionBottomTop.error());
        assertEquals(10, rectExceptionBottomTop.greaterBound());
        assertEquals(20, rectExceptionBottomTop.lesserBound());




        //Test invalid bounds left, right
        Exception leftThanRight = assertThrows(IllegalArgumentException.class,() -> Rectangle.of(6,3,10,20));
        assertTrue(leftThanRight.getCause() instanceof RectangleException);
        RectangleException rectExceptionLeftRight = (RectangleException) leftThanRight.getCause();
        assertEquals(RectangleException.Error.INVALIDBOUNDS, rectExceptionLeftRight.error());

        //testing for nullBounds
      Exception nullBound = assertThrows(IllegalArgumentException.class,() -> Rectangle.of(7,12,10,null));
        assertEquals("some indices are null", nullBound.getMessage());







    }

}