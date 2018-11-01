package Interfaces;

public interface Mergeable <T>{
	
	public void merge(Mergeable<T> ds);
	
	public Object [] getValues();
}
