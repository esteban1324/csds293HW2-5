package polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

//class that makes a grid object of index pairs
public class Grid implements Iterable<IndexPair> {

    //rectangle field
    private final Rectangle<Integer> rectangle;

    //grid that is backed by a list of index pairs
    private final List<IndexPair> grid;

    //private constructor of a grid that takes in rectangle borders
    private Grid(Rectangle<Integer> rectangle) {
        assert (rectangle != null) : "no null objects allowed";
        //initializes the rectangles and grid
        this.rectangle = rectangle;
        grid = new ArrayList<>();
        this.iterator();
    }

    //gettter for rectangle
    private Rectangle<Integer> rectangle() {
        return rectangle;
    }

    List<IndexPair> gridList() {
        return grid;
    }

    //static constructor method that returns a grid from the given rectangle
    public static Grid from(Rectangle<Integer> rectangle) {
        return new Grid(Rectangle.copyOf(rectangle));
    }

    //nested class.
    private class Iterator implements java.util.Iterator<IndexPair> {
        //list of index pairs from the rectangle.
        private final List<IndexPair> gridList;

        //index that keeps track of location in list
        int index;

        //the list that is constructed to iterate through
        public Iterator(Grid gridList) {
            this.gridList = gridList.grid;
            index = 0;

            IntStream.rangeClosed(rectangle().getBorder(Direction.LEFT), rectangle().getBorder(Direction.RIGHT)).
                    forEach(xIndex -> IntStream.rangeClosed(rectangle().getBorder(Direction.BOTTOM),
                                    rectangle().getBorder(Direction.TOP)).
                            forEach(yIndex -> this.gridList.add(new IndexPair(xIndex, yIndex))));
        }

        //has next method, a check as long as index is less than list size
        public boolean hasNext() {
            return index < gridList.size();
        }

        //keeps incrementing upward.
        public IndexPair next() {
            return gridList.get(index++);
        }
    }

    //return the new iterator.
    @Override
    public java.util.Iterator<IndexPair> iterator() {
        return new Iterator(this);
    }

    @Override
    public String toString() {
        return grid.toString();
    }
}


