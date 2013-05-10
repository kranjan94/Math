package list;

/*
 * Represents a single node in a doubly-linked list.
 * Adapted from The Harker School's Honors Data Structures project.
 */

public class DoubleNode
{
	private Object value;  //Value stored in this node
	private DoubleNode previous;
	private DoubleNode next;
	private LinkedList myList;
	private DoubleNode partner;

	public DoubleNode(Object v, LinkedList list)
	{
		value = v;
		previous = null;
		next = null;
		myList = list;
	}

	/**
	 * Getter methods.
	 * @return	variable specified by method name
	 */
	public Object getValue()
	{
		return value;
	}
	
	public DoubleNode getPrevious()
	{
		return previous;
	}

	public DoubleNode getNext()
	{
		return next;
	}
	
	public DoubleNode getPartner(){
		return partner;
	}
	
	public LinkedList getList()
	{
		return myList;
	}

	/**
	 * Setter methods.
	 * @param p	value to which to set variable specified by method name.
	 */
	public void setValue(Object v)
	{
		value = v;
	}

	public void setPrevious(DoubleNode p)
	{
		previous = p;
	}

	public void setNext(DoubleNode n)
	{
		next = n;
	}
	
	public void setPartner(DoubleNode p){
		partner = p;
		p.partner = this;
	}
	
	public void setList(LinkedList l) {
		myList = l;
	}
	
	/**
	 * Removes and returns this node
	 */
	public DoubleNode removeNode() {
		if(previous == null && next == null) {
			myList.decrementSize();
			return this;
		}
		if(previous != null) {
			previous.setNext(next);
		} else {
			myList.incrementFirst();
		}
		if(next != null) {
			next.setPrevious(previous);
		} else {
			myList.decrementLast();
		}
		previous = null;
		next = null;
		myList.decrementSize();
		return this;
	}
}