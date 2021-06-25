package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Order {
	private ProductListDao dao;
	private Scanner sc;

	public Order(ProductListDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}

	// 1. 재고목록 출력

	// 2. 주문받기
	void order() {
		ProductManager manager = new ProductManager(ProductListDao.getInstance());
		Connection conn = null;

		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			// 주문
			for (;;) {
				System.out.println("————————————————————————————————————————————————");
				System.out.print("발주 > \t 음료코드 입력 : ");
				int orderCode = sc.nextInt();
				System.out.println();
				System.out.print("발주 > \t 주문수량 입력 : ");
				System.out.println();
				int orderQty = sc.nextInt();

				ProductList ProductList = new ProductList(orderCode, orderQty);
				int result = dao.editProductList(conn, ProductList);

				if (result > 0) {
					System.out.println("주문 완료");
					System.out.println();
					System.out.println();

					// 3. 주문 내용이 저장된 재고 목록 출력
					manager.itemList();

					// 4. 주문 추가 / 주문 종료 선택

					System.out.println("1. 추가 발주 \n2. 뒤로가기 \n3. 종료");
					int num = sc.nextInt();
					if (num == 2) {
						manager.SaleSelect();
						if (num == 3) {

							System.out.println("주문을 종료합니다.");
							break;
						}

					} else {
						System.out.println("주문 실패");
					}

				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 차감
	void subtract() {
		ProductManager pm = new ProductManager(ProductListDao.getInstance());
		BuyManager manager = new BuyManager(ProductListDao.getInstance());
		Connection conn = null;

		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			while (true) {

				System.out.println("-----------------------------------");
				System.out.println("====================================");
				System.out.print("구매하실 음료를 선택해주세요");
				int orderCode = sc.nextInt();
				System.out.println();
				System.out.print("구매하실 갯수를 선택해주세요.");
				int orderQty = sc.nextInt();
				System.out.println();

				ProductList ProductList = new ProductList(orderCode, orderQty);
				int result = dao.subtractProductList(conn, ProductList);
				pm.buyList(orderCode, orderQty);

				if (result > 0) {
				} else {
					System.out.println("주문 실패");
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}