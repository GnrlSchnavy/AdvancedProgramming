package assignment3;

class Node<E extends Data> implements Clonable{

	E data;
	Node<E> parent,left,right;

	Node(E d){
		parent=left=right=null;
		this.data=d;
	}

	public boolean hasChild(boolean isLeft) {
			return getChild(isLeft)!=null;
	}

	public boolean equals(Node<E> node){
		return node != null && compareTo(node) == 0;
	}

	public Node<E> getChild(boolean isLeft){
		//true->left    false->right
		if(isLeft){
			return left;
		}else{
			return right;
		}
	}

	public Node<E> clone(){
		Node<E> clone;
		try {
			clone = (Node<E>) super.clone();
		} catch (CloneNotSupportedException CNSE) {
			throw new Error("Impossible! instance cannot be cloned");
		}
		clone.data = data == null ? null : (E) data.clone();
		return clone;
	}

	public int compareTo(Node<E> o) {
		return data.compareTo(o.data);
	}

	public String toString(){
		String p= parent== null?"null":parent.data.toString();
		String l = left == null ? "null" : left.data.toString();
		String r = right == null ? "null" : right.data.toString();
		return data.toString()+"\t\t"+p+"\t"+l+"\t"+r;
	}
}