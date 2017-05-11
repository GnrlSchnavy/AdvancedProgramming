package assignment3;

/**
 * Created by user on 16/10/2014.
 */
public interface IdentifierInterface extends Data {
	void init(char c);

	Identifier add(char c);

	char get(int i);

	int length();

	int compareTo(Object obj);

	String toString();
}
