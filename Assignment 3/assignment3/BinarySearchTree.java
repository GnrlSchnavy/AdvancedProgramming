package assignment3;

import java.util.ArrayList;
import java.util.Iterator;

public class BinarySearchTree<E extends Data>{

	public Node<E> root;

	public BinarySearchTree(){
		root=null;
	}

	public void insert(E data){
		Node<E> node=new Node<>(data);
		Node<E> parent= null;
		Node<E> pointer = root;
		while (pointer != null) {
			parent = pointer;
			pointer = pointer.getChild(node.compareTo(pointer) < 0);
		}
		node.parent = parent;
		if (parent == null) {
			root = node;
		} else if(node.compareTo(parent) < 0){
			parent.left = node;
		}else{
			parent.right=node;
		}
	}

	public void delete(E data){
		if(contains(data)){
			Node<E> node= retrieve(data);
			if(!node.hasChild(true)){
				replaceWith(node, node.right);
			}else if (!node.hasChild(false)){
				replaceWith(node, node.left);
			}else{
				Node<E> successor=min(node.right);
				if(!successor.parent.equals(node)){    //if the successor is not the direct child of node
					replaceWith(successor, successor.right);
					successor.right=node.right;
					successor.right.parent=successor;
				}
				replaceWith(node, successor);
				successor.left=node.left;
				successor.left.parent=successor;
			}
		}
	}

	private void replaceWith(Node<E> original, Node<E> newNode){
		if(original.parent==null){
			root=newNode;
		}else if(original.equals(original.parent.getChild(true))){
			original.parent.left=newNode;
		}else{
			original.parent.right=newNode;
		}
		if(newNode!=null){
			newNode.parent=original.parent;
		}
	}

	public boolean contains(E data) {
		return retrieve(data)!=null;
	}

	public Node<E> retrieve(E data){
		Node<E> pointer=root;
		while(pointer!=null && data.compareTo(pointer.data)!=0){
			pointer = pointer.getChild(data.compareTo(pointer.data) < 0);
		}
		return pointer;
	}

	public Node<E> min(Node<E> node){
		Node<E> output=node;
		while(output.hasChild(true)){
			output=output.getChild(true);
		}
		return output;
	}

	public Node<E> max(Node<E> node) {
		Node<E> output = node;
		while (output.hasChild(false)) {
			output = output.getChild(false);
		}
		return output;
	}

	private Node<E> successor(Node<E> node){
		if (node.hasChild(false)) {
			return min(node.getChild(false));
		}
		Node<E> parent=node.parent;
		while (parent != null && node.equals(parent.right)){
			node = parent;
			parent = parent.parent;
		}
		return parent;
	}

	private Node<E> predecessor(Node<E> node){
		if (node.hasChild(true)) {
			return max(node.getChild(true));
		}
		Node<E> parent = node.parent;
		while (parent != null && node.equals(parent.left)) {
			node = parent;
			parent = parent.parent;
		}
		return parent;
	}

	public Iterator<E> ascendingIterator() {
		ArrayList<E> list = new ArrayList<>();
		if(root!=null){
			Node<E> pointer = min(root);
			list.add(pointer.data);
			while (successor(pointer) != null) {
				pointer = successor(pointer);
				list.add(pointer.data);
			}
		}
		return list.iterator();
	}

	public Iterator<E> descendingIterator() {
		ArrayList<E> list = new ArrayList<>();
		if(root!=null) {
			Node<E> pointer = max(root);
			list.add(pointer.data);
			while (predecessor(pointer) != null) {
				pointer = predecessor(pointer);
				list.add(pointer.data);
			}
		}
		return list.iterator();
	}

}