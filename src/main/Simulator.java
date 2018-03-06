/* James Meyer
 * Object Oriented Design Patterns
 * 3/6/2017
 */

package main;

import java.util.Scanner;
import crypto.SHA1;
import currency.CurrencyUnit;
import vendor.Product;
import vendor.VendingMachine;

public class Simulator implements Runnable{
	
	private OpLong op[];
		
	/** @value Instance level values */
	private String name;
	private int status;
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
		this.printWalletInstructions();
		
		int argc;		/** for counting arguments */
		String argv[];
		do{
			input = scan.nextLine();		/** read input */
			argc = countArgs(input);		/** calculate size */
			argv = parseArgs(input, argc);	/** memory allocate */

			for(int i = 0; i < argc; i++){
				System.out.print(argv[i] + " ");
			}
			
		}while(isRunning);
		
		if(!isRunning)
			exit(status);
	}
	
	private int countArgs(String in){
		int c = 0;
		for(int i = 0; i < in.length(); i++){
			if(in.charAt(i) == ' ')
				c++;
		}
		return c;
	}
	
	/** isValidArg - checks validity of command line input 
	 * @param String argument, Integer remaining arguments
	 * @return boolean */
	private boolean isValidArg(String arg, int remaining){

		for(int i = 0; i < op.length; i++){
			boolean found = false;
			if( ( arg.equals(op[i].getOpLong()) || arg.equals(op[i].getOpShort()) )
					&& remaining >= op[i].getOpCount() ){
				op[i].setOpf(true);	/** @value flag */
				return false;
			}
		}
		return false;
	}
	
	/** parseArgs
	 * @param String input, integer argument or space count in input 
	 * @return Array of strings, parsed by the spaces of input */
	private String[] parseArgs(String in, int c){
		String out[] = new String[c];
		int j = 0;
		for(int i = 0; i < c; i++){
			
			do{
				out[i] = out[i] + in.charAt(j);
				j++;
				/** Ignore the first case, because of the way the loop is exited.*/	
			}while(in.charAt(j) != ' ');
		}
		return out;
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
			instFlag = new Flag();
			theMatrix = new Simulator(name, instFlag, hash.getHash());
			/** Begin simulation */
			theMatrix.run();
		} 
		catch (SingletonException e) {e.printStackTrace();}
	}
}
