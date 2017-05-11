// Copyright (c) $year Yoyo Lam

package assignment2;

public class Identifier implements IdentifierInterface,Data {
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

    public void add(char c) {
        elements.append(c);
    }

    public char get(int i) {
        return elements.charAt(i);
    }

    public int length() {
        return elements.length();
    }

    public boolean equals(Identifier identifier) {
        if (elements.length() != identifier.length()) {
            return false;
        }
        for (int i = 0; i < elements.length(); i++) {
            if (get(i) != identifier.get(i)) {
                return false;
            }
        }
        return true;
    }

    public Identifier clone() {
        Identifier clone=new Identifier(elements.charAt(0));
        for (int i = 1; i < elements.length(); i++) {
             clone.add(elements.charAt(i));
        }
        return clone;
    }

    public int compareTo(Object o) {
        if (!(o instanceof Identifier)) {
            throw new Error("Type not Comparable");
        }
        //bare ASCII comparison
        for (int i = 0; i < Math.min(length(), ((Identifier) o).length()); i++) {
            if (get(i) > ((Identifier) o).get(i)) {
                return 1;
            } else if (get(i) < ((Identifier) o).get(i)) {
                return -1;
            }
        }
        if (length() > ((Identifier) o).length()) {
            return 1;
        }else if(length() < ((Identifier) o).length()){
            return -1;
        }
        return 0;
    }
}
