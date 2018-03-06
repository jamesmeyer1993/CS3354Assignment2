package datastructures;

import java.util.EmptyStackException;

public class StackArray<T> implements StackADT<T>{

	private final static int DEFAULT_CAPACITY = 100;
	private int top;
	private T[] stack;
	
	public StackArray(){
		this(DEFAULT_CAPACITY);
	}
	@SuppressWarnings("unchecked")
	public StackArray(int capacity){
		top = 0;
		stack = (T[]) (new Object[capacity]);
	}
	
	public void push(T element) {
		if(size() == stack.length)
			expandCapacity();
		
		stack[top] = element;
		top++;
	}

	public T pop() {
		if(isEmpty())
			throw new EmptyStackException();
		
		top--;
		T result = stack[top];
		stack[top] = null;
		
		return result;
	}

	public T peek() {
		if(isEmpty())
			throw new EmptyStackException();
		
		return stack[top-1];
	}

	public boolean isEmpty() {
		boolean result = false;
		if(size() == 0)
			result = true;
			
		return result;	
	}

	private void expandCapacity(){
		// TODO Arrays.copyOf(stack, steack.length * 2)
		T[] temp = (T[]) (new Object[stack.length * 2]);
		for(int i = 0; i < stack.length; i++){
			temp[i] = stack[i];
		}
		stack = temp;
			
	}
	
	public int size() {
		return top;
	}

	public String toString(){
		String result = "";
		
		result = this.getClass() + " : Object type: " + stack.getClass() + "\n" + "Size: " + size() + "\n";
		for(int i = 0; i < size(); i++){
			result = result + i + ". " + stack[i] + "\n"; 
		}
		
		return result;
	}
	
}