package assignment3;

public class Identifier implements IdentifierInterface {
    private StringBuffer elements;

    public Identifier(char c) {
        init(c);
    }

    public Identifier(Identifier id) {
        init(id.get(0));
        for (int i = 1; i < id.length(); i++) {
            add(id.get(i));
        }
    }

    public void init(char c) {
        elements = new StringBuffer();
        add(c);
    }

    public Identifier add(char c) {
        elements.append(c);
	    return this;
    }

    public char get(int i) {
        return elements.charAt(i);
    }

    public int length() {
        return elements.length();
    }

    public Identifier clone() {
        Identifier clone=new Identifier(elements.charAt(0));
        for (int i = 1; i < elements.length(); i++) {
             clone.add(elements.charAt(i));
        }
        return clone;
    }

    public int compareTo(Object obj) {
        if (!(obj instanceof Identifier)) {
            throw new Error("Type not Comparable");
        }
//	    Uppercase > Lowercase > number
//	    0-9     48-57
//      A-Z 	65-90
//	    a-z     97-122
//      Pure ASCII comparison
		Identifier operand= (Identifier) obj;     //Code readability improvement
	    for (int i = 0; i < Math.min(length(), operand.length()); i++) {
			if(get(i)-operand.get(i)!=0){
				return get(i)-operand.get(i);
			}
        }
        if (length() > operand.length()) {
            return 1;
        }else if(length() < operand.length()){
            return -1;
        }
        return 0;
    }

	public String toString(){
		return elements.toString();
	}
}
