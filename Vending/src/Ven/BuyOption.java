package Ven;

import java.util.Scanner;

public class BuyOption {


	void VendingMachine() {
		BuyManager manager = new BuyManager(ProductListDao.getInstance());
		ProductManager pm = new ProductManager(ProductListDao.getInstance());
		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			int b = 0;
			while (b < 2) {
				System.out.println("---------- 아주시원한 자판기 -----------");
				manager.buyitemList();
				System.out.println("-----------------------------------");
				System.out.println("원하시는 음료 번호를 입력해주세요.");
				int r = sc.nextInt();

				pm.buyList(r);
				// 1. 추가 구매 2. 프로그램 종료 commit 하고나서 뿌려주는 문장
				// 추가 하면 다시 pm.buylist(r) 이동
				//
				b = 3;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}