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

	Connection conn = null;
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pw = "tiger";

	// 발주>
	void order() {
		ProductManager manager = new ProductManager(ProductListDao.getInstance());
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			// 주문
			for (;;) {
				System.out.print("발주 > \t 음료코드 입력 : ");
				int orderCode = sc.nextInt();
				System.out.print("발주 > \t 주문수량 입력 : ");
				int orderQty = sc.nextInt();
				System.out.println();

				ProductList ProductList = new ProductList(orderCode, orderQty);
				int result = dao.editProductList(conn, ProductList);

				if (result > 0) {
					System.out.println("주문 완료");
					System.out.println();

					// 주문 내용이 저장된 재고 목록 출력
					manager.itemList();

					System.out.println("1. 추가 발주 \n2. 뒤로가기 \n3. 종료");
					int num = sc.nextInt();
					if (num == 1) {
						System.out.println("추가 발주");
						continue;
					} else if (num == 2) {
						manager.SaleSelect();
					} else if (num == 3) {
						System.out.println("종료합니다");
						System.exit(0);
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
	public void subtract(int buyCode, int buyQty) {
		buyCode = 0;
		buyQty = 0;

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			ProductList ProductList = new ProductList(buyCode, buyQty);
			int result = dao.subtractProductList(conn, ProductList);
			if (result > 0) {
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
