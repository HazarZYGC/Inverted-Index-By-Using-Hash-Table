public class LinkedList {
    //first node of list.
	private Node head;
	private int total;
	
	//get and set for head
	public Node getHead() {
		return head;
	}
	public void setHead(Node head) {
		this.head = head;
	}  
	
	//contructor
	public LinkedList() {
		this.head = null;
		setTotal(0);
	}
	//
	
	
	//adding new element.
	public void add(String dataToAdd) {
		//adding first element.
		if(head == null)
		{
			//for first txt name.
			//create newnode...
			Node newnode = new Node(dataToAdd);
			head = newnode;
			head.setCount(head.getCount()+1);
			//increase total txt counter..
			setTotal(getTotal() + 1);
		}
		
		
		else 
		{	
			
			Node temp = head;
			Node previous = new Node(null);
			while(temp != null && !dataToAdd.equalsIgnoreCase(temp.getDirectory()))
			{
				//travelling the linkedlist.
				previous = temp;
				temp = temp.getLink();
			}
			//if this txt name is first for linkedlist.
			if(temp==null)
			{
				Node newnode = new Node(dataToAdd);
				previous.setLink(newnode);
				newnode.setCount(newnode.getCount()+1);
				//adding first count.
				setTotal(getTotal() + 1);
				//increase total count.
			}
			//
			else
				//if this txt name is not first for linkedlist.
			{
				previous = temp;
				temp = temp.getLink();
				//increasing count for this txt.
				previous.setCount(previous.getCount()+1);
				//increase total.
				setTotal(getTotal() + 1);
			}
		}
		//else
	}
	//
	
	
	
	

	
	
	
	
	//display list.
	public void display() {
		if(head == null)
			//System.out.println("linked-list is empty");
			System.out.println();
		else
		{
			Node temp = head;
			while(temp!=null) 
			{
				System.out.print(temp.getDirectory() + "  -  ");
				System.out.println(temp.getCount());
				temp = temp.getLink();
			}
			//while
		}
		//else
	}
	//
	
	
	
	
	//
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
