package polygon;

import java.util.*;

public class AxisMap<S extends Comparable<S> > {

//field
private final Map<S,Integer> index;

//constructor for the axis map
AxisMap(Map<S, Integer> indices){
   assert indices != null;
    index = indices;
}

//getter for the index field.
Map<S,Integer> index(){
    return index;
}

    /**
     * @return the keys associated with the AxisMap
     */
    Set<S> getKeys() {
        return index().keySet();
    }


//method to generate axis map
 static <S extends Comparable<S> > AxisMap<S> from(Collection<S> coordinates) {
    assert(coordinates != null): "coordinates are null";
    return inputCoordinates(coordinates);
}

//helper method that extracts the collection of coordinates, inputs them into the AxisMap.
private static <S extends Comparable<S>> AxisMap<S> inputCoordinates(Collection<S> coordinates) {
    assert(coordinates != null): "coordinates are null";

    //must sort the cordinates
    LinkedList<S> list = new LinkedList<>(coordinates);
    Collections.sort(list);

    Map<S, Integer> coordinateIndexMap = new LinkedHashMap<>();
    int i = 0;

        for (S objs: list) {
            coordinateIndexMap.put(objs, i++);
        }
        return new AxisMap<>(coordinateIndexMap);
    }

    //returns index of respective value or null if value isn’t there
Integer flatIndexOf(S value){
    assert(value != null): "value is null";
    return index().getOrDefault(value, null);
}

//size of the index field
public int size(){
    return index().size();
}

//public method that returns the respective value.
public Optional<Integer> indexOf(S value){
    RectangleException.verifyNonNull(value);
    return Optional.ofNullable(flatIndexOf(value));
}

@Override
public String toString(){
    return index().toString();
}


















}
