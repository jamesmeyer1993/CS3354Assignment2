package datastructures;

@SuppressWarnings("serial")
public class EmptyCollectionException extends RuntimeException{

	// TODO: add serial version ID
	
	public EmptyCollectionException(String collection){
		super("The " + collection + " is empty.");
	}
}