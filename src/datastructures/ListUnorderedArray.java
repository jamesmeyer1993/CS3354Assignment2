package datastructures;

public class ListUnorderedArray <T> extends ListArray<T> implements ListUnorderedADT<T>{

	public void addToFront(T element) {
		if(size()+1 == list.length)				// size()+1 to account for inserted element
			expandCapacity();
		
		for(int i = rear+1; i > 0; i--)			// start at +1 from the back and move everything back by
			list[i] = list[i-1];				// decrementing i
		
		list[0] = element;
		rear++;
		modCount++;
	}

	public void addToRear(T element) {
		if(size() == list.length)
			expandCapacity();
		
		list[rear+1] = element;
		rear++;
		modCount++;
	}

	public void addAfter(T element, T target) {
		if(size() == list.length)
			expandCapacity();
		
		int scan = 0;
		
		// find the insertion point
		while (scan < rear && !target.equals(list[scan]))
			scan++;
		
		if(scan == rear)
			throw new ElementNotFoundException("LustUnorderedArray");
		
		scan++;
		
		// shift elements up one
		for(int shift=rear; shift < scan; shift--)
			list[shift] = list[shift-1];
		
		// insert element
		list[scan] = element;
		rear++;
		modCount++;
	}

}