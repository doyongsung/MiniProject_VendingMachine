package Ven;

import java.util.Scanner;

public class BuyOption {
	private Scanner name;
	private Scanner price;

	void VendingMachine() {
		BuyManager manager = new BuyManager(ProductListDao.getInstance());

		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			while (true) {
				System.out.println("---------- 아주시원한 자판기 -----------");
				manager.buyitemList();
				System.out.println("-----------------------------------");
				System.out.println("원하시는 음료 번호를 입력해주세요.");
				sc.nextInt();
				System.out.println("-----------------------------------");
				System.out.println(sc + "번" + name + price + "원 입니다.");
				int num = Integer.parseInt(sc.nextLine());

				switch (num) {
				case 1:
					// manager.itemList();
					break;
				case 2:
					// manager.inputData();
					break;
				case 3:
					// manager.editItem();
					break;
				case 4:
					break;
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}