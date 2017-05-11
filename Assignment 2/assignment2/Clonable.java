package assignment2;

/*
 The programmer who defines a class implementing this interface
 contains the responsibility to define clone so it makes a deep copy
 (in the officially sectioned way.)
*/

public interface Clonable extends Cloneable {

    /* In order to be able to use clone() everywhere is is overridden with
       a public version.
    */

    public Object clone();

}
