package datastructures;

public interface IteratorADT <E>{

	boolean hasNext();
	/*
	 * Iterator<E> itr = mylist.iterator();
	 * while(itr.hasNext())
	 * 		System.out.printlin(itr.next();
	 */
	
	E next();
	
	void remove();
	/*
	 * for (Object obj : list)
	 * 		System.out.println(obj);
	 */
}