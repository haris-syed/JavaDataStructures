package LinearStructures;

public class DoubleNode<T> extends Node<T> {

	private DoubleNode<T> previous;
	
	public DoubleNode(T value) {
		super(value);
		this.previous=null;
	}

	public DoubleNode<T> getPrevious() {
		return previous;
	}

	public void setPrevious(DoubleNode<T> previous) {
		this.previous = previous;
	}
	
	
}
