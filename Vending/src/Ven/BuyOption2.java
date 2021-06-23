package Ven;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BuyOption2 {
	private Scanner name;
	private Scanner price;

	void VendingMachine() {
		BuyManager manager = new BuyManager(ProductListDao.getInstance());
		ProductManager pManager = new ProductManager(ProductListDao.getInstance());

		Scanner sc = new Scanner(System.in);
		
		// 데이터 베이스의 ProductList 테이블 이용 select 결과물 -> list 에 저장
				Statement stmt = null;
				ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			while (true) {
				System.out.println("---------- 아주시원한 자판기 -----------");
				manager.buyitemList();
				System.out.println("-----------------------------------");
				System.out.println("원하시는 음료 번호를 입력해주세요.");
				int num = Integer.parseInt(sc.nextLine());
				System.out.println("-----------------------------------");
				
				// 콜라선택
				if(num == 1) {
				System.out.println("콜라를 선택하셨습니다.");
				System.out.printf("수량을 입력해주세요 : ");
				int qty = Integer.parseInt(sc.next());
				String sql = "select itemqty from PRODUCTINFO where name='콜라'";
					
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}