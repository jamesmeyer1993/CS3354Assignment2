/**
 * CoinAcceptor
 * - Extends CurrencyAcceptor
 * - Specifically Only Accepts Coins
 */

package vendor;

import currency.Coin;

public class CoinAcceptor<Coin> extends CurrencyAcceptor<Coin> {

	private double inputWeight;
	
	CoinAcceptor(){
		super();
	}
	
	public void push(Coin c){
		inputWeight = inputWeight + ((currency.Coin) c).getWeight();
	}
}
