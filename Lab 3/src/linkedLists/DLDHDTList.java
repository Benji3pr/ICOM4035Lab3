package linkedLists; 

import java.util.NoSuchElementException;

public class DLDHDTList<E> extends AbstractDLList<E> {
	private DNode<E> header, trailer; 
	private int length; 
	
	public DLDHDTList() { 
		header = new DNode<E>();   
        trailer = new DNode<E>();  
        header.setNext(trailer);   
         trailer.setPrev(header);  
        length = 0;
	}
	
	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}
	
	public void addLastNode(Node<E> nuevo) { 
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = trailer.getPrev();  
		nBefore.setNext(dnuevo); 
		trailer.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(trailer); 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = ((DNode<E>) target).getPrev(); 
		DNode<E> nAfter = ((DNode<E>) target);
		nBefore.setNext(dnuevo);
		nAfter.setPrev(dnuevo);
		dnuevo.setPrev(nBefore);
		dnuevo.setNext(nAfter);
		length++;
	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target)
			throws NoSuchElementException {
		if ((DNode<E>)target == trailer.getPrev()) 
			throw new NoSuchElementException("Target is the last element on the list.");
		
		return ((DNode<E>)target).getNext(); 
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		if ((DNode<E>)target == header.getNext()) 
			throw new NoSuchElementException("Target is the first element on the list.");
		return ((DNode<E>)target).getPrev(); 
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		DNode<E> nBefore = ((DNode<E>) target).getPrev(); 
		DNode<E> nAfter = ((DNode<E>) target).getNext();
		nBefore.setNext(nAfter);
		nAfter.setPrev(nBefore);
		((DNode<E>) target).clean();
		length--;
	}
	
	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.clean(); 
			header = nnode; 
		}
	}
	
	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		// TODO
	}
		
	protected void finalize() throws Throwable {
	    try {
			this.destroy(); 
	    } finally {
	        super.finalize();
	    }
	}
	
	// The following two methods are to be implemented as part of an exercise
		public Object[] toArray() {
			Object[] newArray = new Object[this.length()];
			DNode<E> curr = this.header.getNext();
			for(int i=0; i<this.length(); i++){
				newArray[i]=curr.getElement();
				curr=curr.getNext();
			}
				
			return newArray;
		}


		
		
		@Override
		public <T1> T1[] toArray(T1[] array) {
			
			T1[] newA = (T1[]) array;
			
			return newA;
	}

}
