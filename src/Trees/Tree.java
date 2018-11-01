package Trees;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import Interfaces.Mergeable;
import Interfaces.Modifiable;
import Interfaces.Traversable;

public abstract class Tree<T extends Comparable<? super T>> 
implements Mergeable<T>, Traversable,Modifiable<T>,Iterable<T> {
	
	protected TreeNode <T> root;
	
	protected int numOfValues;
	
	public abstract void insert(T value);
	
	public abstract void remove(T value);
	
	private boolean containsNodeRecursive(TreeNode<T> current, T value) {
	    if (current == null) {
	        return false;
	    } 
	    if (value.compareTo(current.getValue())==0) {
	        return true;
	    } 
	    return (current.getValue().compareTo(value) > 0)
	      ? containsNodeRecursive(current.getLeft(), value)
	      : containsNodeRecursive(current.getRight(), value);
	}
	
	
	public int getNumOfValues() {
		return numOfValues;
	}

	public void setNumOfValues(int numOfValues) {
		this.numOfValues = numOfValues;
	}

	public boolean search(T value) {
		return containsNodeRecursive(root, value);
	}


	
	@Override
	public void breadthFirstSearch() {
		if (root == null) {
	        return;
	    }
	 
	    Queue<TreeNode<T>> nodes = new LinkedList<>();
	    nodes.add(root);
	 
	    while (!nodes.isEmpty()) {
	 
	    	TreeNode<T> node = nodes.remove();
	 
	        System.out.print(" " + node.getValue().toString());
	 
	        if (node.getLeft() != null) {
	            nodes.add(node.getLeft());
	        }
	 
	        if (node.getRight()!= null) {
	            nodes.add(node.getRight());
	        }
	    }
	}

	
	private void inOrderHelper(TreeNode<T> node) {
	    if (node != null) {
	    	inOrderHelper(node.getLeft());
	        System.out.print(" " + node.getValue().toString());
	        inOrderHelper(node.getRight());
	    }
	}
	
	@Override
	public void inOrder() {
		inOrderHelper(root);
	}
	
	private void preOrderHelper(TreeNode<T> node) {
		if (node != null) {
	        System.out.print(" " + node.getValue().toString());
	        preOrderHelper(node.getLeft());
	        preOrderHelper(node.getRight());
	    }

	}
	
	@Override
	public void preOrder() {
		preOrderHelper(root);
	}
	
	private void postOrderHelper(TreeNode<T> node) {
		if (node != null) {
			postOrderHelper(node.getLeft());
			postOrderHelper(node.getRight());
	        System.out.print(" " + node.getValue().toString());
	    }

	}
	
	@Override
	public void postOrder() {
		postOrderHelper(root);
	}
	
	
	// A simple function to print leaf nodes of a binary tree 
	private void printLeaves(TreeNode<T> node)  
    { 
        if (node != null)  
        { 
            printLeaves(node.getLeft()); 
   
            // Print it if it is a leaf node 
            if (node.getLeft() == null && node.getRight() == null) 
                System.out.print(node.getValue() + " "); 
            printLeaves(node.getRight()); 
        } 
    } 
   
    // A function to print all left boundry nodes, except a leaf node. 
    // Print the nodes in TOP DOWN manner 
    private void printBoundaryLeft(TreeNode<T> node)  
    { 
        if (node != null)  
        { 
            if (node.getLeft() != null)  
            { 
                   
                // to ensure top down order, print the node 
                // before calling itself for left subtree 
                System.out.print(node.getValue() + " "); 
                printBoundaryLeft(node.getLeft()); 
            }  
            else if (node.getRight() != null)  
            { 
                System.out.print(node.getValue().toString() + " "); 
                printBoundaryLeft(node.getRight()); 
            } 
   
            // do nothing if it is a leaf node, this way we avoid 
            // duplicates in output 
        } 
    } 
   
    // A function to print all right boundry nodes, except a leaf node 
    // Print the nodes in BOTTOM UP manner 
    private void printBoundaryRight(TreeNode<T> node)  
    { 
        if (node != null)  
        { 
            if (node.getRight() != null)  
            { 
                // to ensure bottom up order, first call for right 
                //  subtree, then print this node 
                printBoundaryRight(node.getRight()); 
                System.out.print(node.getValue().toString() + " "); 
            }  
            else if (node.getLeft() != null)  
            { 
                printBoundaryRight(node.getLeft()); 
                System.out.print(node.getValue().toString() + " "); 
            } 
            // do nothing if it is a leaf node, this way we avoid 
            // duplicates in output 
        } 
    } 
   
    // A function to do boundary traversal of a given binary tree 
    private void printBoundary(TreeNode<T> node)  
    { 
        if (node != null)  
        { 
            System.out.print(node.getValue().toString() + " "); 
   
            // Print the left boundary in top-down manner. 
            printBoundaryLeft(node.getLeft()); 
   
            // Print all leaf nodes 
            printLeaves(node.getLeft()); 
            printLeaves(node.getRight()); 
   
            // Print the right boundary in bottom-up manner 
            printBoundaryRight(node.getRight()); 
        } 
    } 
    
	//SOURCE: https://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
	@Override
	public void boundaryTraversal() {
		printBoundary(root);
	}

	
	/* root - root of the binary tree 
    d -  distance of current line from rightmost 
         -topmost slope. 
    diagonalPrint - HashMap to store Diagonal 
                    elements (Passed by Reference) */
 static void diagonalPrintUtil(TreeNode root,int d, 
         HashMap<Integer,Vector<Integer>> diagonalPrint){ 
       
      // Base case 
     if (root == null) 
         return; 
       
     // get the list at the particular d value 
     Vector<Integer> k = diagonalPrint.get(d); 
       
     // k is null then create a vector and store the data 
     if (k == null) 
     { 
         k = new Vector<>(); 
         k.add((Integer) root.getValue()); 
     } 
       
     // k is not null then update the list 
     else
     { 
         k.add((Integer) root.getValue()); 
     } 
       
     // Store all nodes of same line together as a vector 
     diagonalPrint.put(d,k); 
       
     // Increase the vertical distance if left child 
     diagonalPrintUtil(root.getLeft(), d + 1, diagonalPrint); 
        
     // Vertical distance remains same for right child 
     diagonalPrintUtil(root.getRight(), d, diagonalPrint); 
 } 
   
 	// Print diagonal traversal of given binary tree 
 private void diagonalPrint(TreeNode<T> root) 
 { 
	 if(root == null || !(root.getValue() instanceof Integer)) return;
	 // create a map of vectors to store Diagonal elements 
	 HashMap<Integer,Vector<Integer>> diagonalPrint = new HashMap<>(); 
	 diagonalPrintUtil(root, 0, diagonalPrint); 

	 System.out.println("Diagonal Traversal of Binnary Tree"); 
	 for (Entry<Integer, Vector<Integer>> entry : diagonalPrint.entrySet()) 
	 { 
		 System.out.println(entry.getValue()); 
	 } 
 }

 	//SOURCE: https://www.geeksforgeeks.org/diagonal-traversal-of-binary-tree/
	@Override
	public void diagonalTraversal() {
		diagonalPrint(root);
		
	}

	@Override
	public void merge(Mergeable<T> ds) {
		Object [] array = ds.getValues();
		for(Object el:array) {
			this.insert((T)el);
		}
		
	}

	@Override
	public Object[] getValues() {
		Object [] array = new Object [this.numOfValues+1];
		int i=0;
		if (root == null) 
            return null; 
  
  
        Stack<TreeNode<T>> s = new Stack<TreeNode<T>>(); 
        TreeNode<T> curr = root; 
  
        // traverse the tree 
        while (curr != null || s.size() > 0) 
        { 
  
            /* Reach the left most TreeNode<T> of the 
            curr TreeNode<T> */
            while (curr !=  null) 
            { 
                /* place pointer to a tree node on 
                   the stack before traversing 
                  the node's left subtree */
                s.push(curr); 
                curr = curr.getLeft(); 
            } 
  
            /* Current must be NULL at this point */
            curr = s.pop(); 
            //System.out.println(curr.getValue());
            array[i]=curr.getValue();
            i++;
            /* we have visited the node and its 
               left subtree.  Now, it's right 
               subtree's turn */
            curr = curr.getRight(); 
        } 
        return array;
	}
	
	//SOURCE: https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/util/AbstractList.java
	private class Itr implements Iterator<T>{

		private Object[] array = Tree.this.getValues();
		private int current=0;
		private int lastRet=-1;
		
		@Override
		public boolean hasNext() {
			if(current <= array.length ) return true;
			return false;
		}

		@Override
		public T next() {
			if(current < array.length) {
				T value = (T)array[current];
				current++;
				return value;
			}
			return null;
		}
		
		public void remove() {
			if (lastRet < 0)
				return;

			Tree.this.remove((T)array[current--]);
			if (lastRet < current)
				current--;
			lastRet = -1;

		}
		
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}
	
	private class Enum implements Enumeration<T>{
		private Object[] array = Tree.this.getValues();
		private int current=0;
		private int lastRet=-1;
		
		@Override
		public boolean hasMoreElements() {
			if(current <= array.length ) return true;
			return false;
		}

		@Override
		public T nextElement() {
			if(current < array.length) {
				T value = (T)array[current];
				current++;
				return value;
			}
			return null;
		}
		
	}
	
	public Enumeration<T> elements(){
		return new Enum();
	}

}


