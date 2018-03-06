package vendor;

public class Product {

	protected int count;
	
	private double usdValue;
	private String title;
	private String description;
	
	public Product(){
		count++;
	}
	
	public Product(String title, double usdValue, String description){
		this.title = title;
		this.usdValue = usdValue;
		this.description = description;
	}

	public void setTitle(String str){
		title = str;
	}
	
	public void setUSDValue(double val){
		usdValue = val;
	}
	
	public void setDescription(String str){
		description = str;
	}
	
	public double getUSDValue(){
		return usdValue;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String toString(){
		return title + " " + usdValue + " " + description;
	}
}
