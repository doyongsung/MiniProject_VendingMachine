package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Order {
	private ProductListDao dao;
	private Scanner sc;
	SaleSelect sal = new SaleSelect();
	
	Connection conn = null;
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pw = "tiger";

	public Order(ProductListDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}

	
	
	
	
	//발주> 음료리스트 출력
	void stockageList() {

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			List<ProductList> list = dao.getProductList(conn);

			// 재고목록 출력
			System.out.println("====================================");
			System.out.println("재고 목록");
			System.out.println("====================================");
			System.out.println("음료번호 \t 음료이름 \t 수량 ");
			System.out.println("====================================");

			for (ProductList pList : list) {
				System.out.printf("%d \t %s \t %d \n", pList.getItemcode(), pList.getName(), pList.getItemQty());
			}
			System.out.println("====================================");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	
	
	//발주>
	void order() {

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			for (;;) {
				System.out.println("====================================");
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
					System.out.println();

					//주문 내용 저장된 재고 목록 출력
					stockageList();

					//주문 추가 / 주문 종료 선택
					System.out.println("1. 추가 발주 \n2. 관리자 메뉴 \n3. 프로그램 종료");
					int num = sc.nextInt();
					switch (num) {
					case 2 : //관리자 메뉴 리턴
						sal.SaleSelect();
						break;
					case 3 : //프로그램 종료
						System.out.println("프로그램을 정상적으로 종료합니다.");
						System.exit(0);
						break;
					}

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