package list;

/*
 * Implementation of doubly-linked list using Java generics. Uses head and tail references.
 * Code skeleton adapted from The Harker School's Honors Data Structures project.
 */
public class LinkedList<E>
{
	private DoubleNode first;
	private DoubleNode last;
	private int size;

	public LinkedList()
	{
		first = null;
		last = null;
		size = 0;
	}

	public String toString()
	{
		DoubleNode node = first;
		if (node == null)
			return "[]";
		String s = "[";
		while (node.getNext() != null)
		{
			s += node.getValue() + ", ";
			node = node.getNext();
		}
		return s + node.getValue() + "]";
	}

	/**
	 * Returns the node at position index.
	 * @param index	index of the node, 0 <= index < size/2
	 * @return		node at position index
	 */
	private DoubleNode getNodeFromFirst(int index)
	{
		DoubleNode ret = first;
		for(int i = 0; i < index; i++) {
			if(ret.getNext() == null) {
				throw new IndexOutOfBoundsException();
			}
			ret = ret.getNext();
		}
		return ret;
	}

	/**
	 * Returns the node at position index.
	 * @param index	index of the node, size/2 <= index < size
	 * @return		node at position index
	 */
	private DoubleNode getNodeFromLast(int index)
	{
		DoubleNode ret = last;
		for(int i = size-1; i > index; i--) {
			if(ret.getPrevious() == null) {
				throw new IndexOutOfBoundsException();
			}
			ret = ret.getPrevious();
		}
		return ret;
	}

	/**
	 * Returns the node at position index.
	 * @param index	index of the node, 0 <= index < size
	 * @return		node at position index
	 */
	private DoubleNode getNode(int index)
	{
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else if(index >= size/2) {
			return getNodeFromLast(index);
		} else {
			return getNodeFromFirst(index);
		}
	}

	public int size()
	{
		return size;
	}

	/**
	 * Returns the item stored at the given index
	 */
	public E get(int index)
	{
		return (E)(getNode(index).getValue());
	}

	/**
	 * Replaces the element at position index with obj, returns the element formerly at 
	 * the specified position.
	 * @param index	index of node to change
	 * @param obj	object to which the node at position index should be changed
	 * @return		item previously at position index
	 */
	public E set(int index, E obj)
	{
		DoubleNode node = getNode(index);
		E ret = (E)(node.getValue());
		node.setValue(obj);
		return ret;
	}

	/**
	 * Appends obj to end of list; returns true.
	 * @param obj	object to add to end of list
	 * @return		the DoubleNode to which the item was added
	 */
	public DoubleNode add(E obj)
	{
		DoubleNode node = new DoubleNode(obj, this);
		if(size == 0) {
			first = node;
			last = first;
		} else {
			last.setNext(node);
			last.getNext().setPrevious(last);
			last = last.getNext();
		}
		size++;
		return node;
	}

	/**
	 * Removes the specified indexed element from the list. Shifts elements to the right
	 * of the node to the left (decreasing their indices by 1).
	 * @param index	index of object to remove
	 * @return		the object removed
	 */
	public E remove(int index)
	{
		if(index == 0) {
			return removeFirst();
		} else if(index == size-1) {
			return removeLast();
		} else {
			DoubleNode node = getNode(index);
			node.getPrevious().setNext(node.getNext());
			node.getNext().setPrevious(node.getPrevious());
			size--;
			return (E)(node.getValue());
		}
	}

	/**
	 * Inserts object into the list. Items with index >= "index" are shifted to the right
	 * by 1.
	 * @param index	index at which to insert the object
	 * @param obj
	 */
	public DoubleNode add(int index, E obj)
	{
		DoubleNode newNode = new DoubleNode(obj, this);
		if(size == 0) {
			first = newNode;
			last = first;
		} else if(index == 0) {
			newNode.setNext(first);
			first.setPrevious(newNode);
			first = newNode;
		} else if(index == size) {
			return add(obj);
		} else {
			DoubleNode change = getNode(index);
			newNode.setNext(change);
			newNode.setPrevious(change.getPrevious());
			newNode.getPrevious().setNext(newNode);
			change.setPrevious(newNode);
		}
		size++;
		return newNode;
	}
	
	/**
	 * Adds an item to the beginning of this list.
	 * @param obj	object to be added
	 */
	public DoubleNode addFirst(E obj)
	{
		return add(0, obj);
	}

