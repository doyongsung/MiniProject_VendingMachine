package Ven;

public class CoinList {

	private int moenyKey;
	private int oback;
	private int back;
	private int moenyAll;

	public int getMoenyKey() {
		return moenyKey;
	}

	public void setMoenyKey(int moenyKey) {
		this.moenyKey = moenyKey;
	}

	public int getOback() {
		return oback;
	}

	public void setOback(int oback) {
		this.oback = oback;
	}

	public int getBack() {
		return back;
	}

	public void setBack(int back) {
		this.back = back;
	}

	public int getMoenyAll() {
		return moenyAll;
	}

	public void setMoenyAll(int moenyAll) {
		this.moenyAll = moenyAll;
	}

	@Override
	public String toString() {
		return "CoinList [moenyKey=" + moenyKey + ", oback=" + oback + ", back=" + back + ", moenyAll=" + moenyAll
				+ "]";
	}

	public CoinList(int moenyKey, int oback, int back, int moenyAll) {
		super();
		this.moenyKey = moenyKey;
		this.oback = oback;
		this.back = back;
		this.moenyAll = moenyAll;
	}

}
