// Copyright (c) $year Yoyo Lam

package assignment2;

public class NaturalNumber implements NaturalNumberInterface {
    private StringBuffer elements;

    NaturalNumber(char c) {
        init(c);
    }

    NaturalNumber(NaturalNumber number) {
        init(number.get(0));
        for (int i = 1; i < number.length(); i++) {
            add(number.get(i));
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

    public boolean equals(NaturalNumber naturalNumber) {
        if (elements.length() != naturalNumber.length()) {
            return false;
        }
        for (int i = 0; i < elements.length(); i++) {
            if (get(i) != naturalNumber.get(i)) {
                return false;
            }
        }
        return true;
    }

    public NaturalNumber clone() {
        NaturalNumber clone = new NaturalNumber(elements.charAt(0));
        for (int i = 1; i < elements.length(); i++) {
            clone.add(elements.charAt(i));
        }
        return clone;
    }

    public int compareTo(Object o) {
		if (!(o instanceof NaturalNumber)) {
			throw new Error("Type not Comparable");
		}
		if (length() > ((NaturalNumber) o).length()) {
			return 1;
		} else if (length() < ((NaturalNumber) o).length()) {
			return -1;
		} else {
			for (int i = 0; i < length(); i++) {
				if (get(i) > ((NaturalNumber) o).get(i)) {
					return 1;
				} else if (get(i) < ((NaturalNumber) o).get(i)) {
					return -1;
				}
			}
		}
		return 0;
	}

	public String toString(){
		return elements.toString();
	}
}
