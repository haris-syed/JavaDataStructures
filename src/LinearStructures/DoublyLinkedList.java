package LinearStructures;


/*  SOURCE: http://www.java2novice.com/data-structures-in-java/linked-list/doubly-linked-list/  */

public class DoublyLinkedList<T extends Comparable<? super T>> extends List <T> {

	private DoubleNode<T> tail;
	
	public DoublyLinkedList() {
		super();
		this.tail=null;
	}
	
	
	@Override
	public  void insert(T value) {
		DoubleNode<T> tmp = new DoubleNode<T>(value);
		tmp.setPrevious(tail);
		if(tail != null) {
			tail.setNext(tmp);
		}
		tail = tmp;
		if(head == null) { head = tmp;}
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
				tail=null;
				size--;
			}
			return;
		}
		
		if(head.getValue()==value) {
			head=head.getNext();
			size--;
			return;
		}
		

		DoubleNode <T> current=(DoubleNode<T>)head;
		while(current != null && current.getValue() != value) {
			current=(DoubleNode<T>) current.getNext();
		}
		if(current==null) {
			return;
		}
		else { //found value
			if(current.getPrevious() != null)
				current.getPrevious().setNext(current.getNext());
			if(current.getNext() != null)
				((DoubleNode<T>)current.getNext()).setPrevious(current.getPrevious());
			size--;
		}	
	}
	
	public void printListReverse() {
		if(tail!=null) {
			DoubleNode<T> current=tail;
			while(current!= ((DoubleNode<T>)head).getPrevious() ) {
				System.out.print(current.getValue().toString()+" ");
				current=current.getPrevious();
			}
		}	
	}
}
