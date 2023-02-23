
package polygon;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AxisMapTest {

    @Test
    void from() {

        ArrayList<Integer> list =  Stream.of(1,2,3,4,5).collect(Collectors.toCollection(ArrayList:: new));

        AxisMap<Integer> it = AxisMap.from(list);

        assertEquals(0,it.flatIndexOf(1));

    }



    @Test
    void flatIndexOf() {

        //test front index
        ArrayList<Integer> list =  Stream.of(1,2,3,4,5).collect(Collectors.toCollection(ArrayList:: new));
        AxisMap<Integer> it = AxisMap.from(list);
        assertEquals(0,it.flatIndexOf(1));
        //test middle index
        assertEquals(2,it.flatIndexOf(3));
        //test end index
        assertEquals(4, it.flatIndexOf(5));
        //test null index
        assertEquals(null, it.flatIndexOf(12));


    }

    @Test
        void testSize(){
        ArrayList<Integer> list =  Stream.of(1,2,3,4,5).collect(Collectors.toCollection(ArrayList:: new));
        AxisMap<Integer> it = AxisMap.from(list);

        assertEquals(5,it.size());
    }

    @Test
        void testindex(){
        //testing the optional version of index of.
        //test front index
        ArrayList<Integer> list =  Stream.of(1,2,3,4,5).collect(Collectors.toCollection(ArrayList:: new));
        AxisMap<Integer> it = AxisMap.from(list);
        assertEquals(Optional.of(0), it.indexOf(1));
        //test middle index
        assertEquals(Optional.of(2),it.indexOf(3));
        //test end index
        assertEquals(Optional.of(4), it.indexOf(5));
        //test null index, should return optional empty
        assertEquals(Optional.empty(), it.indexOf(12));


    }




}