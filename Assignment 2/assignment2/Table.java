package assignment2;

public class Table<K extends Data,V extends Clonable> {
	private SortedList<Variable> variables;
	Table(){
		variables=new SortedList<>();
	}

	public void set(K key,V value){
		if(find(key)){
			variables.remove();
		}
		Variable<K,V> variable=new Variable<>(key,value);
		variables.insert(variable);
	}

	public V get(K key) throws APException{
		if (find(key)) {
			return (V) variables.retrieve().getValue();
		}
		throw new APException("undefined");
	}

	private boolean find(K name){
		return variables.find(name);
	}
}
