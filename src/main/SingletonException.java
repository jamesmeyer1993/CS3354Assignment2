package main;

@SuppressWarnings("serial")
public class SingletonException extends Exception {

	public SingletonException(){
		System.err.print("There may only be one of this class");
		System.exit(-1);
	}
}
