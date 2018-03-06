package vendor;

import datastructures.StackArray;
import currency.CurrencyUnit;

public abstract class CurrencyAcceptor<CurrencyUnit> extends StackArray<CurrencyUnit>{

	@SuppressWarnings("unused")
	private final int DEFAULT_CAPACITY = 8;	/* variable override to save memory */
	
	private double inputValue;
	
	public CurrencyAcceptor(){
		inputValue = 0.0;
	}
	
	public double getInputValue(){
		return inputValue;
	}
	
	public void push(CurrencyUnit u){
		inputValue = inputValue + ((currency.CurrencyUnit) u).getUSDValue();
		super.push(u);
	}
	
	public CurrencyUnit pop(){
		CurrencyUnit u = super.pop();
		inputValue = inputValue - ((currency.CurrencyUnit) u).getUSDValue();
		return u;
	}
}
 