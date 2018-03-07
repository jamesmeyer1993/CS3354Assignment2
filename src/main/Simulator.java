/* James Meyer
 * Object Oriented Design Patterns
 * 3/6/2017
 */

package main;

import java.util.Scanner;
import crypto.SHA1;
import currency.CurrencyUnit;
import datastructures.StackArray;
import vendor.Product;
import vendor.VendingMachine;

public class Simulator implements Runnable{
	
	private OpLong op[];			/** @value list of long operation commands */
	private String name;			/** @value of this simulation */
	private int status;				/** @value general exit status */
	private VendingMachine dummy;	/** @value simulated vending machine */
	private Operator neo;			/** @value object the user acts as */
	protected int simCount = 0;		/** @value maintains the singleness of the simulator class */
	private StackArray<String> in;	/** @StackArray for evaluating command line arguments */
	
	/** Simulator
	 * Constructor. @throws SingletonException because only one simulation may be run at a time.
	 * @param String name, OpLong valid arguments, byte[] hash to lock the vending machine */
	public Simulator(String n, OpLong[] vargs, byte[] rootLock) throws SingletonException{
		simCount++;
		if(simCount > 1)
			throw new SingletonException();
		name = n;
		op = vargs;
	}

	/** Run
	 * Actual main loop of the program.
	 * Evaluates user input and acts accordingly. */
	public void run() {
		boolean isRunning = true;
		Scanner scan = new Scanner(System.in);
		String input = null;
		
		System.out.print("Simulator : Simulator instantiated.\n" +
				"Simulator : Press 'q' or 'Q' to exit.");
		this.printWalletInstructions();
		
		do{
			System.out.print("\n> ");
			input = scan.nextLine();	/** read input */
			in = parseArgs(input);		/** transpose args to a stack */

			for(int i = 0; i < in.size(); i++){
				System.out.print(in.pop() + "\t");
			}
			
		}while(isRunning);
		
		if(!isRunning)
			exit(status);
	}
	
	/** isValidArg - checks validity of command line input 
	 * @param String argument, Integer remaining arguments
	 * @return boolean */
	private boolean isValidArg(String arg, int remaining){

		for(int i = 0; i < op.length; i++){
			boolean found = false;
			if(arg.equals(op[i].getOpLong()) && remaining >= op[i].getOpCount() ){
				op[i].setOpf(true);	/** @value flag */
				return false;
			}
		}
		return false;
	}
	
	/** parseArgs
	 * @param String input, integer argument or space count in input 
	 * @return Array of strings, parsed by the spaces of input */
	private StackArray<String> parseArgs(String in){
		
		String p[];
		char[] ch = in.toCharArray();
		int c = 0;
		for(int i = 0; i < ch.length; i++){
			if(ch[i] == ' ')
				c++;
		}
		
		p = new String[c+1];
		c = 0;
		for(int i = 0; i < p.length; i++)
			p[i] = "";
		
		for(int i = 0; i < ch.length; i++){
			if(ch[i] != ' ')
				p[c] = p[c] + ch[i];
			else
				c++;
		}
		
		StackArray<String> r = new StackArray<String>(p.length);
		for(int i = p.length; i > -1 ; i--)
			r.push(p[i]);
		
		return r;
	}
	
	private void printWalletInstructions(){
		System.out.print("\n" +this.getClass() + " : Stocking up the wallet.\n"
		+ this.getClass() + " : The following currency units exist:\n"
		+ this.getClass() +	" : \tPenny, Nickel, Dime, Quarter\n"
		+ this.getClass() + " : Equal to $0.01, $0.05, $0.10, $0.25, respectively.\n"
		+ this.getClass() + " : To stock up your wallet, enter the name of the currency unit <space> amount you need.\n"
		+ this.getClass() + " : Example: Quarter 15\n");
	}
	
	private void exit(int status){
		System.out.print("Simulator : exiting...");
		System.exit(status);
	}
	
	/** Begin Main
	 * @param String command line arguments*/
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
		//OpLong vargs[];
		
		try 
		{
			OpLong vargs[] = {
				new OpLong<Integer>("help",		(byte)0,	false),
				new OpLong<Integer>("quit", 	(byte)0,	false),
				new OpLong<OpLong<Integer>>("add",(byte)2,	false),
				new OpLong<Product>("purcharse",(byte)1,	false),
				new OpLong<CurrencyUnit>("push",(byte)1,	false),
				new OpLong<Integer>("list",		(byte)0,	false),
				new OpLong<Byte[]>("cashout",	(byte)0,	false),
				new OpLong<Integer>("quarter",	(byte)1,	false),
				new OpLong<Integer>("nickel",	(byte)1,	false),
				new OpLong<Integer>("penny",	(byte)1,	false),
				new OpLong<Integer>("dime",		(byte)1,	false),
			};
			//instFlag = new Flag();
			theMatrix = new Simulator(name, vargs, hash.getHash());
			/** Begin simulation */
			theMatrix.run();
		} 
		catch (SingletonException e) {e.printStackTrace();}
	}
}
