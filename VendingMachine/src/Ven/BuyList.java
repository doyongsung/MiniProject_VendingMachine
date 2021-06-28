package Ven;

public class BuyList {

	private String name;

	private int buyCode;
	private int itemCode;
	private int price;
	private int itemQty;
	private int buyQty;
	private int totalPrice;

	public BuyList(int buyCode, int buyQty, int totalPrice, int itemCode) {
		this.buyCode = buyCode;
		this.buyQty = buyQty;
		this.totalPrice = totalPrice;
		this.itemCode = itemCode;
	}

	public BuyList(int buyQty, int totalPrice, int itemCode) {
		this.buyQty = buyQty;
		this.totalPrice = totalPrice;
		this.itemCode = itemCode;
	}

	public BuyList(int buyQty, int itemCode) {
		this.buyQty = buyQty;
		this.itemCode = itemCode;
	}
	
	public BuyList(int itemCode, String name, int buyQty, int totalPrice) {
		this.itemCode = itemCode;
		this.name = name;
		this.buyQty = buyQty;
		this.totalPrice = totalPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBuyCode() {
		return buyCode;
	}

	public void setBuyCode(int buyCode) {
		this.buyCode = buyCode;
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
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
		return "BuyList [name=" + name + ", buyCode=" + buyCode + ", itemCode=" + itemCode + ", price=" + price
				+ ", itemQty=" + itemQty + ", buyQty=" + buyQty + ", totalPrice=" + totalPrice + "]";
	}



}