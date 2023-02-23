package polygon;

import java.util.*;


public final class PlaneMap<T extends Comparable<T>> {


    //fields for PlaneMap
    private final AxisMap<T> x;

    private final AxisMap<T> y;

    //private constructor for planeMap
    private PlaneMap(AxisMap<T> x, AxisMap<T> y) {
        assert (x != null && y != null) : "coordinates are null";
        this.x = x;
        this.y = y;
    }

    public AxisMap<T> xCoordinate() {
        return x;
    }

    public AxisMap<T> yCoordinate() {
        return y;
    }

    Set<T> horizontalKeys() {
        return xCoordinate().getKeys();
    }

    Set<T> verticalKeys() {
        return yCoordinate().getKeys();
    }


    public int xSize() {
        return xCoordinate().size();
    }

    public int ySize() {
        return yCoordinate().size();
    }

    //creates a new plane map
    public static <S extends Comparable<S>> PlaneMap<S> of(Collection<S> x, Collection<S> y) {
        return new PlaneMap<>(AxisMap.from(x), AxisMap.from(y));
    }

    //return x index
    public Optional<Integer> xIndexOf(T value) {
        RectangleException.verifyNonNull(value);
        return xCoordinate().indexOf(value);
    }

    //return y index
    public Optional<Integer> yIndexOf(T value) {
        RectangleException.verifyNonNull(value);
        return yCoordinate().indexOf(value);
    }

    // return index of integer
    Integer indexOf(T value, boolean horizontal) {
        assert value != null : "value is null";
        return horizontal ? xCoordinate().flatIndexOf(value) : yCoordinate().flatIndexOf(value);
    }

    /**
     * This is a helper method that basically grabs the indexes of the rectangle borders.
     *
     * @param anyRectangle this can be any rectangle that is a generic and
     * @return
     */
    Rectangle<Integer> getIndexRectangle(Rectangle<T> anyRectangle) {

        assert (anyRectangle != null) : "Input is a null rectangle";

        Integer leftIndex = xIndexOf(anyRectangle.getBorder(Direction.LEFT)).orElseThrow();
        Integer rightIndex = xIndexOf(anyRectangle.getBorder(Direction.RIGHT)).orElseThrow();
        Integer topIndex = yIndexOf(anyRectangle.getBorder(Direction.TOP)).orElseThrow();
        Integer bottomIndex = yIndexOf(anyRectangle.getBorder(Direction.BOTTOM)).orElseThrow();
        return Rectangle.of(leftIndex, rightIndex, topIndex, bottomIndex);
    }

    //makes a plane map from a set of rectangle bounds
    public static <S extends Comparable<S>> PlaneMap<S> from(Set<Rectangle<S>> rectangles) {

        Collection<S> xBorders = new LinkedList<>();
        Collection<S> yBorders = new LinkedList<>();

        for (Rectangle<S> rectangle : rectangles) {
            xBorders.addAll(rectangle.getBorders(Rectangle.HORIZONTAL_BOUNDS).values());
            yBorders.addAll(rectangle.getBorders(Rectangle.VERTICAL_BOUNDS).values());
        }

        return of(xBorders, yBorders);
    }


}
