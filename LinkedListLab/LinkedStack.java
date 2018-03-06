public class LinkedStack<T> implements StackADT<T> {
	
	private List<T> linkedList;
	
	public LinkedStack()
	{
		this.linkedList = new List<T>();
	}
	
	/**
	 * push
	 * @param element
	 */
	public void push(T element)
	{
		this.linkedList.addFirst(element);
	}
	
	/**
	 * pop
	 */
	public T pop()
	{
		Node<T> node = this.linkedList.getFirst();
		this.linkedList.delete(0);
		return node.getData();
	}
	
	/**
	 * peek
	 */
	public T peek()
	{
		Node<T> node = this.linkedList.getFirst();
		return node.getData();
	}
	
	/**
	 * isEmpty
	 */
	public boolean isEmpty()
	{
		return this.linkedList.length() < 0 ? true:false;
	}
	
	/**
	 * size
	 */
	public int size()
	{
		return this.linkedList.length();
	}
	
	/**
	 * toString
	 */
	public String toString()
	{
		return "Stacky Stacky";
	}
}
