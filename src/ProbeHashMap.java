import java.util.ArrayList;

public class ProbeHashMap<K,V> extends AbstractHashMap<K,V>{
	 
	 private MapEntry<K,V>[]table;
	 private MapEntry<K,V> DEFUNCT = new MapEntry<>(null, null);
	 //for collision:
	 static long counter = 0;
	 //unique word counter:
	 static int uniqueCounter = 0;
	 //not found words in searching:
	 static int notfound=0;
	 
	 
	 //constructors
	 public ProbeHashMap() {
		 super();
	 }
	 public ProbeHashMap(int cap) {
		 super(cap);
	 }
	 public ProbeHashMap(int cap, int p) {
		 super(cap,p);
	 }
	 //
	 //
	 private boolean isAvailable(int j) {
		 return(table[j]==null||table[j]==DEFUNCT);
	 }

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		for (int h = 0; h < capacity; h++) {
			if(!isAvailable(h)) {
				buffer.add(table[h]);
			}
		}
		return buffer;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createTable() {
		table = (MapEntry<K,V>[]) new MapEntry[capacity]; 
		}
	
	protected int findSlot(int h,K k) {
		int avail = -1;
		int j = h;
		do {
			if(isAvailable(j)) {
				if(avail==-1)avail=j;
				if(table[j]==null) {
					uniqueCounter++;
					break;
				}
				}
				else if(table[j].getKey().equals(k)) {
					
					return j;
				}
			j=(j+1) % capacity;
			counter++;
		}while(j != h);
		return -(avail+1);
	}

	@Override
	protected V bucketGet(int h, K k) {
		int j = findSlot(h,k);
		if(j<0)return null;
		return table[j].getValue();
	}

	@Override
	protected int bucketPut(int h, K k, V v) {
		
		int j = findSlot(h,k);
		if(j>=0) {
			table[j].getList().add(Main.src);
			return j;
		}
		n++;
		if((double)n/capacity>loadfactor) {
			System.out.println("Size of elements is above of the "+ loadfactor+": New size :" + newCap(capacity)+ "!!!!!" );
			
			resize(newCap(capacity));
			
		}
		table[-(j+1)] = new MapEntry<>(k,v);
		table[-(j+1)].getList().add(Main.src);
		
		
		return -(j+1);
	}

	@Override
	protected V bucketRemove(int h, K k) {
		int j = findSlot(h,k);
		if(j<0)return null;
		V answer = table[j].getValue();
		table[j]=DEFUNCT;
		n--;
		return answer;
	}
	
	@Override
	protected int hashValue(K key) {
	
	//   return Summation((String)key)%capacity;
		int hash =  Polynomial((String)key);
		hash = hash%capacity;
		if(hash<0) {
			hash = hash + capacity;
		}
		return hash;
	}
	
	protected int Summation(String key) {
		
		String letters = "abcdefghijklmnopqrstuvwxyz";
		int i =0;
		int sum =0;
		char lettersChar[] = letters.toCharArray();
		char wordChar[] = key.toCharArray();
		for (int j = 0; j < wordChar.length; j++) {
			i=0;
			for (int k = 0; k < lettersChar.length; k++) {
				if(lettersChar[k] == wordChar[j]) {
					i++;
					break;
				}
				else
					i++;
			}
			sum += i;
		}
		return sum;
		
		

		
	}
	
	protected int Polynomial(String word) {
		
		String letters = "abcdefghijklmnopqrstuvwxyz";
		int i =0;
		int sum =0;
		int factor = 33;
		String reverse = new StringBuffer(word).reverse().toString();
		char lettersChar[] = letters.toCharArray();
		char wordChar[] = reverse.toCharArray();
		for (int j = 0; j < wordChar.length; j++) {
			i=0;
			for (int k = 0; k < lettersChar.length; k++) {
				if(lettersChar[k] == wordChar[j]) {
					i++;
					break;
				}
				else
					i++;
			}
			sum = sum*factor;
			sum += i;
		}
		
		return sum;
		

	}
	
	protected int newCap(int number) {
		number = number*2;
		number++;
		boolean flag = false;
		boolean controller = true;
		while(flag==false) {
			for (int i = 2; i < number/2; i++) {
				if(number%i==0) {
					controller = false;
					break;
				}
			}
			if(controller==false) {
				number++;
				controller = true;
			}
			else flag=true;
		}
		return number;
	}
	
	@Override
	protected void resize(int newCap) {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>(n);
		for (Entry<K, V> e : entrySet()) {
			buffer.add(e);
		}
		capacity = newCap;
		createTable(); // based on updated capacity
		n = 0; // will be recomputed while reinserting entries
		uniqueCounter = 0;
		int hash = 0;
		for (Entry<K, V> e : buffer) {
			hash = put(e.getKey(), e.getValue());
			//
			//
			//part of updating the linked lists.
			if(hash<0) {
				hash=-(hash+1);
			}
			if(table[hash] != null) {
			table[hash].setList(e.getList());
			}
			//
			//
		}
	}
	
	//searching word method..
	protected void searchingWord(K word) {
		int a = findSlot(hashValue(word),word);
		
		//controlling the index for - numbers. (findSlot return value)
		if(a<0) {
			a = -(a+1);
		}
		
		try {
			System.out.println("Word : "+table[a].getKey());
			System.out.println();
			table[a].getList().display();
			System.out.println();
			System.out.println("Total word count : "+table[a].getList().getTotal());
			System.out.println("------------------------------------");
		
		}
		//try
		catch(NullPointerException e){
			notfound++;
			System.out.println();
			System.out.println();
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("-------ERROR-------");
			System.out.println(word + " is not existed in hash table.");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println();
			System.out.println();
		}
		//catch
	}
	
	public MapEntry<K, V>[] getTable() {
		return table;
	}
	public void setTable(MapEntry<K, V>[] table) {
		this.table = table;
	}
	
	
 }
		

	 
	 

