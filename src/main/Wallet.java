package main;

import currency.Coin;
import currency.CurrencyUnit;
import datastructures.ListUnorderedArray;

public class Wallet {

	private ListUnorderedArray<CurrencyUnit>content;
	double balance;
	
	public Wallet(){
		balance = 0.0;
	}
	
	private int findClass(String targetClass){
		
	}
	
	private boolean containsClass(String targetClass){
		
	}
	
	public CurrencyUnit removeCurrency(String curClass){
		
	}
	
	public double removeBal(double less) throws OverdraftException{
		if(less > balance)
			throw new OverdraftException(less, balance);
		balance = balance - less;
		return less;
	}
	
	public double getBalance(){return balance;}
}
