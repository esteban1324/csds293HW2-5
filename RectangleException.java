package polygon;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public final class RectangleException extends Exception {

    /**
     * nested enum of Errors
     */
    public enum Error {
        NULLPOINTERS,
        INVALIDBOUNDS
    }

    /* instance fields of types like Error(Enum), Set, Object, and long */
    private final Error error;

    private final Set<Integer> indexes;

    private final Object lesserBound;

    private final Object greaterBound;

    private final long serialVersionUID = 293L;

    /*constructors for the Rectangle Exception, used for certain cases */
    RectangleException(Error error) {
        this.error = error;
        this.indexes = null;
        this.lesserBound = null;
        this.greaterBound = null;
    }


    RectangleException(Set<Integer> indexes) {
        this.error = Error.NULLPOINTERS;
        this.indexes = indexes;
        this.lesserBound = null;
        this.greaterBound = null;
    }


    RectangleException(Error error, Object lesserBound, Object greaterBound) {
        this.error = error;
        this.indexes = null;
        this.lesserBound = lesserBound;
        this.greaterBound = greaterBound;
    }

    /* getter methods for the fields */
    public Error error() {
        return error;
    }

    public Set<Integer> indexes() {
        return indexes;
    }

    public Object lesserBound() {
        return lesserBound;
    }

    public Object greaterBound() {
        return greaterBound;
    }

    /**
     * This method checks to verify the bounds and
     * throws in exception if there are null bounds or invalid bounds
     *
     * @param lesserbound  the bound of type S
     * @param greaterbound the bound of type S
     * @param <S>          the generic used for the bounds
     * @throws IllegalArgumentException
     */
    public static <S extends Comparable<S>> void verifyBounds(S lesserbound, S greaterbound) throws IllegalArgumentException {
        boolean invalidBounds = (lesserbound.compareTo(greaterbound) >= 0);
        boolean nullBounds = (lesserbound == null || greaterbound == null);

        if (nullBounds)
            throw new IllegalArgumentException("lesserBound or greatBound is null", new RectangleException(Error.NULLPOINTERS));

        if (invalidBounds)
            throw new IllegalArgumentException("invalid bounds for the rectangle", new RectangleException(Error.INVALIDBOUNDS, lesserbound, greaterbound));
    }

    /**
     * this method will verfy if there are any null values.
     *
     * @param array object.
     * @throws IllegalArgumentException
     */
    public static void verifyNonNull(Object... array) throws IllegalArgumentException {
        boolean arrayisNull = (array == null);
        if (arrayisNull)
            throw new IllegalArgumentException("the input array is null", new RectangleException(Error.NULLPOINTERS));

        Set<Integer> indices = IntStream.range(0, array.length).filter(i -> array[i] == null).boxed().
                collect(Collectors.toSet());

        if (indices.size() > 0)
            throw new IllegalArgumentException("some indices are null", new RectangleException(indices));
    }


}
