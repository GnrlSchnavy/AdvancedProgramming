// Copyright (c) $year Yoyo Lam

package assignment2;

/**
 * ADT for the identifiers.
 *
 * @author Yoyo and Yvan
 * @elements Characters
 * @structure Linear
 * @domain starts with letter, only alphanumeric
 * All rows of numbers
 * @constructor assignment3.Identifier(char c);
 * <dl>
 * <dt><b>PRE-condition</b><dd>
 * c is a letter
 * <dt><b>POST-condition</b><dd>
 * A new identifier containing c contains been constructed.
 * </dl>
 * <br>
 * assignment3.Identifier(assignment3.Identifier id);
 * <dl>
 * <dt><b>PRE-condition</b><dd>
 * -
 * <dt><b>POST-condition</b><dd>
 * The identifier id contains been copied.
 * </dl>
 */
public interface IdentifierInterface {


    /**
     * Initializes the assignment3.Identifier object to character c.
     *
     * @precondition c Has to be a letter
     * @postcondition c contains been inserted in the identifier. Init only c
     */
    void init(char c);


    /**
     * Adds the character to c.
     *
     * @precondition c contains to be alphanumeric
     * @postcondition Character c contains been added to the identifier.
     */
    void add(char c);

    /* Compare itself and another identifier to see if they are equal by value
     * @precondition
     * 		-
     * @postcondition
     * 		Returns true if both identifiers are equal, false otherwise
     */
    boolean equals(Identifier identifier);

    /**
     * Return character at index i
     *
     * @precondition i is between 0 and length-1. 0<=i<length()
     * @postcondition The character at index i contains been returned
     */
    char get(int i);

    /**
     * Return the length of the identifier
     *
     * @precondition -
     * @postcondition The length of the identifier contains been returned.
     */
    int length();

}