/** @CurrencyUnit
 * 	
 * A class for denoting the most abstract form
 * of an exchange medium. A CurrencyUnit can extend to any
 * class representing an money unit, like a note, a coin,
 * a check, a digital asset etc.
 * */
package currency;

public class CurrencyUnit {

	private double usdValue;
	
	public CurrencyUnit(){
		usdValue = 0.0;
	}
	
	public CurrencyUnit(double value){
		usdValue = value;
	}
	
	public double getUSDValue(){
		return usdValue;
	}
	
	public void setUSDValue(double value){
		usdValue = value;
	}
}
