import java.util.Iterator;

import LinearStructures.*;
import Trees.*;

public class Main {


	public static void main(String[] args) {
		{
			System.out.println("\nSingly Linked list:\n****************\n");
			List <Integer> list=new List<Integer>(); //singly linked list
			int [] array= {7, 0, 2, 0, 5, 5, 4, 6, 4, 5};

			list.insert(5);
			for(int i=0;i<array.length;i++) {
				list.insert(array[i]); //insertion
			}

			//Iterator test
			System.out.println("\nIterator test");
			{
				Iterator<Integer> i =list.iterator();
				System.out.println(i.next());
				System.out.println(i.next());
				System.out.println(i.next());
				System.out.println(i.next());
				i.remove(); //removes 2
			}
			//TODO: enumerator test

			System.out.println(list.search(2)); //seraching by value 
			System.out.println("\nOriginal list:");
			list.printList();

			list.remove(5); //removing by value

			System.out.println("\nRemove 5:");
			list.printList();

			//sorting test
			System.out.println("\nSorting test:");
			list.bubbleSort();
			list.printList();
			list.printList();
			list.printList();
			list.insertionSort();
			list.printList();
			list.quickSort();
			list.printList();
			list.mergeSort();
			list.printList();
			list.countingSort();
			list.printList();
		}
		
		{
			System.out.println("\nCircular list:\n*****************\n");
			CircularList<Integer> list=new CircularList<Integer>();
			int [] array= {7, 0, 2, 0, 5, 5, 4, 6, 4, 5};

			list.insert(5);
			for(int i=0;i<array.length;i++) {
				list.insert(array[i]); //insertion
			}

			//Iterator test
			System.out.println("\nIterator test");
			{
				Iterator<Integer> i =list.iterator();
				System.out.println(i.next());
				System.out.println(i.next());
				System.out.println(i.next());
				System.out.println(i.next());
				i.remove(); //removes 2
			}
			//TODO: enumerator test

			System.out.println(list.search(2)); //seraching by value 
			System.out.println("\nOriginal list:");
			list.printList();

			list.remove(5); //removing by value

			System.out.println("\nRemove 5:");
			list.printList();

			//sorting test
			System.out.println("\nSorting test:");
			list.bubbleSort();
			list.printList();
			list.printList();
			list.printList();
			list.insertionSort();
			list.printList();
			list.quickSort();
			list.printList();
			list.mergeSort();
			list.printList();
			list.countingSort();
			list.printList();
		}
		
		{
			System.out.println("\nDoubly linked list:\n*****************\n");
			DoublyLinkedList<Integer> list=new DoublyLinkedList<Integer>();
			int [] array= {7, 0, 2, 0, 5, 5, 4, 6, 4, 5};

			list.insert(5);
			for(int i=0;i<array.length;i++) {
				list.insert(array[i]); //insertion
			}

			//Iterator test
			System.out.println("\nIterator test");
			{
				Iterator<Integer> i =list.iterator();
				System.out.println(i.next());
				System.out.println(i.next());
				System.out.println(i.next());
				System.out.println(i.next());
				i.remove(); //removes 2
			}
			//TODO: enumerator test

			System.out.println(list.search(2)); //seraching by value 
			System.out.println("\nOriginal list:");
			list.printList();

			list.remove(5); //removing by value
			System.out.println("\nRemove 5:");
			list.printList();

			//sorting test
			System.out.println("\nSorting test:");
			list.bubbleSort();
			list.printList();
			list.printList();
			list.printList();
			list.insertionSort();
			list.printList();
			list.quickSort();
			list.printList();
			list.mergeSort();
			list.printList();
			list.countingSort();
			list.printList();
		}
		
		{
			System.out.println("\nStack:\n*****************\n");
			Stack<Integer> list=new Stack<Integer>();
			int [] array= {7, 0, 2, 0, 5, 5, 4, 6, 4, 5};

			list.push(5);
			for(int i=0;i<array.length;i++) {
				list.push(array[i]); //insertion
			}
			System.out.println("Popping: ");
			System.out.print(list.pop()+" ");
			System.out.print(list.pop()+" ");
			System.out.print(list.pop()+" ");
			System.out.print(list.pop()+" ");
		}
		{
			{
				System.out.println("\n\nQueue:\n*****************\n");
				Queue<Integer> list=new Queue<Integer>();
				int [] array= {7, 0, 2, 0, 5, 5, 4, 6, 4, 5};
				list.enqueue(5);
				for(int i=0;i<array.length;i++) {
					list.enqueue(array[i]); //insertion
				}
				System.out.println("Dequeing: ");
				System.out.print(list.deqeue()+" ");
				System.out.print(list.deqeue()+" ");
				System.out.print(list.deqeue()+" ");
				System.out.print(list.deqeue()+" ");
			}
		}
		
		{
			System.out.println("\n\n\nBinary Tree:\n*****************\n");
			BinaryTree<Integer> tree = new BinaryTree<Integer>();
			for(int i=0;i<10;i++) {
				tree.insert((int)(Math.random()*10)); //insertion
			}
			System.out.println("\nIterator test");
			{
				Iterator<Integer> i =tree.iterator();
				System.out.println(i.next());
				System.out.println(i.next());
				System.out.println(i.next());
				System.out.println(i.next());
				i.remove(); //removes item in inOrder order
			}
			//TODO: enumerator test

			System.out.println("Inorder");
			tree.inOrder();
			System.out.println("\npostOrder");
			tree.postOrder();
			System.out.println("\npreOrder");
			tree.preOrder();
			System.out.println("\ndiagonal traveral:");
			tree.diagonalTraversal();
			System.out.println("\nBFS:");
			tree.breadthFirstSearch();
			System.out.print("\nSearch 5:");
			System.out.print(tree.search(5));
			System.out.print("\nremove 5:");
			tree.remove(5);
			tree.inOrder();
		}
		
		{
			System.out.println("\n\n\nAVL Tree:\n*****************\n");
			AVLtree<Integer> tree = new AVLtree<Integer>();
			for(int i=0;i<10;i++) {
				tree.insert((int)(Math.random()*10)); //insertion
			}
			System.out.println("\nIterator test");
			{
				Iterator<Integer> i =tree.iterator();
				System.out.println(i.next());
				System.out.println(i.next());
				System.out.println(i.next());
				System.out.println(i.next());
				i.remove(); //removes item in inOrder order
			}
			//TODO: enumerator test

			System.out.println("Inorder");
			tree.inOrder();
			System.out.println("\npostOrder");
			tree.postOrder();
			System.out.println("\npreOrder");
			tree.preOrder();
			System.out.println("\ndiagonal traveral:");
			tree.diagonalTraversal();
			System.out.println("\nBFS:");
			tree.breadthFirstSearch();
			System.out.print("\nSearch 5:");
			System.out.print(tree.search(5));
			System.out.print("\nremove 5:");
			tree.remove(5);
			tree.inOrder();
		}
	}

}
