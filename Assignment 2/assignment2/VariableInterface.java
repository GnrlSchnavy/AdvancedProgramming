package assignment2;

public interface VariableInterface<K extends Data, V extends Clonable> extends Data{
    K getKey();

    V getValue();

    void setValue(V v);
}
