public class Node {
	//txt name
    private String directory;
    private int count;
    private Node link;
    
    public Node(String dataToAdd)
    {
    	setDirectory(dataToAdd);
    	//total word count for one txt.
    	count =0;
    	link = null;
    }
    
	
	public Node getLink() {
		return link;
	}
	public void setLink(Node link) {
		this.link = link;
	}


	public String getDirectory() {
		return directory;
	}


	public void setDirectory(String directory) {
		this.directory = directory;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}
    
}
