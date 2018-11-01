package Trees;

//SOURCE: https://www.baeldung.com/java-binary-tree

public class BinaryTree <T extends Comparable<? super T>> extends Tree<T> {
	
	
	public BinaryTree(){
		root=null;
		numOfValues=0;
	}
	
	
	private TreeNode<T> addRecursive(TreeNode<T> current, T value) {
	    if (current == null) {
	    	numOfValues++;
	        return new TreeNode<T>(value);
	    }
	 
	    if (current.getValue().compareTo(value) > 0) {
	        current.setLeft(addRecursive(current.getLeft(), value));
	    } else {
	        current.setRight(addRecursive(current.getRight(), value));
	    }
	    return current;
	}
	
	@Override
	public void insert(T value) {
		root=addRecursive(root, value);
	}

	private T findSmallestValue(TreeNode<T> root) {
	    return root.getLeft() == null ? root.getValue() : findSmallestValue(root.getLeft());
	}
	
	private TreeNode<T> deleteRecursive(TreeNode<T> current, T value) {
	    if (current == null) {
	        return null;
	    }
	 
	    if (((T)current.getValue()).compareTo(value)==0) {
	    	numOfValues--;
	    	if (current.getLeft() == null && current.getRight() == null) {
	    	    return null;
	    	}
	    	else if (current.getRight() == null) {
	    	    return current.getLeft();
	    	}
	    	 
	    	else if (current.getLeft() == null) {
	    	    return current.getRight();
	    	}
	    	else {
	    		T smallestValue = findSmallestValue(current.getRight());
	    		current.setValue(smallestValue);
	    		current.setRight(deleteRecursive(current.getRight(), smallestValue));
	    		return current;
	    	}
	    } 
	    if ((current.getValue()).compareTo(value) > 0) {
	        current.setLeft(deleteRecursive(current.getLeft(), value));
	        return current;
	    }
	    current.setRight(deleteRecursive(current.getRight(), value));
	    return current;
	}
	
	@Override
	public void remove(T value) {
		root=deleteRecursive(root, value);
		
	}
}
