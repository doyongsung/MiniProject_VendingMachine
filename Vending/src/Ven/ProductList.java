package Ven;


public class ProductList {
	
	private int itemCode;
	private	String name;
	private int price;
<<<<<<< HEAD
	private	String[] itemQty;
=======
	private	int itemQty;
>>>>>>> asdsd
	
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
<<<<<<< HEAD
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
=======
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
>>>>>>> asdsd
		this.itemQty = drinkStock;
	}
	@Override
	public String toString() {
<<<<<<< HEAD
		return "ProductList [itemcode=" + itemCode + ", name=" + name + ", price=" + price + ", drinkStock="
				+ Arrays.toString(itemQty) + "]";
	}
	

=======
		return "ProductList [itemCode=" + itemCode + ", name=" + name + ", price=" + price + ", itemQty=" + itemQty
				+ ", getItemcode()=" + getItemcode() + ", getName()=" + getName() + ", getPrice()=" + getPrice()
				+ ", getDrinkStock()=" + getDrinkStock() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
>>>>>>> asdsd

}
