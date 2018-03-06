/**
 * @Coin
 * Extends CurrencyUnit and adds the additional property of
 * weight that a coin would have. Older vending machines would
 * evaluate payment based on the cumulative weight of coins
 * inserted. (I discovered this when I lived in Alaska and my
 * brother started buying snacks from vending machines with
 * Canadian quarters).
 */
package currency;

public abstract class Coin extends CurrencyUnit {

	private double weight; /* in grams */
	
	public Coin(){
		super();
		weight = 0.0;
	}
	
	public Coin(double value){
		super(value);
		weight = 0.0;
	}
	
	public Coin(double value, double weight){
		super(value);
		this.weight = weight;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public void setWeight(double weight){
		this.weight = weight;
	}
}
