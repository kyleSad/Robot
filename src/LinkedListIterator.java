import java.util.*;

/**
 *Creates the iterator for the robot log to use as it goes through the elements of the linked list
 *
 * @author Kyle Sadler
 */
public class LinkedListIterator<E> implements Iterator<E>{
		private LinkedList<E> robotLog;  //creates the list for iterator
		private int position;            //creates a pointer int
		/**
		 *This method sets the robotlog to the passed list and sets a integer to 0
		 *
		 * @param list takes in a list that was passed by iterator
		 */		
		public LinkedListIterator(LinkedList <E> list){
			robotLog= list;           // copies in the list 
			position = 0;             // startes at the 0 position
		}
		/**
		 *method creates a boolean that checks if there is another element
		 *
		 * @return true is the position is less than the size of the list
		 */
		public boolean hasNext(){
			return position <= robotLog.size();
		}
		/**
		 * This method checks that there is another element and returns that element
		 *
		 * @return the next value in the list
		 */
		public E next(){
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			E temp = null;               //initializes a object to null
			try {
				temp = robotLog.get(position);  //sets the temp to a element of the list
			} catch (InvalidListPositionException e) {
				System.out.print("invalid line number");
			}
			position++;
			return temp;
		}
		/**
		 *this method throws an error anytime it is used
		 *
		 */
		public void remove(){
			throw new UnsupportedOperationException();
		}
}