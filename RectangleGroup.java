package polygon;

import java.util.*;

//rectangle group class
public final class RectangleGroup<T extends Comparable<T>> {

    //fields
    private final Set<Rectangle<T>> rectangles;

    private final PlaneMap<T> planeMap;

    private final NavigableMap<IndexPair, Long> matrixGrid;

    private final boolean isOverlapping;

    private final boolean isConnected;

    //constructor
    private RectangleGroup(Set<Rectangle<T>> rectangles,
                           PlaneMap<T> planeMap,
                           NavigableMap<IndexPair, Long> matrixGrid,
                           boolean isOverlapping,
                           boolean isConnected) {
        assert (rectangles != null) : "rectangle is null";

        this.rectangles = rectangles;
        this.planeMap = planeMap;
        this.matrixGrid = matrixGrid;
        this.isOverlapping = isOverlapping;
        this.isConnected = isConnected;

    }

    //getter for the rectangle set
    public Set<Rectangle<T>> rectangleSet() {
        return Collections.unmodifiableSet(rectangles);
    }

    //getter for planeMap
    public PlaneMap<T> planeMap() {
        return planeMap;
    }

    //getter for the matrixGrid
    public NavigableMap<IndexPair, Long> matrixGrid() {
        return Collections.unmodifiableNavigableMap(matrixGrid);
    }

    /**
     * This method will create the matrix grid. We iterate through each rectangle and grab it's index
     * based on the borders and put it into our matrixGrid, we then will update and see if theres any
     * overlap which the long gets updated.
     */
    private static <T extends Comparable<T>> NavigableMap<IndexPair, Long> makeMatrixGrid(Set<Rectangle<T>> rectangle, PlaneMap<T> planeMap) {
        assert (rectangle != null) : "rectangle is not null";
        //make the matrixGrid which is a treeMap.
        NavigableMap<IndexPair, Long> matrixGrid = new TreeMap<>();
        //set that holds Rectangle Border Indexes of type integer.
        Set<Rectangle<Integer>> indexRects = new HashSet<>();
        //here we convert the elements of the rectangle into its indexes of rectangle,
        // add this to our set of rect Integers.
        rectangle.forEach(rectElement -> indexRects.add(planeMap.getIndexRectangle(rectElement)));
        //then for each Index Rectangle we process each integer rectangle,
        // fill the matrix and update the matrix count.
        indexRects.forEach(pair -> fillMatrix(Grid.from(pair), matrixGrid));

        return matrixGrid;
    }

    private static void fillMatrix(Grid rectGrid, NavigableMap<IndexPair, Long> matrixGrid) {
        rectGrid.forEach(indexPair -> updateMatrixCount(indexPair, matrixGrid));
    }

    private static void updateMatrixCount(IndexPair pair, NavigableMap<IndexPair, Long> matrixGrid) {
        Long countOverlap = matrixGrid.getOrDefault(pair, 0L);
        matrixGrid.put(pair, countOverlap + 1);
    }



    private static Set<IndexPair> component(IndexPair start, Set<IndexPair> current, NavigableMap<IndexPair, Long> matrixGrid) {

        for (Direction direction : Direction.values()) {
            //increment in any given direction.
            IndexPair next = start.increment(direction);
            if (checkConnection(next,current,matrixGrid)) component(next, current, matrixGrid);
        }

        return current;
    }

    //check to see if theres any connection
    private static boolean checkConnection(IndexPair next, Set<IndexPair> current, NavigableMap<IndexPair, Long> matrixGrid) {
        if (matrixGrid.getOrDefault(next, 0L) > 0 && !(current.contains(next))) {
            current.add(next);
            return true;
        }
        else
            return false;
    }


    //check if theres overlap, if the number is greater than one, then there is overlap.
    private static boolean checkOverlap(NavigableMap<IndexPair, Long> matrixGrid) {
        assert (matrixGrid != null) : "this grid is null";
        return matrixGrid.values().stream().anyMatch(count -> count > 1);
    }

    //returns a overlap within grid.
    public boolean isOverlapping() {
        return isOverlapping;
    }

    public boolean isConnected() {
        return isConnected;
    }

    //rectangleGroup method that returns a new rectangle.
    static <T extends Comparable<T>> RectangleGroup<T> from(Set<Rectangle<T>> rectangles) {
        PlaneMap<T> planeMap = PlaneMap.from(rectangles);
        NavigableMap<IndexPair, Long> matrixGrid = makeMatrixGrid(rectangles, planeMap);
        //start from a first key for the Index Pair.
        IndexPair newPair = matrixGrid.firstEntry().getKey();

        boolean isoverlapping;
        boolean isConnected;

        Set<IndexPair> s1 = new TreeSet<>();

        Set<IndexPair> s2 = new TreeSet<>();

        for (IndexPair indexes : matrixGrid.keySet()) if (checkOverlap(matrixGrid)) s1.add(indexes);

        //call to component method
        component(newPair, s2, matrixGrid);

        //comparing if our s1 set which adds all the keys from the grid and gather s2 from component.
        isConnected = s1.equals(s2);
        isoverlapping = checkOverlap(matrixGrid);

        return new RectangleGroup<>(rectangles, planeMap, matrixGrid, isoverlapping, isConnected);
    }


    @Override
    public String toString() {
        return "matrixGrid = " + matrixGrid();
    }
}
