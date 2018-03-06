package main;

import currency.CurrencyUnit;

public class Operator {
	
	Wallet coinbase;
	
	public Operator(){
		coinbase = null;
	}
	
	public Operator(Wallet w){
		coinbase = w;
	}
	
	public CurrencyUnit spend(CurrencyUnit u){
		
		try {
			return coinbase.removeCurrency(u);
		} catch (ClassNotFoundException e) {
			String c = u.getClass().toGenericString();
			System.err.print("Operator : you do not have any " + c + " in your wallet.\n");
		}
		
		return null;
	}
	
	public void recieve(CurrencyUnit u){
		coinbase.add(u);
	}
}
