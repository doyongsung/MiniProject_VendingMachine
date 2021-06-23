package Ven;

public class CoinList {

	public int getMoenyKey() {
		return moenyKey;
	}

	public void setMoenyKey(int moenyKey) {
		this.moenyKey = moenyKey;
	}

	public String getMoneyName() {
		return moneyName;
	}

	public void setMoneyName(String moneyName) {
		this.moneyName = moneyName;
	}

	public int getMoneyCount() {
		return moneyCount;
	}

	public void setMoneyCount(int moneyCount) {
		this.moneyCount = moneyCount;
	}

	public int getMoenyAll() {
		return moenyAll;
	}

	public void setMoenyAll(int moenyAll) {
		this.moenyAll = moenyAll;
	}

	public CoinList(int moenyKey, String moneyName, int moneyCount, int moenyAll) {
		super();
		this.moenyKey = moenyKey;
		this.moneyName = moneyName;
		this.moneyCount = moneyCount;
		this.moenyAll = moenyAll;
	}

	@Override
	public String toString() {
		return "CoinList [moneyMax=" + moneyMax + "]";
	}

	public CoinList(int moneyMax) {
		super();
		this.moneyMax = moneyMax;
	}

	public int getMoneyMax() {
		return moneyMax;
	}

	public void setMoneyMax(int moneyMax) {
		this.moneyMax = moneyMax;
	}

	private int moneyMax;
	private int moenyKey;
	private String moneyName;
	private int moneyCount;

	public CoinList(int moenyKey, int moneyCount) {
		super();
		this.moenyKey = moenyKey;
		this.moneyCount = moneyCount;
	}

	private int moenyAll;

}
