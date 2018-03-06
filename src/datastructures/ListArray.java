package datastructures;

public abstract class ListArray<T> implements ListADT<T>{

	private final static int SIZE = 100;
	private final static int NOT_FOUND = -1;
	protected int rear;
	protected T[] list;
	protected int modCount;
	
	public ListArray(){
		this(SIZE);
	}
	
	public ListArray(int size){
		rear = 0;
		list = (T[]) (new Object[size]);
		modCount = 0;
	}
	
	/** at()
	 * @param integer index for requesting item at point
	 * @return generic type T
	 *  */
	public T at(int index){
		if(index < rear-1)
			throw new IndexOutOfBoundsException();
		return list[index];
	}
	
	public T removeFirst() {
		T first = list[0];
		
		for(int i = 0; i < list.length-1; i++)
			list[i] = list[i+1];
		
		rear--;
		modCount++;
		
		return first;
	}

	public T removeLast() {
		T last = list[rear];
		
		list[rear] = null;
		rear--;
		modCount++;
		
		return last;
	}

	public T remove(T element) {
		T result;
		int index = find(element);
		
		if (index == NOT_FOUND)
			throw new EmptyCollectionException("ListArray");
		
		result = list[index];
		rear--;
		
		// shift the appropriate elements
		for (int scan = index; scan < rear; scan++)
			list[scan] = list[scan+1];
		
		list[rear] = null;
		modCount++;
		return result;
	}

	private int find(T target){
		int scan = 0;
		int result = NOT_FOUND;
		if(!isEmpty()){
			while(result == NOT_FOUND && scan < rear)
				if (target.equals(list[scan]))
					result = scan;
				else
					scan++;
		}
		return scan;
	}
	
	protected void expandCapacity(){
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) (new Object[list.length * 2]);
		for(int i = 0; i < list.length; i++){
			temp[i] = list[i];
		}
		list = temp;
	}
	
	public T first() {return list[0];}

	public T last() {return list[rear];}

	public boolean contains(T target) {
		return (find(target) != NOT_FOUND);
	}

	public boolean isEmpty() {
		if(list[0] == null)
			return true;
		else
			return false;
	}

	public int size() {return rear;}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString(){
		String str = "";
		for(int i = 0; i < size(); i++)
			str = str + list[i] + "\n";
		return str;
	}
}