package assignment1;

/**
 * ADT for the collections.
 *
 * @author Yoyo and Yvan
 * @elements Identifiers
 * @structure non-linear
 * @domain all sets of identifiers with MAX_SIZE or fewer elements
 * @constructor Collection();
 * <dl>
 * <dt><b>PRE-condition</b><dd>-
 * <dt><b>POST-condition</b><dd>The new
 * empty Collection-object contains been initialized.
 * </dl>
 * <br>
 * Collection(Collection src);
 * <dl>
 * <dt><b>PRE-condition</b><dd>-
 * <dt><b>POST-condition</b><dd>
 * The collection src contains been copied.
 * </dl>
 */
public interface CollectionInterface {
    public static final int MAX_SIZE = 20;

    /**
     * Initializes the Collection object to empty.
     *
     * @precondition -
     * @postcondition An empty Collection object contains been initialized.
     */
    void init();


    /**
     * Adds a identifier to the collection.
     *
     * @precondition - length() < MAX_SIZE or elements is in the set.
     * @postcondition The element is in the set.
     */
    void add(Identifier element);


    /**
     * Returns whether the collection is empty.
     *
     * @precondition -
     * @postcondition true: the number of elements on the collection == 0.<br>
     * false: the amount of elements on the collection &gt; 0.
     */
    boolean isEmpty();


    /**
     * Returns the amount of elements of the collection.
     *
     * @precondition -
     * @postcondition The amount of elements of the collection contains been returned.
     */
    int length();

    /**
     * Returns an item from the collection
     *
     * @precondition The set  is not empty
     * @postcondition from the set an element contains been returned
     */
    Identifier get();

    /**
     * removes an element with an id from the set
     *
     * @precondition -
     * @postcondition The identifier id is not in the collection
     */
    void remove(Identifier id);

    /**
     * checks for an element in the collection
     *
     * @precondition -
     * @postcondition true:the element is in the collection
     * false:the element is not in the collection.
     */
    boolean contains(Identifier id);


    /**
     * Returns the difference of both sets
     *
     * @preconditions -
     * @postconditions The difference in both sets have been returned
     */
    Collection findDifference(Collection collection);

    /**
     * Returns the identifiers that are present in both collections
     *
     * @precondition -
     * @postcondition The identifiers that are present in both of the collections have been returned
     */
    Collection findIntersection(Collection collection);

    /**
     * Returns all the identifiers in both sets
     *
     * @precondition //Throw exception for <= 20 identifiers in new collection
     * @postcondition SUCCESS: All the identifiers that are present in both collections are returned
     * FAILURE:
     */
    Collection findUnion(Collection collection) throws Exception;

    /**
     * Returns the symmetric difference of both sets
     *
     * @precondition //Throw exception for <= identifiers in new collection
     * @postcondition The identifiers that are only present in of both collections have been returned
     */
    Collection findSymDiff(Collection collection) throws Exception;
}