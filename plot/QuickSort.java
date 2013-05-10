package plot;
import list.*;

/**
 * Implementation of the quicksort algorithm using Queues of Comparable objects.
 * @author 	Kushal Ranjan
 * @version	042813
 */
public class QuickSort {
	
	/**
	 * Performs a quicksort on a list of input data.
	 * Runtime: O(n log n) for input length n; O(n^2) if nearly sorted.
	 * @param input	LinkedList<Comparable> of Comparable input items
	 * @return		a sorted version of input
	 */
	public static LinkedList<Comparable> sort(LinkedList<Comparable> input) {
		if(input.size() <= 1) return input;
		LinkedList<Comparable> low = new LinkedList<Comparable>();
		LinkedList<Comparable> equal = new LinkedList<Comparable>();
		LinkedList<Comparable> high = new LinkedList<Comparable>();
		Comparable pivot = input.get((int)(Math.random()*input.size()));
		partition(input, pivot, low, equal, high);
		low = sort(low);
		high = sort(high);
		low.concatenate(equal);
		low.concatenate(high);
		return low;
	}
	
	/**
	 * Partitions the input list whole into three lists: low contains the elements smaller than
	 * pivot, equal contains the elements equal to pivot, and high contains the elements higher
	 * than pivot.
	 * @param whole	input list
	 * @param pivot	pivot to use for comparison
	 * @param low	list of elements in whole lower than pivot
	 * @param equal	list of elements in whole equal to pivot
	 * @param high	list of elements in whole higher than pivot
	 */
	private static void partition(LinkedList<Comparable> whole, Comparable pivot,
									LinkedList<Comparable> low, LinkedList<Comparable> equal,
									LinkedList<Comparable> high) {
		Iterator<Comparable> it = whole.iterator();
		while(it.hasNext()) {
			Comparable check = it.next();
			int comp = check.compareTo(pivot);
			if(comp < 0) {
				low.add(check);
			} else if(comp == 0) {
				equal.add(check);
			} else {
				high.add(check);
			}
		}
	}
}
