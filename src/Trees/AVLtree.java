package Trees;


//SOURCE: https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
//SOURCE: https://www.geeksforgeeks.org/avl-tree-set-2-deletion/
public class AVLtree <T extends Comparable<? super T>> extends Tree<T> {

	@Override
	public void insert(T value) {
		root=insertHelper((AVLTreeNode<T>) root, value);
		
	}

	@Override
	public void remove(T value) {
		root=deleteNode((AVLTreeNode<T>)root, value);
		
	}

	// A utility function to get the height of the tree 
    int height(AVLTreeNode<T> N) { 
        if (N == null) 
            return 0; 
  
        return N.getHeight(); 
    } 
  
    // A utility function to get maximum of two integers 
    int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 
  
    // A utility function to right rotate subtree rooted with y 
    // See the diagram given above. 
    AVLTreeNode<T> rightRotate(AVLTreeNode<T> y) { 
        AVLTreeNode<T> x = (AVLTreeNode<T>) y.getLeft(); 
        AVLTreeNode<T> T2 = (AVLTreeNode<T>) x.getRight(); 
  
        // Perform rotation 
        x.setRight(y); 
        y.setLeft(T2); 
  
        // Update heights 
        y.setHeight(max(height((AVLTreeNode<T>)y.getLeft()), height((AVLTreeNode<T>)y.getRight())) + 1); 
        x.setHeight(max(height((AVLTreeNode<T>)x.getLeft()), height((AVLTreeNode<T>)x.getRight())) + 1); 
  
        // Return new root 
        return x; 
    } 
  
    // A utility function to left rotate subtree rooted with x 
    // See the diagram given above. 
    AVLTreeNode<T> leftRotate(AVLTreeNode<T> x) { 
        AVLTreeNode<T> y = (AVLTreeNode<T>) x.getRight(); 
        AVLTreeNode<T> T2 = (AVLTreeNode<T>) y.getLeft(); 
  
        // Perform rotation 
        y.setLeft(x); 
        x.setRight(T2); 
  
        //  Update heights 
        x.setHeight(max(height((AVLTreeNode<T>)x.getLeft()), height((AVLTreeNode<T>)x.getRight())) + 1); 
        y.setHeight(max(height((AVLTreeNode<T>)y.getLeft()), height((AVLTreeNode<T>)y.getRight())) + 1); 
  
        // Return new root 
        return y; 
    } 
  
    // Get Balance factor of node N 
    int getBalance(AVLTreeNode<T> N) { 
        if (N == null) 
            return 0; 
  
        return height((AVLTreeNode<T>)N.getLeft()) - height((AVLTreeNode<T>)N.getRight()); 
    } 
  
    private AVLTreeNode<T> insertHelper(AVLTreeNode<T> node, T value) { 
  
        /* 1.  Perform the normal BST insertion */
        if (node == null) {
        	numOfValues++;
            return (new AVLTreeNode<T>(value)); 
        }
        if (value.compareTo(node.getValue())<0) 
            node.setLeft(insertHelper((AVLTreeNode<T>)node.getLeft(), value)); 
        else if (value.compareTo(node.getValue())>0) 
            node.setRight(insertHelper((AVLTreeNode<T>)node.getRight(), value)); 
        else // Duplicate keys not allowed 
            return node; 
  
        /* 2. Update height of this ancestor node */
        node.setHeight(1 + max(height((AVLTreeNode<T>)node.getLeft()), 
                              height((AVLTreeNode<T>)node.getRight()))); 
  
        /* 3. Get the balance factor of this ancestor 
              node to check whether this node became 
              unbalanced */
        int balance = getBalance(node); 
  
        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && value.compareTo(node.getLeft().getValue()) < 0) 
            return rightRotate(node); 
  
        // Right Right Case 
        if (balance < -1 && value.compareTo(node.getRight().getValue())>0) 
            return leftRotate(node); 
  
