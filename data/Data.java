package data;
/**
 * Interface for representing data types and performing operations central to all data types.
 * @author 	Kushal Ranjan
 * @version	050913
 */
interface Data<T> {
	/**
	 * Adds this data piece to another.
	 * @param other	other data piece
	 * @return		the sum of this and other
	 */
	public abstract T add(T other);
}
