package queue;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {

	}

	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() { 
		return new QueueIterator();
	}

	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	x the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E x) {
		QueueNode<E> newNode = new QueueNode<>(x);
		if(size()==0){
			last = newNode;
			last.next = last;
		} else {
			QueueNode<E> first = last.next;
			last.next = newNode;
			last = newNode;
			newNode.next = first;
		}
		size++;
		return true;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		E element = peek();
		if (element != null){
			if(size() == 1){
				last = null;
			} else {
				last.next = last.next.next;
			}
			size--;
		}
		return element;
	}

	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(size == 0){
			return null;
		} else {
			return last.next.element;
		}
		
	}
	
	public void append(FifoQueue<E> q){
		if (q==this){ //kollar så kön inte lägger till sig själv
			throw new IllegalArgumentException();
		} else {
			if (size()!=0 && q.size!=0){
				QueueNode<E> first = last.next; //första nod i denna queue
				last.next = q.last.next; //vår sista nod pekar på q:s första
				q.last.next = first; //q:s sista pekar på vår första
				size += q.size();
			} else if (q.size()!=0){
				last = q.last;
				size = q.size();
			}
		}
		
		
			
		
	}


	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}

	}
	
	private class QueueIterator implements Iterator<E>{
		private QueueNode<E> pos;
		
		private QueueIterator() {
			if(size()!=0){ //om listan är tom
				pos = last.next; //Iteratorn börjar på första noden'
			}
		}

		@Override
		public boolean hasNext() {
			return pos!=null;
		}

		/**
		 * Retrieves next element
		 * 
		 */
		@Override
		public E next() {
			if(hasNext()){
				E element = pos.element;
				if (pos == last){
					pos = null;
				} else {
					pos = pos.next;
				}
				return element;
			} else {
				throw new NoSuchElementException();
			}
		}

	}

}
