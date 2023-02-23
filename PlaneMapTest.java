package polygon;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PlaneMapTest {



    @Test
    void xSize() {

        LinkedList<Integer> list =  Stream.of(1,2,3,4,5).collect(Collectors.toCollection(LinkedList:: new));
        LinkedList<Integer> list2 = Stream.of(6,7,8,9,10).collect(Collectors.toCollection(LinkedList:: new));

        PlaneMap.of(list,list2);

        assertEquals(5,PlaneMap.of(list,list2).xSize());



    }

    @Test
    void ySize() {
        LinkedList<Integer> list =  Stream.of(1,2,3,4,5).collect(Collectors.toCollection(LinkedList:: new));
        LinkedList<Integer> list2 = Stream.of(6,7,8,9,10).collect(Collectors.toCollection(LinkedList:: new));

        PlaneMap.of(list,list2);

        assertEquals(5,PlaneMap.of(list,list2).ySize());
    }

    @Test
    void xyIndexOf() {
        ArrayList<Integer> list =  Stream.of(1,2,3,4,5).collect(Collectors.toCollection(ArrayList:: new));
        ArrayList<Integer> list2 =  Stream.of(2,4,6,8,10).collect(Collectors.toCollection(ArrayList:: new));

        PlaneMap<Integer> it = PlaneMap.of(list,list2);

        assertEquals(Optional.of(0), it.xIndexOf(1));
        //test middle index
        assertEquals(Optional.of(2),it.xIndexOf(3));
        //test end index
        assertEquals(Optional.of(4), it.xIndexOf(5));
        //test null index, should return optional empty

        assertEquals(Optional.empty(), it.xIndexOf(12));

        assertEquals(Optional.of(0), it.yIndexOf(2));
        //test middle index
        assertEquals(Optional.of(2),it.yIndexOf(6));
        //test end index
        assertEquals(Optional.of(4), it.yIndexOf(10));

    }


    @Test
    void indexOf() {

        ArrayList<Integer> list =  Stream.of(1,2,3,4,5).collect(Collectors.toCollection(ArrayList:: new));
        ArrayList<Integer> list2 =  Stream.of(2,4,6,8,10).collect(Collectors.toCollection(ArrayList:: new));

        PlaneMap<Integer> it = PlaneMap.of(list,list2);
        assertEquals(0,it.indexOf(1,true));

        assertEquals(0, it.indexOf(2,false));

        assertEquals(2,it.indexOf(3,true));

        assertEquals(2,it.indexOf(6,false));

        assertEquals(4,it.indexOf(5,true));

        assertEquals(4,it.indexOf(10,false));

    }

    @Test
    void from() {
        Rectangle<Integer> rect1 = Rectangle.of(1, 4, 7, 3);
        Rectangle<Integer> rect2 = Rectangle.of(0, 2, 4, 2);
        Rectangle<Integer> rect3 = Rectangle.of(2, 6, 6, 1);


        HashSet<Rectangle<Integer>> set = new HashSet<>();
        set.add(rect1);
        set.add(rect2);
        set.add(rect3);

        assertEquals("{0=0, 1=1, 2=3, 4=4, 6=5}", PlaneMap.from(set).xCoordinate().toString());
        assertEquals("Optional[3]", PlaneMap.from(set).xIndexOf(2).toString());








    }
}