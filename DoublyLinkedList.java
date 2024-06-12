package nineToFourGPA;

/** A basic doubly linked list implementation. */
public class DoublyLinkedList<E> {
	// nested Node class
	public static class Node<E> {
		private E element;		// reference to the element stored at this node
		private Node<E> prev;	// reference to the previous node in the list
		private Node<E> next;	// reference to the subsequent node in the list
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}
		public E getElement() { return element; }
		public Node<E> getPrev() { return prev; }
		public Node<E> getNext() { return next; }
		public void setPrev(Node<E> p) { prev = p; }
		public void setNext(Node<E> n) { next = n; }
		// end of nested Node class
	}
	
	private Node<E> header;		// header sentinel
	private Node<E> trailer;	// trailer sentinel
	private int size = 0;		// number of elements in the list
	/** Constructs a new empty list. */
	public DoublyLinkedList() {
		header = new Node<>(null, null, null);		// create header
		trailer = new Node<>(null, header, null); 	// trailer is preceded by header
		header.setNext(trailer);					// header is followed by trailer
	}
	/** Returns the number of elements in the linked list. */
	public int size() { return size; }
	/** Tests whether the linked list is empty. */
	public boolean isEmpty() { return size == 0; }
	/** Returns the first node in the linked list. */
	public Node<E> nodeFirst() {
		return header.getNext();
	}
	/** Returns (but does not remove) the first element of the list. */
	public E first() {
		if (isEmpty()) return null;
		return header.getNext().getElement();	// first element is beyond header
	}
	/** Returns (but does not remove) the last element of the list. */
	public E last() {
		if (isEmpty()) return null;
		return trailer.getPrev().getElement();	// last element is before trailer
	}
	
	/** Returns the first node with the value, or NULL if it doesn't exist. */
	public Node<E> search(E element) {
		Node<E> cur = header;
		while (cur.getNext() != null && cur.getElement() != element) {
			cur = cur.getNext();
		}
		return cur;
	}
	
	/** Insert a new node with value v into the list at the head. */
	public void insertHead(E element) {
		Node<E> node = new Node<E>(element, header, header.getNext());
		header.getNext().setPrev(node);
		header.setNext(node);
		size++;
	}
	
	/** Insert a new node with value v into the list at the tail. */
	public void insertTail(E element) {
		Node<E> node = new Node<E>(element, trailer.getPrev(), trailer);
		trailer.getPrev().setNext(node);
		trailer.setPrev(node);
		size++;
	}
	
	/** Given the reference to a node p in the list, delete it from the list. */
	public void delete(Node<E> node) {
		node.getPrev().setNext(node.getNext());
		node.getNext().setPrev(node.getPrev());
	}
	
	@Override
	public String toString() {
		String display = "";
		Node<E> cur = header.getNext();
		
		while (cur.getNext() != null) {
			display += "[Element=" + cur.getElement() + ", Prev=" + cur.getPrev() + ", Next=" + cur.getNext() + "]\n";
			cur = cur.getNext();
		}
		
		return display;
	}
}