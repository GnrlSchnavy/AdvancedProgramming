package assignment2;

public class Variable<K extends Data,V extends Clonable> implements VariableInterface<K,V> {

    private K key;
    private V value;

    Variable(K k,V v){
        key=k;
        value=v;
    }

    public K getKey() {
        return key;
    }

    public V getValue(){
        return value;
    }

    public void setValue(V v){
        value=v;
    }

    public Variable clone() {
        Variable clone;
        try {
            clone = (Variable) super.clone();
        } catch (CloneNotSupportedException CNSE) {
            throw new Error("Impossible! instance cannot be cloned");
        }
        clone.key = key == null ? null : (K) key.clone();
        clone.value = value == null ? null : (V) value.clone();
        return clone;
    }

    public int compareTo(Object o) {
        if (o instanceof Variable) {
	        return key.compareTo(((Variable) o).getKey());
        }else if(o instanceof Identifier){
	        return key.compareTo(o);
        }
	    throw new Error("Type not Comparable");
    }

}
