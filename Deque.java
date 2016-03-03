import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int size;
    
    private class Node
    {
		private Item item;
		private Node next;
		private Node prev;
		
	}
	
	private class DequeIterator implements Iterator<Item>
	{
		private Node current = first;
		public boolean hasNext()
		{
			return current != null;
		}
		public Item next()
		{
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item; 
		}
		public void remove() { 
			throw new java.lang.UnsupportedOperationException();
		}
	}
	
	public Deque()                           // construct an empty deque
    {
		first = null;
		last = null;
		size = 0;
    }
	
	public boolean isEmpty()                 // is the deque empty?
	{
	    return (first == null) || (last == null);
	}
	
	public int size()                        // return the number of items on the deque
	{
	    return size;
	}
	
	public void addFirst(Item item)          // add the item to the front
	{
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		Node currentFirst = first;
		first = new Node();
		if (isEmpty()) {
			last = first;
		}
		else {
			currentFirst.prev = first;
		}
		first.item = item;
		first.next = currentFirst;
		first.prev = null;
		size++;
	}
	
	public void addLast(Item item)           // add the item to the end
	{
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		Node currentLast = last;
		if (isEmpty()) {
			first = new Node();
			last = first;
		}
		else {
			last.next = new Node();
			last = last.next;
		}
		last.item = item;
		last.next = null;
		last.prev = currentLast;
		size++;
	}
	
	public Item removeFirst()                // remove and return the item from the front
	{
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		Node currentFirst = first;

		first = first.next;
		if (isEmpty()) {
			last = first;
		}
		else {
		    first.prev = null;
		}
		size--;
		
		return currentFirst.item;
	}
	
	public Item removeLast()                 // remove and return the item from the end
	{		   
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		Node currentLast = last;
		last = last.prev;
		if (isEmpty())
			first = last;
		else
		    last.next = null;

		size--;
		return currentLast.item;
	}
	
	public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	{
		return new DequeIterator();
	}
	
	public static void main(String[] args)   // unit testing
	{	}
}
