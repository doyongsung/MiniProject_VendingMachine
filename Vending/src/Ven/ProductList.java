package Ven;


public class ProductList {
	
	private int itemCode;
	private	String name;
	private int price;
	private	int itemQty;
	
	public ProductList(int itemCode, String name, int price, int itemQty) {
		this.itemCode = itemCode;
		this.name = name;
		this.price = price;
		this.itemQty = itemQty;
	}
	public int getItemcode() {
		return itemCode;
	}
	public void setItemcode(int itemcode) {
		this.itemCode = itemcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDrinkStock() {
		return itemQty;
	}
	public void setDrinkStock(int drinkStock) {
		this.itemQty = drinkStock;
	}
	@Override
	public String toString() {
		return "ProductList [itemCode=" + itemCode + ", name=" + name + ", price=" + price + ", itemQty=" + itemQty
				+ ", getItemcode()=" + getItemcode() + ", getName()=" + getName() + ", getPrice()=" + getPrice()
				+ ", getDrinkStock()=" + getDrinkStock() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	

}
