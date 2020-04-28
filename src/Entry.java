public interface Entry<K,V> {

	K getKey();
	V getValue();
	LinkedList getList();
}
