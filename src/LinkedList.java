import java.util.*;

/**
 *This class implements the ADT making the linked list to be used
 *
 * @author Kyle Sadler
 */
public class LinkedList<E> implements LinkedListADT<E> {

	private ListNode<E> head;               // creates the different nodes that will be used
	private ListNode<E> tail;
	private ListNode<E> curr;
	private int numItems;                  // creates the counter for elements in list

	/**
	 * Sets the initial references to null and starts the count of how many items there are.
	 *
	 */
	public LinkedList() {
		head = tail = new ListNode<E>(null);   //makes a dumby node
		numItems = 0;                           //shows there is no elements yet
	}
	/**
	 * takes a new item and adds it to the end of the list. It also changes the tail reference to the new node
	 *
	 * @param item it gets the item that is trying to be added
	 * 
	 */
	public void add(E item) {
		tail.setNext(new ListNode<E>(item));    // makes new node from the parameter
		tail = tail.getNext();                  //makes the tail point to last node
		numItems++;                             // adds 1 for the new node
	}
	/**
	 * This method gets the position and item to be added, then checks if it is valid and then adds it either to the end of the list or cycle through the list till the current node is equal to the pos
	 *
	 * @param pos  gets the spot in where the list it should be added
	 * @param item  gets the item that is wanted to be added
	 * @return (description of the return value)
	 */
	public void add(int pos, E item) throws InvalidListPositionException {
		curr = head;                           //allowing to step through each node
		if (pos <= 0 || pos > numItems)
			throw new InvalidListPositionException();

		if (pos == numItems) {              //if the pos is at end of list just adds there
			add(item);
			return;
		}

		for (int i = 0; i < pos-1; i++) {
			curr = curr.getNext();         //steps through the list
		}
		curr.setNext(new ListNode<E>(curr.getPrev(), item, curr.getNext()));  //adds the new node
		numItems++;
	}
	/**
	 * This method checks if it is valid and then it removes the node by changing the next field to the one after that is being removed
	 * and then sets that next fields previous feild to the one that it is now connected to
	 *
	 * @param pos  takes in the position at which to remove
	 * @return data, the object at which to remove
	 */
	public E remove(int pos) throws InvalidListPositionException {
		curr = head;
		E data;
		
		if (pos > numItems || pos <= 0)
			throw new InvalidListPositionException();
		// gets position before node to be removed
		for (int i = 0; i < pos-1; i++) {
			curr = curr.getNext();
		}
		// takes out that node by jumping it
		if (curr.getNext().getNext() != null) {
			data = curr.getNext().getData();
			curr.setNext(curr.getNext().getNext());
			curr.getNext().setPrev(curr);
		}
		// if last position
		else {
			data = curr.getNext().getData();
			curr.getNext().setPrev(null);
			curr.setNext(null);
		}
		numItems--;
 
		return data;
	}
	/**
	 * This method starts at the head node and cycles through till it gets to the one designated and returns it
	 *
	 * @param pos  gets the input of which spot to get
	 * @return the contents of the node
	 */
	public E get(int pos) throws InvalidListPositionException {
		if (pos < 0 || pos > numItems)
			throw new InvalidListPositionException();
		//gets the node and steps though till it sees the positon it wants
		ListNode<E> curr = head;
		for (int i = 0; i < pos; i++) {
			curr = curr.getNext();
		}
		return curr.getData();
	}
	/**
	 * This method checks to see if there is more than just the dumby node
	 *
	 * @return true if there is only a head node
	 */
	public boolean isEmpty() {
		if (head.getNext() == null)
			return true;
		else
			return false;
	}
	/**
	 * This method has a check for the size of the list
	 *
	 * @return the number of nodes
	 */
	public int size() {
		return numItems;
	}
	/**
	 * This method creates the iterator
	 *
	 * @return new iterator from the iterator class
	 */
	public Iterator<E> iterator() {
		return new LinkedListIterator<E>(this);
	}
}

