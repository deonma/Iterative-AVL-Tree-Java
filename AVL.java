/*
 * Iterative AVL Tree where insert,delete operations are iterative
 * but calculating load balance is recursive
 * @author Deon Ma
 */
public class AVL //AVL Tree
{
	private int size;
	private TreeNode root;
	public AVL() {
		this.size = 0;
		this.root = new TreeNode();
	}
	public void insert(int value) { 
		/*enter new value in binary tree as a leaf and then balance*/
		TreeNode current = root;
		//iterate through tree
		while(root.getNode() != null) {
			if(value > root.getNode()) {
				root = root.getRight();
			}
			else if(value < root.getNode()) {
				root  = root.getLeft();
			}
			else { //do nothing if value in tree
				root = current;
				System.out.printf("Value: %d already in AVL tree!\n", value);
				return;
			}
		}
	    //add as leaf
		root.setNode(value);
		root.setLeft(new TreeNode());
		root.setRight(new TreeNode());
		root = current;//reset root
		
		balance();
	}
	public Integer delete(int value) {
		/*Binary Search for value in tree and calls
		 * removeNode on node if value is found. Returns
		 * value if found, otherwise returns null
		 */
		TreeNode current = root;
		int toReturn;
		//iterate through tree until value is found or until the end is reached
		while(root.getNode() != null) {
			if(root.getNode() > value ){
				if(root.getLeft().getNode() == value) {
					toReturn = removeNode(root.getLeft());
					root = current;
					return toReturn;
				}
				else {//continue
					root = root.getLeft();
				}
			}
			else if(root.getNode() < value) {
				if(root.getRight().getNode() == value){
					toReturn = removeNode(root.getRight());
					root = current;
					return toReturn;
				}
				else{//continue
					root = root.getRight();
				}
				
			}
			else {//remove the root
				toReturn = removeNode(root);
				root = current;
				return toReturn;
			}
		}
		root = current;//reset root
		return null;
	}
	private Integer removeNode(TreeNode toRemove) {
		/* If toRemove has no children, replace the node with null
		 * If toRemove has one child, replace toRemove with the child
		 * If toRemove has two children, iterate right once, and then
		 * 	to the farthest left child stopping before null is reached
		 * 	then replacing the value of toReturn with the value of that child, 
		 * 	and replaces that child with that child's right tree if any
		 * balances tree at the end
		 */
		size--;
		int toReturn = toRemove.getNode();
		if(toRemove.getRight().getNode() == null && toRemove.getLeft().getNode() == null){ //no children
			toRemove.setNode(null);
			toRemove.setLeft(null);
			toRemove.setRight(null);
		}
		else if(toRemove.getRight().getNode() != null && toRemove.getLeft().getNode() != null) {//2 children
			TreeNode current = toRemove;
			toRemove = toRemove.getRight();
			int value;
			while(toRemove.getLeft().getNode() != null) {
				toRemove = toRemove.getLeft();
			}
			value = toRemove.getNode();
			toRemove.setNode(toRemove.getRight().getNode());
			toRemove.setLeft(toRemove.getRight().getLeft());
			toRemove.setRight(toRemove.getRight().getRight());
			toRemove = current;
			toRemove.setNode(value);
			
		}
		//1 child
		else if(toRemove.getLeft().getNode() != null) { //if tree has left child
			toRemove.setNode(toRemove.getLeft().getNode());
			toRemove.setRight(toRemove.getLeft().getRight());
			toRemove.setLeft(toRemove.getLeft().getLeft());
		}
		else { //if tree has right child
			toRemove.setNode(toRemove.getRight().getNode());
			toRemove.setLeft(toRemove.getRight().getLeft());
			toRemove.setRight(toRemove.getRight().getRight());
		}
		balance();
		return toReturn;
		
	}
	private void balance() { //balances tree
		/*Cases:
		 * 1. if balance factor 0 do nothing and return
		 * 2. if balance factor is right heavy, move down right subtree
		 * 3.if balance factor is left heavy move down left subtree
		 * 4.if balance factor is greater than 1, do a left left rotate if balance factor 
		 * 	of right tree is right heavy, and right left rotate if left heavy
		 * 5.if balance factor is less than -1, do a right right rotate if balance factor
		 * 	of left tree is left heavy, and left right rotate if right heavy		 * 
		 */
		TreeNode current = root;
		while(root != null ){
			int bf = balanceFactor(root);
			if (bf == 0) { //if balance factor is 0 do nothing
				break;
			}
			else if (bf == 1) { //if right heavy move to right subtree
				root = root.getRight();
			}
			else if (bf == -1) { //if left heavy move to left subtree
				root = root.getLeft();
			}
			else if (bf == 2) { //if unbalanced and right heavy
				if(balanceFactor(root.getRight()) > 0) { //if right subtree is right heavy do ll rotate
					leftRotate(root);
				}
				else{ //if right subtree is left heavy do a rl rotate
					rlRotate(root);
				}
			}
			else if (bf == -2) {//if unbalanced and left heavy
				if(balanceFactor(root.getLeft()) < 0) { //if left subtree is left heavy do a rr rotate
					rightRotate(root);
				}
				else { //if left subtree is right heavy do a lr rotate
					lrRotate(root);
				}
			}
		}
		root = current; //reset root
		
	}
	private int balanceFactor(TreeNode node) {//calculate balance factor 
		return height(node.getRight()) - height(node.getLeft());
	}
	private void rightRotate(TreeNode node) {
		TreeNode toShift = new TreeNode(node);
		node.setNode(toShift.getLeft().getNode()); //substitute root node with left node
		node.setLeft(toShift.getLeft().getLeft()); // set left node of new root with its previous left node
		TreeNode temp = new TreeNode(toShift.getNode()); //set right node using old root
		temp.setLeft(toShift.getLeft().getRight()); //set new right node's left node with root's old right node
		temp.setRight(toShift.getRight()); //set new right node's right node with its previous right node
		node.setRight(temp);		
	}
	private void leftRotate(TreeNode node) {
		TreeNode toShift = new TreeNode(node);
		node.setNode(toShift.getRight().getNode()); //substitute root node with right node
		node.setRight(toShift.getRight().getRight());// set right node of new root with its previous right node
		TreeNode temp = new TreeNode(toShift.getNode()); //set left node using old root
		temp.setRight(toShift.getRight().getLeft()); //set new left node's right node with root's old left node
		temp.setLeft(toShift.getLeft()); //set new left node's left node with its previous left node
		node.setLeft(temp);		
	}
	
	private void lrRotate(TreeNode node){
		leftRotate(node.getLeft());
		rightRotate(node);
	}
	
	private void rlRotate(TreeNode node){
		rightRotate(node.getRight());
		leftRotate(node);
	}
	public int height(TreeNode node) {//calculate height of tree recursively
		if(node == null) {
			return -1;
		}
		else{
			return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
		}
	}
	//getters
	public int getSize() {
		return size;
	}
	public TreeNode getTree() {
		return this.root;
	}
	public void printTree(TreeNode node) { //print tree in breadth first search inorder traversal
		LinkedList queue = new LinkedList();
		TreeNode temp;
		queue.add(new TreeNode(root));
		while(queue.getSize() != 0) {
			temp = (TreeNode) queue.remove(0);
			System.out.print(temp.getNode());
			System.out.print("-");
			if(temp.getLeft() != null) {
				queue.add(temp.getLeft());
			}
			if(temp.getRight() != null) {
				queue.add(temp.getRight());
			}
			
		}
		
	}
}
