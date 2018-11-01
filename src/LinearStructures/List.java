package LinearStructures;

import java.util.Enumeration;
import java.util.Iterator;

import Interfaces.Mergeable;
import Interfaces.Modifiable;
import Interfaces.Sortable;

public class List<T extends Comparable<? super T>> implements Sortable, Mergeable<T>, Modifiable<T>, Iterable<T> {

	protected Node <T> head;
	protected int size;

	public List() {
		this.head=null;
		this.size=0;
	}

	@Override
	public  void insert(T value) {
		Node <T> current = head;
		Node <T> newNode=new Node<T>(value);
		if(head == null) {
			head=newNode;
		}
		else {
			while (current.getNext() != null) {
				current = current.getNext(); // we'll loop until current.next is null
			}
			current.setNext(newNode);
		}
		size++;
	}

	@Override
	public  void remove(T value) {
		if(head==null) {
			return;
		}
		if(head.getNext()==null) {
			if(head.getValue()==value) {
				head=null;
				size--;
			}
			return;
		}
		if(head.getValue()==value) {
			head=head.getNext();
			size--;
			return;
		}

		Node <T> current=head;
		Node<T> prev = null;
		while(current != null && current.getValue() != value) {
			prev=current;
			current=current.getNext();
		}
		if(current==null) {
			return;
		}
		else {
			prev.setNext(current.getNext());
			size--;
		}	
	}

	public T removeFirst() {
		if(head==null) return null;
		T value=head.getValue();
		head=head.getNext();
		size--;
		return value;
	}

	public T removeLast() {
		if(head==null) return null; //if no value
		Node <T> current = head;
		if(head.getNext()==null) { //if only one value
			T value=head.getValue();
			head=null;
			return value;
		}
		while (current.getNext().getNext() != null) { 
			current = current.getNext(); // we'll loop until current.next is null
		}
		T value=current.getNext().getValue();
		current.setNext(null);
		return value;
	}

	public T get(int i) {
		if(i >= size) return null;
		else {
			Node<T> current=head;
			for(int j=0;j<i;j++) {
				current=current.getNext();
			}
			return current.getValue();
		}
	}

	public int size() {
		return size;
	}

	private void rebuildList(Object[] arr) {
		this.head=null;//delete previous list
		this.size=0;
		for(int i=0;i<arr.length;i++) {
			this.insert((T)arr[i]);
		}
	}


	//returns index of searched item, -1 if not found
	public  int search(T value) {
		if(head!=null) {
			Node<T> current=head;
			int i=0;
			while(current!=null && current.getValue()!=value) {
				i++;
				current=current.getNext();
			}
			if(i >= size) return -1;
			return i;
		}
		return -1;
	}

	public void printList() {
		if(head!=null) {
			Node<T> current=head;
			while(current!=null) {
				System.out.print(current.getValue().toString()+" ");
				current=current.getNext();
			}
			System.out.println();
		}	
	}

	//SOURCE: https://www.javatpoint.com/bubble-sort-in-java
	@Override
	public void bubbleSort() {
		Object [] values=getValues();
		int n = values.length;  
		T temp = null;  
		for(int i=0; i < n; i++){  
			for(int j=1; j < (n-i); j++){  
				if(((T)values[j-1]).compareTo((T)values[j]) < 0){  
					//swap elements  
					temp = (T)values[j-1];  
					values[j-1] = values[j];  
					values[j] = temp;  
				}  
			}  
		} 
		rebuildList(values);

	}

	//SOURCE: https://www.geeksforgeeks.org/insertion-sort/
	@Override
	public void insertionSort() {
		Object [] arr=this.getValues();
		int n = arr.length;
		for (int i=1; i<n; ++i)
		{
			T key = (T)arr[i];
			int j = i-1;

			/* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
			while (j>=0 && ((T)arr[j]).compareTo(key) < 0)
			{
				arr[j+1] = arr[j];
				j = j-1;
			}
			arr[j+1] = key;
		}

	}


	private int partition(Object arr[], int low, int high) 
	{ 
		T pivot = (T)arr[high];  
		int i = (low-1); // index of smaller element 
		for (int j=low; j<high; j++) 
		{ 
			// If current element is smaller than or 
			// equal to pivot
			int comparison = ((T)arr[j]).compareTo(pivot);
			if (comparison <= 0) 
			{ 
				i++; 

				// swap arr[i] and arr[j] 
				T temp = (T)arr[i]; 
				arr[i] = arr[j]; 
				arr[j] = temp; 
			} 
		} 

		// swap arr[i+1] and arr[high] (or pivot) 
		T temp = (T)arr[i+1]; 
		arr[i+1] = arr[high]; 
		arr[high] = temp; 

		return i+1; 
	} 


	private void quickSortHelper(Object [] arr, int low, int high) 
	{ 
		if (low < high) 
		{ 
			/* pi is partitioning index, arr[pi] is  
              now at right place */
			int pi = partition(arr, low, high); 

			// Recursively sort elements before 
			// partition and after partition 
			quickSortHelper(arr, low, pi-1); 
			quickSortHelper(arr, pi+1, high); 
		} 
	} 

	//SOURCE: https://www.geeksforgeeks.org/quick-sort/
	@Override
	public void quickSort() {
		Object [] arr=this.getValues();
		int low=0;
		int high=arr.length-1;
		quickSortHelper(arr, low, high);

		rebuildList(arr);
	}


