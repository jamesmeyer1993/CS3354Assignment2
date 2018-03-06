/* James Meyer
 * Object Oriented Design Patterns
 * 3/6/2017
 */

package main;

import java.util.Scanner;

import crypto.SHA1;
import vendor.VendingMachine;

public class Simulator implements Runnable{
	
	private String name;
	private int status;
	//private String valArgs[] = {"help", "flag"}; /* valid arguments */
	private Flag flags;
	private VendingMachine dummy;
	private Operator neo;
	private Flag flag;
	
	protected int simCount = 0;
	
	public Simulator(String n, Flag f, byte[] rootLock) 
			throws SingletonException{
		simCount++;
		if(simCount > 1)
			throw new SingletonException();
		name = n;
		flag = f;
	}

	public void run() {
		boolean isRunning = true;
		Scanner scan = new Scanner(System.in);
		String input = null;
		
		System.out.print("Simulator : Simulator instantiated.\n" +
				"Simulator : Press 'q' or 'Q' to exit.");
		do{
			input = scan.nextLine();
			
			switch(input.charAt(0)){
				case 'q': isRunning = false; status = 0; break;
				case 'Q': isRunning = false; status = 0; break;
				default: System.err.print(name + " : " + input.charAt(0) + " Unkown input."); break;
			}
		}while(isRunning);
		
		if(!isRunning)
			exit(status);
	}
	
	private void mkOperator(){
		
	}
	
	private void exit(int status){
		System.out.print("Simulator : exiting...");
		System.exit(status);
	}
	
	/** Begin Main
	 * @param String command line arguments
	 */
	
	public static void main(String[] args) {
		
		/* Check for command line args */
		if(args.length < 1)
			System.out.print("Simulator : No command line arguments provided. Resorting to default flags.\n");
		else{
			System.out.print("Simulator : Current version cannot process command line args. Resorting to defaul flags.\n");
		}
		
		Scanner scan = new Scanner(System.in);
		
		/* Retrieve Variables for the Simulator */
		System.out.print("Simulator : Welcome new user.\n" +
				"Simulator : In order to simulate a vending machine, we fist need to configure it.\n" +
				"Simulator : What is the vending machine to be called?\n");
		String name = scan.nextLine();
		System.out.print("Simulator : Thank you.\n" +
				"Please enter the password for the vending machine.\n" +
				"(This password will prevent unauthorised users from accessing money in the machine).\n");
		String pass = scan.nextLine();
		SHA1 hash = new SHA1(pass);
		
		Simulator theMatrix;
		Flag instFlag;
		
		try 
		{
			instFlag = new Flag();
			theMatrix = new Simulator(name, instFlag, hash.getHash());
			
			/** Begin simulation */
			theMatrix.run();
		} 
		catch (SingletonException e) {e.printStackTrace();}
	}
}
