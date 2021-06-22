package Ven;

import java.util.Arrays;

public class ProductList {
	
	private int itemCode;
	private	String name;
	private int price;
	private	String[] itemQty;
	
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
	public String[] getDrinkStock() {
		return itemQty;
	}
	public void setDrinkStock(String[] drinkStock) {
		this.itemQty = drinkStock;
	}
	@Override
	public String toString() {
		return "ProductList [itemcode=" + itemCode + ", name=" + name + ", price=" + price + ", drinkStock="
				+ Arrays.toString(itemQty) + "]";
	}
	


}