	private void merge(Object [] arr, int l, int m, int r) 
	{ 
		// Find sizes of two subarrays to be merged 
		int n1 = m - l + 1; 
		int n2 = r - m; 

		/* Create temp arrays */
		Object L[] = new Object [n1]; 
		Object R[] = new Object [n2]; 

		/*Copy data to temp arrays*/
		for (int i=0; i<n1; ++i) 
			L[i] = arr[l + i]; 
		for (int j=0; j<n2; ++j) 
			R[j] = arr[m + 1+ j]; 


		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays 
		int i = 0, j = 0; 

		// Initial index of merged subarry array 
		int k = l; 
		while (i < n1 && j < n2) 
		{ 
			int comparison = ((T)L[i]).compareTo((T)R[j]);
			if (comparison <= 0) 
			{ 
				arr[k] = L[i]; 
				i++; 
			} 
			else
			{ 
				arr[k] = R[j]; 
				j++; 
			} 
			k++; 
		} 

		/* Copy remaining elements of L[] if any */
		while (i < n1) 
		{ 
			arr[k] = L[i]; 
			i++; 
			k++; 
		} 

		/* Copy remaining elements of R[] if any */
		while (j < n2) 
		{ 
			arr[k] = R[j]; 
			j++; 
			k++; 
		} 
	} 

	// Main function that sorts arr[l..r] using 
	// merge() 
	private void mergeSortHelper(Object arr[], int l, int r) 
	{ 
		if (l < r) 
		{ 
			// Find the middle point 
			int m = (l+r)/2; 

			// Sort first and second halves 
			mergeSortHelper(arr, l, m); 
			mergeSortHelper(arr , m+1, r); 

			// Merge the sorted halves 
			merge(arr, l, m, r); 
		} 
	} 

	//SOURCE: https://www.geeksforgeeks.org/merge-sort/
	@Override
	public void mergeSort() {
		Object [] arr= this.getValues();
		mergeSortHelper(arr, 0, arr.length-1);
		rebuildList(arr);

	}

	private void countingSortHelper( Object[] input,int n)

	{
		if(!(input[0] instanceof Integer)) {
			return; //if cannot be cast to Integer then return (because range cannot be calculated)
		}
		int min=0,max=0;
		for (int i = 0; i < n; i++)
		{
			if ((Integer)input[i] > max)
				max = (Integer)input[i];
			if ((Integer)input[i] < min)
				min = (Integer)input[i];
		}
		int range = max - min + 1;
		int[] count = new int[range];
		//counts frequencies for each element
		for (int i = 0; i < n; i++)
			count[(Integer)input[i] - min]++;
		// getting positions in final array
		for (int i = 1; i < range; i++)
			count[i] += count[i - 1];
		//copy to output array, preserving order of inputs with equal keys
		int j = 0;
		for (int i = 0; i < range; i++)
			while (j < count[i])
				input[j++] = i + min;
	}    

	//SOURCE: http://www.topjavatutorial.com/java/java-programs/counting-sort-in-java/
	@Override
	public void countingSort( ) {
		Object [] arr = this.getValues();
		countingSortHelper(arr, arr.length);
		rebuildList(arr);
	}

	@Override
	public void merge(Mergeable<T> ds) {
		Object [] array = ds.getValues();
		for(Object el:array) {
			this.insert((T)el);
		}
	};

	@Override
	public Object [] getValues() {
		if(this.size>0) {
			Node<T> current=head;
			Object[] array = new Object[this.size];
			int i=0;
			while(current!=null) {
				array[i]=current.getValue();
				i++;
				current=current.getNext();
			}
			return array;
		}
		return null;
	};

	
	//SOURCE: https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/util/AbstractList.java
	private class Itr implements Iterator<T> {
		/**
		 * Index of element to be returned by subsequent call to next.
		 */
		int cursor = 0;

		/**
		 * Index of element returned by most recent call to next or
		 * previous.  Reset to -1 if this element is deleted by a call
		 * to remove.
		 */
		int lastRet = -1;

		/**
		 * The modCount value that the iterator believes that the backing
		 * List should have.  If this expectation is violated, the iterator
		 * has detected concurrent modification.
		 */

		public boolean hasNext() {
			return cursor != size();
		}

		public T next() {
			int i = cursor;
			T next = get(i);
			lastRet = i;
			cursor = i + 1;
			return next;
		}

		public void remove() {
			if (lastRet < 0)
				return;

			List.this.remove(get(lastRet));
			if (lastRet < cursor)
				cursor--;
			lastRet = -1;

		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}
	
	private class Enum implements Enumeration<T>{
		/**
		 * Index of element to be returned by subsequent call to next.
		 */
		int cursor = 0;

		/**
		 * Index of element returned by most recent call to next or
		 * previous.  Reset to -1 if this element is deleted by a call
		 * to remove.
		 */
		int lastRet = -1;

		/**
		 * The modCount value that the iterator believes that the backing
		 * List should have.  If this expectation is violated, the iterator
		 * has detected concurrent modification.
		 */
		
		@Override
		public boolean hasMoreElements() {
			return cursor != size();
		}

		@Override
		public T nextElement() {
			int i = cursor;
			T next = get(i);
			lastRet = i;
			cursor = i + 1;
			return next;
		}
		
	}
	
	public Enumeration<T> elements(){
		return new Enum();
	}
}
