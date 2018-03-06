package main;

public class OpLong<T>{

	String oplong;	/** @value long form of the argument */
	byte opc;		/** @value number of arguments to follow this */
	boolean opf;	/** @value is this argument used? */
	T data;			/** @value instance data for whatever operation */
	
	public OpLong(String s, byte n, boolean o){
		if(n > 2 || n < 0) /** @value opc may only be 0 or 1*/
			throw new IllegalArgumentException();
		
		oplong = s;
		opc = n;
		opf = o;
	}
	
	public String getOpLong(){return oplong;}
	public byte getOpCount(){return opc;}
	public boolean getOpFlag(){return opf;}
	public T getOpData(){return data;}
	void setOpf(boolean v){opf = v;}
	void setData(T d){data = d;}

}
