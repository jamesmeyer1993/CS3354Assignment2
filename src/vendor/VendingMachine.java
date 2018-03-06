package vendor;

import currency.Coin;
import datastructures.ListUnorderedArray;
import datastructures.StackArray;

public class VendingMachine {

	private CoinAcceptor<Coin> slot;	/* coin slot */
	private ListUnorderedArray<StackArray<Product>> inventory;
	private StackArray<Product>[] products; 
	
	public VendingMachine(){
		/* Default Constructor */
		slot = new CoinAcceptor<Coin>();
		inventory = new ListUnorderedArray<StackArray<Product>>();
		
		int defSize = 8;
		products = new StackArray[defSize];
		
		for(int i = 0; i < defSize; i++){
			products[i] = new StackArray<Product>();
			Product p = null;
		}
	}
	
	/* Display Methods */
	public void displayAll(){
		System.out.print(inventory.toString());
	}
}
