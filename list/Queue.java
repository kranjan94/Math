package list;

/**
 * Crippled list that allows additions only to the end of the list and removals and access
 * only to the front.
 * @author 		Kushal Ranjan
 * @param <E>	class of the objects that will be stored in this Queue
 */
public class Queue<E> {

	private LinkedList<E> objects;
	
	/**
	 * Initializes a new empty queue.
	 */
	public Queue() {
		objects = new LinkedList<E>();
	}
	
	/**
	 * Constructs a new Queue from a preset list.
	 * @param objects	the list of type <E> containing the objects to use
	 */
	public Queue(LinkedList<E> objects) {
		this.objects = objects;
	}
	
	/**
	 * Returns the element at the front of the queue.
	 */
	public E peek() {
		if(objects.size() == 0) {
			throw new QueueEmptyException();
		} else {
			return objects.getFirst();
		}
	}
	
	/**
	 * Returns and removes the element at the front of the queue.
	 */
	public E pop() {
		if(objects.size() == 0) {
			throw new QueueEmptyException();
		} else {
			return objects.removeFirst();
		}
	}
	
	/**
	 * Adds a new object to this queue.
	 * @param obj	object of type E to be added
	 */
	public void push(E obj) {
		objects.add(obj);
	}
	
	/**
	 * Adds all of the objects in another queue of the same type to the end of this queue.
	 * @param other	Queue<E> to be concatenated to this Queue<E>
	 */
	public void concatenate(Queue<E> other) {
		objects.concatenate(other.objects);
	}
	
	/**
	 * Returns a copy of the <E> list for this Queue to avoid access violations.
	 * @return	a copy of objects
	 */
	public LinkedList<E> getObjects() {
		LinkedList<E> ret = objects.copy();
		return ret;
	}
}

/**
 * Exception thrown when a peek or pop call is made on an empty queue.
 */
class QueueEmptyException extends RuntimeException {}
