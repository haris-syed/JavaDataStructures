package Trees;

public class TreeNode <T> {
	
	private T value;
	private TreeNode <T> right;
	private TreeNode <T> left;
	
	
	public TreeNode(T value, TreeNode<T> right, TreeNode<T> left) {
		super();
		this.value = value;
		this.right = right;
		this.left = left;
	}
	
	

	public TreeNode(T value) {
		super();
		this.value = value;
		this.right=this.left=null;
	}



	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public TreeNode<T> getRight() {
		return right;
	}
	
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}
	
	public TreeNode<T> getLeft() {
		return left;
	}
	
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}
	
	
	
}
