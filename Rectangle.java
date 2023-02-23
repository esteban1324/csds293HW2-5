package polygon;

import java.util.*;


public final class Rectangle<T extends Comparable<T>> {

    /*borders which are the components for the rectangle */
    private final EnumMap<Direction, T> borders;

    //static fields for horizontal and vertical bounds of the rectangle.
    static final Set<Direction> HORIZONTAL_BOUNDS = Direction.HORIZONTAL_BOUNDS;
    static final Set<Direction> VERTICAL_BOUNDS = Direction.VERTICAL_BOUNDS;

    /* constructor that initializes the borders*/
    private Rectangle(T left, T right, T top, T bottom) {
        borders = new EnumMap<>(Direction.class);
        this.setBorders(left,right,top,bottom);
    }

    private void setBorders(T left, T right, T top, T bottom){
        borders.put(Direction.LEFT, left);
        borders.put(Direction.RIGHT, right);
        borders.put(Direction.TOP, top);
        borders.put(Direction.BOTTOM, bottom);
    }

    /*getters for the left, right, top, bottom */
    public T left() {
        return borders.get(Direction.LEFT);
    }

    public T right() {
        return borders.get(Direction.RIGHT);
    }

    public T top() {
        return borders.get(Direction.TOP);
    }

    public T bottom() {
        return borders.get(Direction.BOTTOM);
    }


    /*gets the border direction from the enumMap */
    T getBorder(Direction direction) {
        return borders.get(direction);
    }

    /**
     * This method transfers over the directions into an enum map
     *
     * @param directions collection of directions
     * @return rectMap which puts the directions from the Collections object
     */
    EnumMap<Direction, T> getBorders(Collection<Direction> directions) {
        assert (directions != null) : "directions have a null object";
        EnumMap<Direction, T> rectMap = new EnumMap<>(Direction.class);
        for (Direction d : directions) rectMap.put(d, getBorder(d));
        return rectMap;
    }

    /**
     * This method will construct a rectangle from the given parameters,
     * it will check for exceptions in case of bad user input
     *
     * @param left   border
     * @param right  border
     * @param top    border
     * @param bottom border
     * @param <T>    generic type of the rectangle
     * @return a new rectangle object.
     */
    public static <T extends Comparable<T>> Rectangle<T> of(T left, T right, T top, T bottom) {
        //error checks
        RectangleException.verifyNonNull(left, right, top, bottom);
        RectangleException.verifyBounds(left, right);
        RectangleException.verifyBounds(bottom, top);

        return new Rectangle<>(left, right, top, bottom);
    }

    /**
     * This will make a copy of a rectangle object we previously created
     *
     * @param rectangle the orginal rectangle
     * @param <T>       generic type of the rectangle
     * @return a new rectangle which copies it.
     */
    public static <T extends Comparable<T>> Rectangle<T> copyOf(Rectangle<T> rectangle) {
        //error check
        RectangleException.verifyNonNull(rectangle.left(), rectangle.right(), rectangle.top(), rectangle.right());
        return new Rectangle<>(rectangle.left(), rectangle.right(), rectangle.top(), rectangle.bottom());
    }

    @Override
    public String toString(){
        return "leftBorder: " + left() +
                " rightBorder: " + right() +
                " bottomBorder: " + bottom() +
                " topBorder: " + top();
    }


}
