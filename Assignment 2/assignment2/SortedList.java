package assignment2;

public class SortedList<E extends Data> extends List<E> {

	public List<E> insert(E d) {
		if (isEmpty()) {
			return super.insertBeforeCurrent(d);
		}
		if (setFirst()) {
			do {
				if (d.compareTo(current.data) < 0) {
					return super.insertBeforeCurrent(d);
				}
			} while (getNext());
			//insert to the tail
			Node<E> node=new Node<>((E) d,tail,null);
			tail.next = node;
			tail = node;
		}
		return this;
	}

}
