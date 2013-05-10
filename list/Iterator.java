package list;

/**
 * Steps through elements of an iterable object one by one until the object is exhausted.
 * Based on java.util.Iterator
 * @author Kushal Ranjan
 * @version 032813
 */
public interface Iterator<E> {
	/**
	 * Returns true if calling next() will not return null.
	 * @return	true iff elements are left in the iterable.
	 */
	public boolean hasNext();
	/**
	 * Returns the next item in the iterable; null if iterable is exhausted.
	 * @return	the next item in the iterable; null if iterable is exhausted.
	 */
	public E next();
	/**
	 * Removes the most recently returned element from the iterable.
	 */
	public void remove();
}