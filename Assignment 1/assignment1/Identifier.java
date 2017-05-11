package assignment1;

public class Identifier implements IdentifierInterface {
    private StringBuffer elements;

    Identifier(char c) {
        init(c);
    }

    Identifier(Identifier id) {
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
}
