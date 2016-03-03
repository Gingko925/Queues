import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
	private int size;
	
	private class Node
	{
		private Item item;
		private Node next;
		private Node prev;
	}
	
	private class RQIterator implements Iterator<Item>
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
	
	public RandomizedQueue()                 // construct an empty randomized queue
	{
		first = null;
		last = null;
		size = 0;
	}
	
	public boolean isEmpty()                 // is the queue empty?
	{
		return (first == null) || (last == null);
	}
	
	public int size()                        // return the number of items on the queue
	{
		return size;
	}
	
	public void enqueue(Item item)           // add the item
	{
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		
		Node currentLast = last;
		if (isEmpty()) {
			last = new Node();
			first = last;
			last.item = item;
			last.next = null;
			last.prev = null;
		}
		else {
			last.next = new Node();
			last = last.next;
			last.next = null;
			last.prev = currentLast;
			int n = StdRandom.uniform(this.size + 1);
			if (n == size) {
				last.item = item;
			}
			else {
				if (n < size / 2) {
					Node temp = first;
					for (int i = 0; i < n; i++) {
						temp = temp.next;
					}
					last.item = temp.item;
					temp.item = item;
				}
				else {
					Node temp = currentLast;
					for (int i = size-1; i > n; i--) {
						temp = temp.prev;
					}
					last.item = temp.item;
					temp.item = item;
				}
			}
		}
		
		size++;
	}

	
	public Item dequeue()                    // remove and return a random item
	{
		if (first == null) {
			throw new java.util.NoSuchElementException();
		}
		
		else if (size == 1) {
			Node curretFirst = first;
			first = null;
			last = null;
			size--;
			return curretFirst.item;	
		}
		else {
			int rdm = StdRandom.uniform(size);
			if (rdm < size/2) {
				Node temp = first;
				for (int i = 0; i < rdm; i++) {
					temp = temp.next;
				}
				if (temp.prev != null) {
					temp.prev.next = temp.next;
					temp.next.prev = temp.prev;
				}
				else {
					first = first.next;
					first.prev = null;
				}

				size--;
				return temp.item;

			}
			else {
				Node temp = last;
				for (int i = size-1; i > rdm; i--) {
					temp = temp.prev;
				}
				if (temp.next != null) {
					temp.next.prev = temp.prev;
					temp.prev.next = temp.next;
				}
				else {
					last = last.prev;
					last.next = null;
				}

				size--;
				return temp.item;
				
			}
			
		}

	}
	
	public Item sample()                     // return (but do not remove) a random item
	{
		if (first == null) {
			throw new java.util.NoSuchElementException();
		}
		int n = StdRandom.uniform(size);
		Node current = first;
		for (int i = 0; i < n; i++) {
			current = current.next;
		}
		return current.item;
	}
	
	public Iterator<Item> iterator()         // return an independent iterator over items in random order
	{
		return new RQIterator();
	}
	
	public static void main(String[] args)   // unit testing
	{  }
	
}
