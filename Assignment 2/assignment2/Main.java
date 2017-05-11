package assignment2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	private PrintStream out;
	private Table<Identifier, Collection<NaturalNumber>> variables;

	//Known problem: cannot handle any spaces in between valid input (wtf no more space stripping solution)
	//Possible solution: Patches in places (assignment3.Identifier and Number) to reject spaces

	//Possible work: optimize the program when there is enough time
	//Known problem: time is never enough

	Main() {
		out = new PrintStream(System.out);
		variables = new Table<>();
	}

	char nextChar(Scanner in) {
		return in.next().charAt(0);
	}

	boolean nextCharIs(Scanner in, char c) {
		return in.hasNext(Pattern.quote(c + ""));
	}

	boolean nextCharIs(Scanner in, char... chars) {
		for (char c : chars) {
			if (in.hasNext(Pattern.quote(c + ""))) {
				return true;
			}
		}
		return false;
	}

	boolean nextCharIsDigit(Scanner in) {
		return in.hasNext("[0-9]");
	}

	boolean nextCharIsLetter(Scanner in) {
		return in.hasNext("[a-zA-Z]");
	}

	boolean hasIdentifier(Scanner in) {      //Can be improved
		return nextCharIsLetter(in);
	}

	void read(Scanner in, char c, String errorMessage) throws APException {
		//Expect character along with space trim
		trimSpaces(in);
		if (!nextCharIs(in, c)) {
			throw new APException("Character '"+c+"' expected. Detail(s): "+errorMessage);
		}
		in.next();
		trimSpaces(in);
	}

	void trimSpaces(Scanner in){
		while(nextCharIs(in,' ')){
			in.next();
		}
	}

	void isLineValid(Scanner in) throws APException {
		trimSpaces(in);
		if (in.hasNext()) {
			throw new APException("Invalid statement: End of line expected");
		}
	}

	void readPrintStatement(Scanner in) throws APException {
		read(in, '?', "A print statement starts with ?");
		String expression = collectionToString(readExpression(in));
		isLineValid(in);
		out.println(expression);
	}

	String collectionToString(Collection col) {
		StringBuffer output = new StringBuffer();
		Collection<NaturalNumber> collection = new Collection();
		collection = col.clone();
		while (collection.length() > 0) {
			NaturalNumber identifier = collection.get();
			for (int j = 0; j < identifier.length(); j++) {
				output.append(identifier.get(j));
			}
			output.append(" ");
			collection.remove(identifier);
		}
		return output.toString();
	}

	void readAssignment(Scanner in) throws APException {
		Identifier name = readIdentifier(in);
		read(in, '=', "An assignment starts with an identifier followed by '=' and an expression");
		Collection value = readExpression(in);
		isLineValid(in);
		variables.set(name, value);
	}

	Identifier readIdentifier(Scanner in) throws APException {
		Identifier identifier;
		if (!in.hasNext()) {
			throw new APException("Invalid Expression");
		}
		if (!nextCharIsLetter(in)) {
			throw new APException("An identifier begins with a letter");
		} else {
			identifier = new Identifier(nextChar(in));
			while (in.hasNext() && !nextCharIs(in, '+', '-', '*', '|', '=', ')')) {  //The ')' is a temporary fix for the readComplexFactor, not confirmed best fix
				if(nextCharIs(in,' ')){
					break;      //space in or after an identifier
				}
				if (!nextCharIsDigit(in) && !nextCharIsLetter(in)) {
					throw new APException("Only alphanumeric characters are allowed in an identifier");
				}
				identifier.add(nextChar(in));
			}
		}
		return identifier;
	}

	Collection readExpression(Scanner in) throws APException {
		Collection output = readTerm(in);       //Space trimmed by readTerm(), thanks!
		while (nextCharIs(in, '+', '-', '|')) {
			char operator = nextChar(in);
			Collection term2 = readTerm(in);    //Space trimmed by readTerm(), thanks!
			if (operator == '+') {
				output = output.findUnion(term2);
			} else if (operator == '-') {
				output = output.findDifference(term2);
			} else {  //operator=='|'
				output = output.findSymDiff(term2);
			}
			trimSpaces(in); //The space between an operator and the next factor
		}
		return output;
	}

	Collection readTerm(Scanner in) throws APException {
		Collection output = readFactor(in);
		trimSpaces(in);
		while (nextCharIs(in, '*')) {
			in.next();
			output = output.findIntersection(readFactor(in));
			trimSpaces(in); //The space between an operator and the next factor
		}
		return output;
	}

	Collection readFactor(Scanner in) throws APException {
		trimSpaces(in);
		if (hasIdentifier(in)) {
			return variables.get(readIdentifier(in));
		} else if (nextCharIs(in, '(')) {
			return readComplexFactor(in);
		} else if (nextCharIs(in, '{')) {
			return readCollection(in);
		}
		throw new APException("Invalid : A factor either starts with '(', or a collection or an identifier.");
	}

	Collection readComplexFactor(Scanner in) throws APException {
		read(in, '(', "A complex factor starts with a '('");
		Collection output = readExpression(in);
		read(in, ')', "A complex factor ends with a ')'");
		return output;
	}

	Collection readCollection(Scanner in) throws APException {
		read(in, '{',"A collection starts with a {");
		Collection col = readRowNaturalNumber(in);
		read(in, '}',"A collection ends with a }");
		return col;
	}

	Collection readRowNaturalNumber(Scanner in) throws APException{
		Collection collection = new Collection();
		if (nextCharIs(in, '}')) {
			return collection;
		}
		do {
			trimSpaces(in);
			collection.insert(readNaturalNumber(in));
			trimSpaces(in);
			if (nextCharIs(in, ',')) {
				in.next();
			}
		} while (!nextCharIs(in, '}') && in.hasNext());
		return collection;
	}

	NaturalNumber readNaturalNumber(Scanner in) throws APException{
		if(!nextCharIsDigit(in)){
			throw new APException("Invalid data, a set can only contain natural numbers");
		}
		char leadDigit=nextChar(in);
		if(leadDigit=='0' && nextCharIsDigit(in)){
			throw new APException("No leading zeros are allowed before a number");
		}
		NaturalNumber number = new NaturalNumber(leadDigit);
		while(in.hasNext()){
			while (nextCharIsDigit(in)) {
				number.add(nextChar(in));
			}
			trimSpaces(in); //Space between a number and end of set
			if (!nextCharIs(in, ',', '}')) {
				throw new APException("Invalid data, a set can only contain natural numbers");
			}else{
				return number;
			}
		}
		return number;
	}

	void parse(Scanner line) throws APException {
		if(line.hasNext()) {
			trimSpaces(line);
			if (nextCharIs(line, '/')) {
				return; //comment
			} else if (nextCharIs(line, '?')) {
				readPrintStatement(line);
			} else if (hasIdentifier(line)) {
				readAssignment(line);
			} else {
				throw new APException("Invalid statement. A statement starts with a '/', '?' or an identifier");
			}
		}else {
			throw new APException("A statement cannot be empty and starts with a '/', '?' or an identifier");
		}
	}

	void start(Scanner in) {
		while (in.hasNextLine()) {
			Scanner lineScanner = new Scanner(in.nextLine());
			lineScanner.useDelimiter("");
			try {
				parse(lineScanner);
			} catch (APException e) {
				out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		new Main().start(in);
	}
}