        // Left Right Case 
        if (balance > 1 && value.compareTo(node.getLeft().getValue())>0) { 
            node.setLeft(leftRotate((AVLTreeNode<T>) node.getLeft())); 
            return rightRotate(node); 
        } 
  
        // Right Left Case 
        if (balance < -1 && value.compareTo(node.getRight().getValue())<0) { 
            node.setRight(rightRotate((AVLTreeNode<T>) node.getRight())); 
            return leftRotate(node); 
        } 
  
        /* return the (unchanged) node pointer */
        return node; 
    } 
    
    private AVLTreeNode<T>  minValueNode(AVLTreeNode<T>  node) 
    { 
    	AVLTreeNode<T>  current = node; 
  
        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null) 
           current = (AVLTreeNode<T>) current.getLeft(); 
  
        return current; 
    } 
    
    private AVLTreeNode<T> deleteNode(AVLTreeNode<T> root, T value) 
    { 
        // STEP 1: PERFORM STANDARD BST DELETE 
        if (root == null) 
            return root; 
  
        // If the value to be deleted is smaller than 
        // the root's value, then it lies in left subtree 
        if (value.compareTo(root.getValue()) < 0) 
            root.setLeft(deleteNode((AVLTreeNode<T>) root.getLeft(), value)); 
  
        // If the value to be deleted is greater than the 
        // root's value, then it lies in right subtree 
        else if (value.compareTo(root.getValue()) > 0) 
            root.setRight(deleteNode((AVLTreeNode<T>) root.getRight(), value)); 
  
        // if value is same as root's value, then this is the node 
        // to be deleted 
        else
        { 
  
        	numOfValues--;
            // node with only one child or no child 
            if ((root.getLeft() == null) || (root.getRight() == null)) 
            { 
                AVLTreeNode<T> temp = null; 
                if (temp == root.getLeft()) 
                    temp = (AVLTreeNode<T>) root.getRight(); 
                else
                    temp = (AVLTreeNode<T>) root.getLeft(); 
  
                // No child case 
                if (temp == null) 
                { 
                    temp = root; 
                    root = null; 
                } 
                else   // One child case 
                    root = temp; // Copy the contents of 
                                 // the non-empty child 
            } 
            else
            { 
  
                // node with two children: Get the inorder 
                // successor (smallest in the right subtree) 
                AVLTreeNode<T> temp = minValueNode((AVLTreeNode<T>) root.getRight()); 
  
                // Copy the inorder successor's data to this node 
                root.setValue(temp.getValue()); 
  
                // Delete the inorder successor 
                root.setRight(deleteNode((AVLTreeNode<T>) root.getRight(), temp.getValue())); 
            } 
        } 
  
        // If the tree had only one node then return 
        if (root == null) 
            return root; 
  
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE 
        root.setHeight(max(height((AVLTreeNode<T>) root.getLeft()), height((AVLTreeNode<T>) root.getRight())) + 1); 
  
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether 
        //  this node became unbalanced) 
        int balance = getBalance(root); 
  
        // If this node becomes unbalanced, then there are 4 cases 
        // Left Left Case 
        if (balance > 1 && getBalance((AVLTreeNode<T>) root.getLeft()) >= 0) 
            return rightRotate(root); 
  
        // Left Right Case 
        if (balance > 1 && getBalance((AVLTreeNode<T>) root.getLeft()) < 0) 
        { 
            root.setLeft(leftRotate((AVLTreeNode<T>) root.getLeft())); 
            return rightRotate(root); 
        } 
  
        // Right Right Case 
        if (balance < -1 && getBalance((AVLTreeNode<T>) root.getRight()) <= 0) 
            return leftRotate(root); 
  
        // Right Left Case 
        if (balance < -1 && getBalance((AVLTreeNode<T>) root.getRight()) > 0) 
        { 
            root.setRight(rightRotate((AVLTreeNode<T>) root.getRight())); 
            return leftRotate(root); 
        } 
  
        return root; 
    } 
}
