/**Wallet
 * Acts as wrapper class for an unordered list of currency units.
 * */
package main;

import currency.CurrencyUnit;
import datastructures.ListUnorderedArray;

public class Wallet {

	private ListUnorderedArray<CurrencyUnit>content;
	double balance;
	
	public Wallet(){
		balance = 0.0;
	}
	
	private int findClass(String targetClass){
		boolean found = false;
		int i = 0;
		while(!found && i < content.size()){
			if(content.at(i).getClass().equals(targetClass))
				found = true;
		}
		
		if(!found)
			return -1;
		return i;
	}
	
	private boolean containsClass(String targetClass){
		if(findClass(targetClass) == -1)
			return false;
		else
			return true;
	}
	
	/** add() - wrapper method for ListUnorderedArray.addToBack() 
	 * @param any class that extends CurrencyUnit 
	 * Increments balance */
	public void add(CurrencyUnit u){
		double c = u.getUSDValue();
		balance = balance + c;
		content.addToRear(u);
	}
	
	public CurrencyUnit removeCurrency(CurrencyUnit c) 
			throws ClassNotFoundException{
		
		String curClass = c.getClass().toGenericString();
		
		if(!containsClass(curClass))
			throw new ClassNotFoundException();
		
		int location = findClass(curClass);
		return (CurrencyUnit) content.at(location);
	}
	
	public double removeBal(double less) throws OverdraftException{
		if(less > balance)
			throw new OverdraftException(less, balance);
		balance = balance - less;
		return less;
	}
	
	public double getBalance(){return balance;}
}
