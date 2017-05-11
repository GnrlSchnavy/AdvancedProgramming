package assignment1;

public class Collection implements CollectionInterface {
	public static final int MAX_INPUT_SIZE = MAX_SIZE/2;
    private Identifier[] identifiers;
    private int length;

    Collection() {
        init();
    }

    Collection(Collection collection) {
        init();
        int length = collection.length();
        for (int i = 0; i < length; i++) {
            add(new Identifier(collection.identifiers[i]));
        }
    }

    public void init() {
        identifiers = new Identifier[MAX_SIZE];
        length = 0;
    }

    public void add(Identifier element) {
        identifiers[length] = element;
        length++;
    }

    //Returns the first identifier
    public Identifier get() {
        return identifiers[0];
    }

    //Removes the first instance of id
    public void remove(Identifier id) {
        for (int i = 0; i < length; i++) {
            if (id.equals(identifiers[i])) {
                for (int j = i; j < length - 1; j++) {
                    identifiers[j] = new Identifier(identifiers[j + 1]);
                }
                length--;
            }
        }
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        if (length == 0) {
            return true;
        }
        return false;
    }

    public boolean contains(Identifier id) {
        Collection clone = new Collection(this);
        while (clone.length() > 0) {
            if (id.equals(clone.get())) {
                return true;
            }
            clone.remove(clone.get());
        }
        return false;
    }

    public Collection findDifference(Collection collection) {
        if (collection.isEmpty()) {
            return this;
        }
        Collection output = new Collection(this);
        Collection clone = new Collection(collection);

        while (clone.length() > 0) {
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

        while (clone.length() > 0) {
            if (contains(clone.get())) {
                output.add(clone.get());
            }
            clone.remove(clone.get());
        }
        return output;
    }

    public Collection findUnion(Collection collection) throws Exception {
        if (isEmpty()) {
            return collection;
        }
        if (collection.isEmpty()) {
            return this;
        }
        Collection output = new Collection(this);
        Collection clone = new Collection(collection);

        while (clone.length() > 0) {
            if (!contains(clone.get())) {
                if (output.length() >= MAX_SIZE) {
                    throw new Exception("The Collection contains reached its max size");
                }
                output.add(clone.get());
            }
            clone.remove(clone.get());
        }
        return output;
    }

    public Collection findSymDiff(Collection collection) throws Exception {
        if (isEmpty()) {
            return collection;
        }
        if (collection.isEmpty()) {
            return this;
        }
        Collection output = findUnion(collection);
        Collection intersection = findIntersection(collection);
        while (intersection.length() > 0) {
            output.remove(intersection.get());
            intersection.remove(intersection.get());
        }

        return output;
    }
}
