/*
 * Represents a node in an AVL tree
 */
public class TreeNode
{
	private Integer node;
	private TreeNode left;
	private TreeNode right;
	public TreeNode() {
		this.node = null; 
	}
	public TreeNode(Integer node) {
		this.node = node; 
	}
	/*constructor given another tree node */
	public TreeNode(TreeNode node) {
		this.node = node.getNode(); 
		if(node.getLeft() != null){
			this.left = node.getLeft();
		}
		if(node.getRight() != null) {
			this.right = node.getRight();
		}
	}
	//getters
	public Integer getNode() {
		return this.node;
	}
	public TreeNode getLeft() {
		return this.left;
	}
	public TreeNode getRight() {
		return this.right;
	}	
	
	//setters
	public void setNode(Integer node) {
		this.node = node;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	
	
}
