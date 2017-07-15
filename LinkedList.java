/*A simple one way linked list class
 * that takes in any object to be used as 
 * a queue or stack
 * @author Deon Ma*/
public class LinkedList
{
	private int size;
	private Node head;
	public LinkedList() {
		this.size = 0;
		this.head = new Node(null);
	}
	public int getSize() {
		return size;
	}
	
	public void addFront(Object node) { 
		/*adds object to the from of list*/
		Node temp = this.head;
		this.head = new Node(node);
		this.head.setNext(temp);	
		size++;
	}
	public void add(Object node) { 
		/*adds object to the back of the list*/
		Node current = head;
		while(head.getNode() != null) {
			/*iterate until end of list*/
			head = head.getNext();
		}
		/*replace end null Node with new object*/
		head.setNode(node);
		head.setNext(new Node(null));
		head = current;
		size++;
	}
	public void insert(Object node, int pos) {
		/*insert object into index 'pos'*/
		if(pos >= size){
			System.out.println("Index out of range");
			return;
		}
		Node current = head;
		for(int i = 0; i != pos; ++i) {
		/*iterate to the given position*/
			head = head.getNext();
		}
		Node temp = new Node(head.getNode());
		temp.setNext(head.getNext());
		head.setNext(temp);
		head.setNode(node);
		head = current;
		size++;
	}
	
	public void remove(Object node) {
		/*remove given object from list*/
		Node current = head;
		while(head.getNode() != null) {
			if(head.getNode() == node) {
				Node temp = head.getNext();
				head.setNode(temp.getNode());
				head.setNext(temp.getNext());
				head = current;
				size--;
				return;
			}
			head = head.getNext();
		}
		
		System.out.println("node not found");
	}
	public Object remove(int pos) {
		/*remove object in index 'pos'*/
		if(pos >= size){
			System.out.println("Index out of range");
			return null;
		}
		Node current = head;
		for(int i = 0; i != pos; ++i) {
			head = head.getNext();
		}
		Object toReturn = head.getNode();
		Node temp = head.getNext();
		head.setNode(temp.getNode());
		head.setNext(temp.getNext());
		head = current;
		size--;
		return toReturn;
	}
	
	public void printLList() {
		/*print each element of the list*/
		Node temp = head;
		while(temp.getNode() != null) {
			System.out.println(temp.getNode().toString());
			temp = temp.getNext();
			
		}
	}
}
