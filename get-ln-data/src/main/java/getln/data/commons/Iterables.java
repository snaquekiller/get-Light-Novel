package getln.data.commons;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.google.common.collect.Iterators;

/**
 * Common iterable functions.
 */
public final class Iterables {

    /**
     * Private constructor.
     */
    private Iterables() {
        // No instantiation allowed
    }

    /**
     * Returns a sequential stream on the provided iterable.
     *
     * @param <T>      The iterable type.
     * @param iterable The iterable.
     * @return The stream.
     */
    public static <T> Stream<T> toStream(Iterable<T> iterable) {
        if (iterable == null) {
            return Stream.empty();
        }
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    /**
     * Determines if the given iterable contains no elements.
     * <p/>
     * <p>There is no precise {@link Iterator} equivalent to this method, since one can only ask an iterator whether it has any
     * elements <i>remaining</i> (which one does using {@link Iterator#hasNext}).
     * From Guava.
     *
     * @return {@code true} if the iterable contains no elements
     */
    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection<?>) iterable).isEmpty();
        }
        return !iterable.iterator().hasNext();
    }

    /**
     * Returns the number of elements in {@code iterable}.
     * From Guava.
     *
     * @param iterable The iterable to perform its size.
     * @return The number of elements in {@code iterable}.
     */
    public static int size(Iterable<?> iterable) {
        return (iterable instanceof Collection) ? ((Collection<?>) iterable).size() : Iterators.size(iterable.iterator());
    }

}
