package polygon;

import java.util.*;

 enum Direction {
     /*The Directions of the grid, the rectangle components */
    LEFT (true,false),
    RIGHT (true,true),
     BOTTOM (false, false),
     TOP (false, true);


    /* denoting the directions, and if it increases or moves laterally    */
    private final boolean increment;
    private final boolean horizontal;

    /* fields for the vertical/horizontal bounds, taken as sets */
     static final Set<Direction> VERTICAL_BOUNDS = EnumSet.of(Direction.BOTTOM, Direction.TOP);

     static final Set<Direction> HORIZONTAL_BOUNDS = EnumSet.of(Direction.LEFT, Direction.RIGHT);

    /*constructor for this Direction Enum */
    Direction(boolean horizontal, boolean increment) {
        this.horizontal = horizontal;
        this.increment = increment;
    }

    /*getter methods for the horizontal and increment fields */
    boolean horizontal(){ return horizontal; }

    boolean increment(){ return increment; }









}

