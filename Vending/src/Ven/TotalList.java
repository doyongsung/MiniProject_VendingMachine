package Ven;

public class TotalList {

	private int itemCode;
	private String name;
	private int price;
	private int itemQty;
	private int buyQty;
	private int totalPrice;

	public TotalList(int itemCode, String name, int buyQty, int totalPrice) {
		this.itemCode = itemCode;
		this.name = name;
		this.buyQty = buyQty;
		this.totalPrice = totalPrice;
	}

	public TotalList(String name) {
		this.name = name;

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

	public int getItemQty() {
		return itemQty;
	}

	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}

	// BUYINFO
	public int getBuyQty() {
		return buyQty;
	}

	public void setBuyQty(int buyQty) {
		this.buyQty = buyQty;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "ProductList [itemCode=" + itemCode + ", name=" + name + ", price=" + price + ", itemQty=" + itemQty
				+ ", buyQty=" + buyQty + ", totalPrice=" + totalPrice + "]";
	}

}