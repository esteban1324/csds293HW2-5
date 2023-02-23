package polygon;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

//record for the index pair that implements comparable.
public record IndexPair(Integer xIndex, Integer yIndex) implements Comparable<IndexPair> {

    //increment method
    public IndexPair increment(Direction direction) {
        RectangleException.verifyNonNull(direction);

        Map<Direction, IndexPair> lookup = new EnumMap<>(Direction.class);
        lookup.put(Direction.RIGHT, new IndexPair(xIndex() + 1, yIndex()));
        lookup.put(Direction.LEFT, new IndexPair(xIndex() - 1, yIndex()));
        lookup.put(Direction.TOP, new IndexPair(xIndex(), yIndex() + 1));
        lookup.put(Direction.BOTTOM, new IndexPair(xIndex(), yIndex() - 1));

        return lookup.get(direction);
    }

    //helper to check if direction is right
    private boolean directionIsRight(Direction direction) {
        return direction == Direction.RIGHT;
    }

    //helper to check if direction is left
    private boolean directionIsTop(Direction direction) {
        return direction == Direction.TOP;
    }

    @Override
    public String toString() {
        return "(" + xIndex() + ", " + yIndex() + ")";
    }

    //compare too method that will determine which IndexPair is larger.
    @Override
    public int compareTo(IndexPair o) {
        return 2 * Integer.compare(xIndex(), o.xIndex()) + Integer.compare(yIndex(), o.yIndex());
    }
}

