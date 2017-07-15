/*repersents a node in the LinkedList
 * @author Deon Ma
 */
public class Node
{
	private Object node;
	private Node next;
	public Node(Object node) {
		this.node = node;
	}
	//getters
	Object getNode() {
		return node;
	}
	Node getNext() {
		return next;
	}
	//setters
	void setNext(Node next) {
		this.next = next;
	}
	void setNode(Object node) {
		this.node = node;
	}
}
