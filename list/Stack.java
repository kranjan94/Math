package list;

/**
 * Crippled list that provides access to only the most recently added element.
 * @author 		Kushal Ranjan
 * @param <E>	class of the objects that will be stored in this Stack
 */
public class Stack<E> {

	private LinkedList<E> objects;
	
	/**
	 * Initializes a new empty stack.
	 */
	public Stack() {
		objects = new LinkedList<E>();
	}
	
	/**
	 * Returns the most recently added element.
	 */
	public E peek() {
		if(objects.size() == 0) {
			throw new StackEmptyException();
		} else {
			return objects.getLast();
		}
	}
	
	/**
	 * Returns and removes the most recently added element.
	 */
	public E pop() {
		if(objects.size() == 0) {
			throw new StackEmptyException();
		} else {
			return objects.removeLast();
		}
	}
	
	/**
	 * Adds a new object to this stack.
	 * @param obj	object of type E to be added
	 */
	public void push(E obj) {
		objects.add(obj);
	}
}

/**
 * Exception thrown when a peek or pop call is made on an empty stack.
 */
class StackEmptyException extends RuntimeException {}
