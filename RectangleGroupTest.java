package polygon;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RectangleGroupTest {

    @Test
    void matrixGrid() {

            Rectangle<Integer> a = Rectangle.of(0,2,3,1);
            Rectangle<Integer> b = Rectangle.of(1,3,2,0);

            Set<Rectangle<Integer>> set = new LinkedHashSet<>();
            set.add(a);
            set.add(b);
            RectangleGroup<Integer> group = RectangleGroup.from(set);


       assertEquals("matrixGrid = {(0, 1)=2, (0, 2)=2, " +
               "(0, 3)=2, (1, 0)=2, (1, 1)=4, (1, 2)=4, (1, 3)=2, " +
               "(2, 0)=2, (2, 1)=4, (2, 2)=4, (2, 3)=2, " +
               "(3, 0)=2, (3, 1)=2, (3, 2)=2}", group.toString());



       //  Test working with other types than just integers
       Set<Rectangle<String>> set2 = new LinkedHashSet<>();
       set2.add(Rectangle.of("bell", "fun", "fun", "bell"));
       set2.add(Rectangle.of("a", "b", "e", "c"));

       RectangleGroup<String> rectangleGroup = RectangleGroup.from(set2);
        assertEquals("matrixGrid = {(0, 1)=2, (0, 2)=2, (1, 1)=2, " +
                        "(1, 2)=2, (2, 0)=2, (2, 1)=2, (2, 2)=2, (2, 3)=2, " +
                        "(3, 0)=2, (3, 1)=2, (3, 2)=2, (3, 3)=2}",
                rectangleGroup.toString());


    }
    @Test
    void isConnected(){
        Set<Rectangle<String>> rectanglesSet1 = new LinkedHashSet<>();
        Set<Rectangle<String>> rectangleSet2 = new LinkedHashSet<>();

        rectanglesSet1.add(Rectangle.of("bar","foo","foo","beta"));
        rectangleSet2.add(Rectangle.of("bar","foo","foo","beta"));
        rectangleSet2.add(Rectangle.of("linarez","zeth","zeth","linarez"));

        RectangleGroup<String> rectangleGroup1 =  RectangleGroup.from(rectanglesSet1);
        RectangleGroup<String> rectangleGroup2 = RectangleGroup.from(rectangleSet2);

        assertTrue(rectangleGroup1.isConnected());

        //test 1 for connection is false
        assertFalse(rectangleGroup2.isConnected());

        //add a third rectangle to the set for testing the overlap in group 2.
        rectangleSet2.add(Rectangle.of("kc","mob","mob","kc"));

        RectangleGroup<String> rectangleGroup3 = RectangleGroup.from(rectangleSet2);

        //test two for the overlapping of rectangles
        assertTrue(rectangleGroup3.isOverlapping());


        //test 3 for the borders touching.
        Set<Rectangle<String>> rectangleSet4 = new LinkedHashSet<>();
        rectangleSet4.add(Rectangle.of("bar","foo","foo","beta"));
        rectangleSet4.add(Rectangle.of("git","prep","grit","beta"));

        RectangleGroup<String> rectangleGroup4 =  RectangleGroup.from(rectangleSet4);

        assertTrue(rectangleGroup4.isConnected());
    }





}