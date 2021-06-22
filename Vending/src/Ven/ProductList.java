package Ven;

public class ProductList {
	
	private	int drinkName;
	private	String[] drinkPrice;
	private	int drinkStock;
	
	ProductList(int drinkName,String[] drinkPrice,int drinkStock) {
		this.drinkName = drinkName;
		this.drinkPrice = drinkPrice;
		this.drinkStock = drinkStock;
			}

	public int getDrinkName() {
		return drinkName;
	}

	public void setDrinkName(int drinkName) {
		this.drinkName = drinkName;
	}

	public String[] getDrinkPrice() {
		return drinkPrice;
	}

	public void setDrinkPrice(String[] drinkPrice) {
		this.drinkPrice = drinkPrice;
	}

	public int getDrinkStock() {
		return drinkStock;
	}

	public void setDrinkStock(int drinkStock) {
		this.drinkStock = drinkStock;
	}



}
