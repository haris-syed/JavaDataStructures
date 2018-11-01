package LinearStructures;

public class CircularList<T extends Comparable<? super T>> extends List<T> {

	public CircularList() {
		super();
	}
	
	@Override
	public  void insert(T value) {
		Node <T> current = head;
		Node <T> newNode=new Node<T>(value);
		if(head == null) {
			head=newNode;
			newNode.setNext(head);
		}
		else {
			while (current.getNext() != head) {
				current = current.getNext(); // we'll loop until current.next is head
			}
			current.setNext(newNode);
			newNode.setNext(head);
		}
		size++;
	}
	
	@Override
	public  void remove(T value) {
		if(head==null) {
			return;
		}

		if(head.getValue()==value) {
			Node<T> last= head;
			while(last.getNext()!=head) {
				last=last.getNext();
			}
			last.setNext(head.getNext());
			head=head.getNext();
			size--;
			return;
		}
		
		
		Node <T> current=head.getNext();
		Node<T> prev = null;
		while(current != head && current.getValue() != value) {
			prev=current;
			current=current.getNext();
		}
		if(current==head) {
			return;
		}
		else {
			prev.setNext(current.getNext());
			size--;
		}	
	}
	
	@Override
	public  int search(T value) {
		return -1;
	}
	
	@Override
	public void printList() {
		if(head!=null) {
			System.out.print(head.getValue().toString()+" ");
			Node<T> current=head.getNext();
			while(current!=head) {
				System.out.print(current.getValue().toString()+" ");
				current=current.getNext();
			}
			System.out.println();
		}	
	}
	
	@Override
	public Object[] getValues() {
		if(this.size>0) {
			Node<T> current=head.getNext();
			Object[] array = new Object[this.size];
			array[0]=head.getValue();
			int i=1;
			while(current!=head) {
				array[i]=current.getValue();
				i++;
				current=current.getNext();
			}
			return array;
		}
		return null;
	};
}