	/**
	 * Adds an item to the end of this list.
	 * @param obj	object to be added
	 */
	public DoubleNode addLast(E obj)
	{
		return add(obj);
	}

	/**
	 * Returns the item at index 0.
	 */
	public E getFirst()
	{
		if(size == 0) throw new IndexOutOfBoundsException();
		return (E)first.getValue();
	}

	/**
	 * Returns the item at index size-1.
	 */
	public E getLast()
	{
		if(size == 0) throw new IndexOutOfBoundsException();
		return (E)last.getValue();
	}
	
	/**
	 * Removes the item at index 0.
	 * @return	the item removed
	 */
	public E removeFirst()
	{
		if(size == 0) {
			throw new IndexOutOfBoundsException();
		} else {
			E ret = (E)first.getValue();
			if(size == 1) {
				first = null;
				last = null;
			} else {
				first = first.getNext();
				first.setPrevious(null);
			}
			size--;
			return ret;
		}
	}

	/**
	 * Removes the item at index size-1.
	 * @return	the item removed
	 */
	public E removeLast()
	{
		if(size == 0) {
			throw new IndexOutOfBoundsException();
		} else {
			E ret = (E)last.getValue();
			if(size == 1) {
				first = null;
				last = null;
			} else {
				last = last.getPrevious();
				last.setNext(null);
			}
			size--;
			return ret;
		}
	}
	
	/**
	 * Returns a copy of this list.
	 * @return	a LinkedList<E> containing all the Es that this list contains
	 */
	public LinkedList<E> copy() {
		LinkedList<E> copy = new LinkedList<E>();
		Iterator<E> it = iterator();
		while(it.hasNext()) {
			copy.add(it.next());
		}
		return copy;
	}
	
	/**
	 * Checks whether this list contains a given object; uses a linear search.
	 * @param obj	object for which to search
	 * @return		true iff obj is in this list
	 */
	public boolean contains(E obj) {
		Iterator<E> it = iterator();
		while(it.hasNext()) {
			if(it.next().equals(obj)) {
				return true;
			}
		}
		return false;
	}
	
	public Object[] toArray() {
		Object[] out = new Object[size];
		Iterator<E> it = iterator();
		for(int i = 0; i<out.length; i++) {
			out[i] = it.next();
		}
		return out;
	}
	
	/**
	 * Concatenates another LinkedList of the same type to the end of this list.
	 * @param other	the LinkedList<E> to add to the end of this list
	 */
	public void concatenate(LinkedList<E> other) {
		if(other.size() == 0) return;
		if(last != null) last.setNext(other.first);
		else first = other.first;
		last = other.last;
		other.first = null;
		other.last = null;
		size += other.size;
		other.size = 0;
	}
	
	/**
	 * Used to change list quantities ONLY from within the DoubleNode class.
	 */
	void decrementSize() {
		size--;
	}
	
	void incrementFirst() {
		first = first.getNext();
	}
	
	void decrementLast() {
		last = last.getPrevious();
	}
	
	/**
	 * Returns an iterator for this linked list.
	 */
	public Iterator<E> iterator()
	{
		return new LinkedListIterator();
	}

	/*
	 * Implements the Iterator<E> interface for a LinkedList<E> object.
	 */
	private class LinkedListIterator implements Iterator<E>
	{
		private DoubleNode nextNode;
		private DoubleNode lastNode;

		public LinkedListIterator()
		{
			nextNode = first;
			lastNode = null;
		}

		/**
		 * Checks whether a call of next() will return a valid object.
		 * @return	true iff there are items left in this list to be returned
		 */
		public boolean hasNext()
		{
			return nextNode != null;
		}

		/**
		 * Returns the next item in this list.
		 * @return	next item in this list
		 */
		public E next()
		{
			if(hasNext()) {
				E ret = (E)nextNode.getValue();
				lastNode = nextNode;
				nextNode = nextNode.getNext();
				return ret;
			} else {
				return null;
			}
		}

		/**
		 * Removes the last object returned by next() from the list.
		 */
		public void remove()
		{
			if(lastNode == null) return;
			if(lastNode != first) lastNode.getPrevious().setNext(lastNode.getNext());
			else first = first.getNext();
			if(lastNode != last) lastNode.getNext().setPrevious(lastNode.getPrevious());
			else last = last.getPrevious();
			size--;
		}
	}
}