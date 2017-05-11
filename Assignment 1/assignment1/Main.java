package assignment1;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public PrintStream out;
    
    Main() {
        out = new PrintStream(System.out);
    }

    char nextChar(Scanner in) {
        return in.next().charAt(0);
    }

    boolean nextCharIs(Scanner in, char c) {
        return in.hasNext(Pattern.quote(c + ""));
    }

    boolean nextCharIsDigit(Scanner in) {
        return in.hasNext("[0-9]");
    }

    boolean nextCharIsLetter(Scanner in) {
        return in.hasNext("[a-zA-Z]");
    }

    boolean error(String message) {
        out.println(message);
        return false;
    }

    boolean validateCollection(String input) {
        Scanner in = new Scanner(input);
        in.useDelimiter("");
        while (nextCharIs(in, ' ')) {
            in.next();           //move pointer past character, saves 1 more method call than nextChar() (String.charAt())
        }
        if (!nextCharIs(in, '{')) {
            return error("Missing '{'");
        }
        in.next();
        while (in.hasNext()) {
            if (nextCharIs(in, ' ')) {
                in.next();
            } else if (nextCharIs(in, '}')) {
                return true;    //End of Collection
            } else {
                if (!validateIdentifier(in)) {
                    return false;
                }
            }
        }
        in.close();
        return error("Missing '}'");
    }

    boolean validateIdentifier(Scanner in) {
        if (!nextCharIsLetter(in)) {
            return error("An identifier begins with a letter");
        } else {
            in.next();
            while (!nextCharIs(in, ' ') && !nextCharIs(in, '}') && in.hasNext()) {
                if (!nextCharIsDigit(in) && !nextCharIsLetter(in)) {
                    return error("Only alphanumeric characters are allowed in an identifier.");
                }
                in.next();
            }
        }
        return true;
    }

	boolean isUniqueAndWithinSize(String rawCollection) {
		//A modification to readCollection that works
		Scanner in = new Scanner(rawCollection);
		int size = 0;
		in.useDelimiter("");
		in.next();            //Run the pointer through the {
		Collection collection = new Collection();
		while (in.hasNext()) {
			if (nextCharIs(in, ' ')) {
				in.next();
			} else if (nextCharIs(in, '}')) {
				return true;        //End of Collection
			} else {
				Identifier id = readIdentifier(in);
				if (collection.contains(id)) {
					return error("Identifiers in collections should be unique.");
				}
				//Size check is put here so cases which the input contain >10 identifiers but <10 UNIQUE identifiers can be covered
				//In other words, it's here to improve UX by giving a better sequence of error messages
				if (++size > Collection.MAX_INPUT_SIZE) {
					return error("The input may only contain a maximum of 10 elements.");
				}
				collection.add(id);
			}
		}
		return false;
	}

	Collection readCollection(String input) {
        Scanner in = new Scanner(input);
        in.useDelimiter("");
        in.next();            //Run the pointer through the {
        Collection collection = new Collection();
        while (in.hasNext()) {
            if (nextCharIs(in, ' ')) {
                in.next();
            } else if (nextCharIs(in, '}')) {
                break;        //End of Collection
            } else {
                collection.add(readIdentifier(in));
            }
        }
        in.close();
        return collection;
    }

    Identifier readIdentifier(Scanner in) {
        Identifier identifier = new Identifier(nextChar(in));
        while (nextCharIsLetter(in) || nextCharIsDigit(in)) {
            identifier.add(nextChar(in));
        }
        return identifier;
    }

    String collectionToString(Collection col) {
        StringBuffer output = new StringBuffer();
        Collection collection = new Collection(col);
        while (collection.length() > 0) {
            Identifier identifier = collection.get();
            for (int j = 0; j < identifier.length(); j++) {
                output.append(identifier.get(j));
            }
            output.append(" ");
            collection.remove(identifier);
        }
        return output.toString();
    }

    void printRelations(Collection first, Collection second) {
        try {
            out.printf("difference\t= %s\n", collectionToString(first.findDifference(second)));
            out.printf("intersection\t= %s\n", collectionToString(first.findIntersection(second)));
            out.printf("union\t\t= %s\n", collectionToString(first.findUnion(second)));
            out.printf("sym. diff.\t= %s\n", collectionToString(first.findSymDiff(second)));
        } catch (Exception e) {
            out.println("An error occurred: " + e.getMessage());
            out.print("Stack trace: \n" + e.getStackTrace());
            out.println();
        }
    }

    Collection getInput(Scanner in,String message){
	    String input;
	    do {
		    out.print(message);
		    if (!in.hasNextLine()) {
			    System.exit(0);
		    }
		    input = in.nextLine();
	    } while ((input.equals("") || !validateCollection(input) || !isUniqueAndWithinSize(input)));
    	return new Collection(readCollection(input));
    }

    void start(Scanner in){
	    Collection set1, set2;
    	while(true){
		    set1 = getInput(in, "Give the first collection: ");
		    set2 = getInput(in, "Give the second collection: ");
    		printRelations(set1,set2);
    	}
    }
    
    public static void main(String[] args) {
	    Scanner in=new Scanner(System.in);
	    new Main().start(in);
    }
}