package Ven;

public class BuyList {
	
	private int buyCode;
	private int buyQty;
	private int totalPrice;
	private int itemCode;
	
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

	public int getBuyCode() {
		return buyCode;
	}
	
	public void setBuyCode(int buyCode) {
		this.buyCode = buyCode;
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

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	
	@Override
	public String toString() {
		return "BuyList [buyCode="+buyCode+", buyQty="+buyQty+", totalPrice="+totalPrice+", itemCode="+itemCode+"]";
	}	

}
