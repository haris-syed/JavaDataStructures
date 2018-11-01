package Trees;

public class AVLTreeNode<T> extends TreeNode<T> {

	private int height;
	
	public AVLTreeNode(T value) {
		super(value);
		height=1;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
