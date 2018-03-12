package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.NoSuchElementException;

import linkedLists.LinkedList;
import linkedLists.AbstractSLList.SNode;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		((SNode<E>) nuevo).setNext(first);
		first = ((SNode<E>) nuevo);
		if(length == 0)
			last = ((SNode<E>) nuevo);
		length++;
		
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		SNode<E> nextN = ((SNode<E>) target).getNext();
		((SNode<E>) target).setNext((SNode<E>) nuevo);
		((SNode<E>) nuevo).setNext(nextN);
		length++;
		
		
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		if((SNode<E>)target == first){
			this.addFirstNode(nuevo);
		}
		else{
			Node<E> prevNode = findNodePrevTo(target);  
			this.addNodeAfter(prevNode, nuevo); 
		}
		
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		return first;
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		return last;
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		SNode<E> aNode = ((SNode<E>) target).getNext(); 
		if (aNode == null)  
			throw new NoSuchElementException("getNextNode(...) : target is the last node."); 
		else 
			return aNode;
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		if ((SNode<E>)target == first)  
			throw new NoSuchElementException("getPrevNode(...) : target is the first node."); 
		else 
			return findNodePrevTo(target);
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		if((SNode<E>)target == first){
			first = first.getNext();
		}
		else{
			SNode<E> prevNode = ((SNode<E>)findNodePrevTo(target));
			SNode<E> afterNode = ((SNode<E>) target).getNext();
			prevNode.setNext(afterNode);
			
		}
		length--;
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}
	
	private Node<E> findNodePrevTo(Node<E> target) {
		// Pre: target is a node in the list
		if (target == first) 
			return null; 
		else { 
			SNode<E> prev = first; 
			while (prev != null && prev.getNext() != target) 
				prev = prev.getNext();  
			return prev; 
		}
	}

}
