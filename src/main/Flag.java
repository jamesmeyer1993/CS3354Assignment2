package main;

public class Flag {

	private boolean help;
	private boolean verbose;
	private boolean input;
	private boolean output;
	
	private String inv_src;	/* inventory source file */
	private String log_src;	/* output log source file*/
	
	protected static int flagCount = 0;
	
	public Flag() throws SingletonException{
		this(true, true, true, "data/inventory.csv", true, "data/log.csv");
	}
	
	public Flag(boolean h, boolean v, boolean i, String input, boolean o, String log) throws SingletonException{
		flagCount++;
		if(flagCount > 1)
			throw new SingletonException();
		
		this.help = h;
		this.verbose = v;
		this.input = i;
		this.output = o;
	}
	
	boolean getfHelp(){return help;}
	boolean getfVerbose(){return verbose;}
	boolean getfInput(){return input;}
	boolean getfOutput(){return output;}
}
