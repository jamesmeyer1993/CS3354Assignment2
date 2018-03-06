package main;

@SuppressWarnings("serial")
public class OverdraftException extends Exception {

	public OverdraftException(double less, double balance){
		double x = less = balance;
		System.err.print(this.getClass() + " : you are trying to withdraw " + less + ".\n"
				+ this.getClass() + " : But your balanance is " + balance + ".\n"
				+ this.getClass() + " : You do not have overdraft protection for a " + x + " deficit.\n");
		
	}
}
