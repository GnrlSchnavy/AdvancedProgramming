package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;

public class sort {
	private static final int LOWERCASE_OFFSET =32;
	private static final String[] ARGUMENTS ={"-i","-d"};
	private boolean isCaseInsensitive;
	private boolean isDescending;
	private BinarySearchTree<Identifier> tree;
	public PrintStream out;

	sort(){
		tree=new BinarySearchTree<>();
		out=new PrintStream(System.out);
	}
	//An advanced version that returns according to isCaseInsensitive
	char nextChar(Scanner in) {
		char next=in.next().charAt(0);
		if(nextCharIsLetter(in) && isCaseInsensitive && next<91){
			return toLowerCase(next);
		}
		return next;
	}

	private char toLowerCase(char c) {
		return (char) (c + LOWERCASE_OFFSET);
	}

	boolean nextCharIsDigit(Scanner in) {
		return in.hasNext("[0-9]");
	}

	boolean nextCharIsLetter(Scanner in) {
		return in.hasNext("[a-zA-Z]");
	}

	boolean nextCharisAlphanumeric(Scanner in){
		return in.hasNext("[0-9a-zA-Z]");
	}

	boolean isArgument(String input){
		for(String argument: ARGUMENTS){
			if(input.equals(argument)){
				return true;
			}
		}
		return false;
	}

	boolean setFlag(String argument){       //return false only when a flag is already set
		if(argument.equals("-i") && !isCaseInsensitive){
			isCaseInsensitive=true;
			return true;
		}else if(argument.equals("-d") && !isDescending){
			isDescending=true;
			return true;
		}
		return false;
	}

	Scanner openFile(String path) {
		Scanner file;
		try {
			file = new Scanner(new File(path));
			return file;
		} catch (FileNotFoundException e) {
			out.println("File not found");
		}
		return null;
	}

	void parseFile(Scanner file){
		if(file!=null){
			while(file.hasNextLine()){
				parseLine(file.nextLine());
			}
		}
	}

	void parseLine(String line){
		Scanner in=new Scanner(line);
		in.useDelimiter("");
		while(in.hasNext()) {
			if(nextCharIsLetter(in)){
				Identifier id=readIdentifier(in);
				//out.println(id.toString());
				if (!tree.contains(id)) {
					tree.insert(id);
				} else {
					tree.delete(id);
				}
			}else if(nextCharIsDigit(in)){
				skipNonIdentifier(in);
			}else{
				in.next();
			}
		}
	}

	public Identifier readIdentifier(Scanner in){
		Identifier output;
		output = new Identifier(nextChar(in));
		while (in.hasNext()) {
			if (!nextCharisAlphanumeric(in)) {
				break;
			}
			output.add(nextChar(in));
		}
		return output;
	}

	void skipNonIdentifier(Scanner in){
		in.next();      //skip the first digit
		while(nextCharisAlphanumeric(in)){
			in.next();
		}
	}

	void printTree(){
		Iterator<Identifier> iterator;
		if(isDescending) {
			iterator=tree.descendingIterator();
		}else {
			iterator = tree.ascendingIterator();
		}
		while(iterator.hasNext()){
			Identifier id= iterator.next();
			for(int i=0;i<id.length();i++){
				out.print(id.get(i));
			}
			out.println();
		}
	}

	void start(String... args) {
		for(int i=0;i<args.length;i++) {
			if (isArgument(args[i]) && i < ARGUMENTS.length && setFlag(args[i])) {
				continue;
			}
			parseFile(openFile(args[i]));
		}
		printTree();
	}

	public static void main(String[] args){
		new sort().start(args);
	}
}
