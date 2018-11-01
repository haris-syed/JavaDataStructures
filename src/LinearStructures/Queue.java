package LinearStructures;

import Interfaces.Mergeable;
import Interfaces.Sortable;

public class Queue <T extends Comparable<? super T>> implements Mergeable<T>,Sortable {

	List<T> list;
	
	public Queue() {
		list=new List<T>();
	}
	
	public void enqueue(T value) {
		list.insert(value);
	}

	public T deqeue() {
		return list.removeFirst();
	}
	
	@Override
	public void bubbleSort() {
		list.bubbleSort();
		
	}

	@Override
	public void insertionSort() {
		list.insertionSort();		
	}

	@Override
	public void quickSort() {
		list.quickSort();// TODO Auto-generated method stub
		
	}

	@Override
	public void mergeSort() {
		list.mergeSort();// TODO Auto-generated method stub
		
	}

	@Override
	public void countingSort() {
		list.countingSort();// TODO Auto-generated method stub
		
	}

	@Override
	public void merge(Mergeable<T> ds) {
		list.merge(ds);
	}

	@Override
	public Object[] getValues() {
		return list.getValues();
	}

}
