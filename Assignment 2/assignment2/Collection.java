package assignment2;

public class Collection<E extends Data> implements Clonable{
	private SortedList<E> collection;
	Collection(){
		init();
	}

	Collection(Collection clone){
		collection = new SortedList<>();
		if (clone.collection.setFirst()) {
			do {
				collection.insert((E) clone.collection.retrieve());
			} while (clone.collection.getNext());
		}
	}

	private void init() {
		collection = new SortedList<>();
	}

	public Collection clone(){
		Collection clone;
		try {
			clone = (Collection) super.clone();
		} catch (CloneNotSupportedException CNSE) {
			throw new Error("Impossible! instance cannot be cloned");
		}
		clone.collection = new SortedList<>();
		if(collection.setFirst()) {
			do {
				clone.collection.insert(collection.retrieve());
			} while (collection.getNext());
		}
		return clone;
	}

	public void insert(E data){     //unique check is done here
		if (!collection.find(data)) {
			collection.insert(data);
		}
	}

	public E get(){        //Returns the first element, same as Assignment 1
		if(collection.setFirst()) {
			return (E) collection.retrieve();
		}
		return null;
	}

	public void remove(E data) {
		if(collection.find(data)) {
			collection.remove();
		}
	}

	public int length() {
		return collection.size();
	}

	public boolean isEmpty(){
		return collection.isEmpty();
	}

	public boolean contains(E data){
		return this.collection.find(data);
	}

	public Collection findDifference(Collection collection) {
		if (collection.isEmpty()) {
			return this;
		}
		Collection output = this.clone();
		Collection clone=collection.clone();

		while (!clone.isEmpty()) {
			if (output.contains(clone.get())) {
				output.remove(clone.get());
			}
			clone.remove(clone.get());
		}
		return output;
	}

	public Collection findIntersection(Collection collection) {
		if (isEmpty() || collection.isEmpty()) {
			return new Collection();
		}

		Collection output = new Collection();
		Collection clone = new Collection(collection);

		while (!clone.isEmpty()) {
			if (contains((E) clone.get())) {
				output.insert(clone.get());
			}
			clone.remove(clone.get());
		}

		return output;
	}

	public Collection findUnion(Collection collection) throws APException{ //Possible pointer reset during insert()
		if (isEmpty()) { return collection.clone(); }
		if (collection.isEmpty()) { return this.clone(); }

		Collection output   = this.clone();
		Collection clone    = collection.clone();

		while(!clone.isEmpty()) {
			E e = (E) clone.get();
			clone.remove(e);
			output.insert(e);
		}

		return output;
	}

	public Collection findSymDiff(Collection collection) throws APException{
		if (isEmpty()) {
			return collection;
		}
		if (collection.isEmpty()) {
			return this;
		}

		Collection output = findUnion(collection);
		Collection intersection = findIntersection(collection);
		while (!intersection.isEmpty()) {
			output.remove(intersection.get());
			intersection.remove(intersection.get());
		}
		return output;
	}

	public String toString(){
		return collection.toString();
	}
}
