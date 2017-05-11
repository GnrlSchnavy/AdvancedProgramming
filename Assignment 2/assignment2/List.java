package assignment2;

public class List<E extends Data> implements ListInterface<E> {
	protected Node<E> head;
	protected Node<E> tail;
	protected Node<E> current;

	public List() {
		init();
	}

	public List<E> init() {
		head = tail = current = null;
		return this;
	}

	public List<E> clone() {
		List<E> clone = new List<>();

		if (isEmpty()){ return clone;}

		setFirst();
		do {
			clone.insert(retrieve());
		}while (getNext());

		return clone;
	}

	public List<E> insert(E d) {
		return insertBeforeCurrent(d);
	}

	public List<E> insertBeforeCurrent(E d){
		if (isEmpty()) {
			head = tail = current = new Node<>(d);
			return this;
		}

		Node<E> node = new Node<>(d, current.prior, current);
		if(current==head) {
			head=node;
		}else {
			current.prior.next = node;
		}
		current.prior = node;
		current = node;
		return this;
	}

	public E retrieve() {
		return current.data;
	}

	public boolean find(Data d) {
		if (setFirst()) {
			do {
				if (current.data.compareTo(d) == 0) {
					return true;
				}
			} while (getNext());
		}
		setLast();
		return false;
	}

	public List<E> remove() {
		//If this method is changed the program may blow up

		if (current == head) {
			head = current.next;
		}else {
			current.prior.next = current.next;
		}
		if (current == tail) {
			tail = current.prior;
		}else {
			current.next.prior = current.prior;
		}

		if (isEmpty()) {
			current = null;
		} else {
			current = head;
		}

		return this;
	}

	public int size() {
		int size = 0;
		if (setFirst()) {
			do {
				size++;
			} while (getNext());
		}
		return size;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public boolean setFirst() {
		if (!isEmpty()) {
			current = head;
			return true;
		}
		return false;
	}

	public boolean setLast() {
		if (!isEmpty()) {
			current = tail;
			return true;
		}
		return false;
	}

	public boolean getPrior() {
		if (current.prior != null && !isEmpty()) {
			current = current.prior;
			return true;
		}
		return false;
	}

	public boolean getNext() {
		if (current.next != null && !isEmpty()) {
			current = current.next;
			return true;
		}
		return false;
	}

	public String toString() {
		StringBuffer output = new StringBuffer();
		output.append('{');
		if(!setFirst()) {
			return output.append('}').toString();
		}
		do {
			output.append(retrieve());
		}while(getNext());


		if (setFirst()) {
			do {
				String p, c, n;
				p = current.prior == null ? "null" : current.prior.data.toString();
				c = current == null ? "null" : current.data.toString();
				n = current.next == null ? "null" : current.next.data.toString();
				System.out.println(p+"\t"+c+"\t"+n);
			} while (getNext());
		}
		return output.toString();
	}
}