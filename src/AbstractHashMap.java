import java.util.Random;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {
	protected int n =0; // number of entries in the dictionary
	protected int capacity;
	protected int prime;
	private long scale, shift;
	protected double loadfactor;

	public AbstractHashMap(int cap, int p) {
		prime = p;
		capacity = cap;
		Random rand = new Random();
		scale = rand.nextInt(prime - 1) + 1;
		shift = rand.nextInt(prime);
		loadfactor = 0.8;
		createTable();
	}

	public AbstractHashMap(int cap) {
		this(cap, 109345121);
	}

	public AbstractHashMap() {
		this(17);
	}

	public int size() {
		return n;
	}

	public V get(K key) {
		return bucketGet(hashValue(key), key);
	}

	public V remove(K key) {
		return bucketRemove(hashValue(key), key);
	}

	public int put(K key, V value) {
		return bucketPut(hashValue(key), key, value);
	}

	protected int hashValue(K key) {
		
		return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
		
	}

	
	protected abstract void resize(int newCap); 

	protected abstract void createTable();

	protected abstract V bucketGet(int h, K k);

	protected abstract int bucketPut(int h, K k, V v);

	protected abstract V bucketRemove(int h, K k);

}

