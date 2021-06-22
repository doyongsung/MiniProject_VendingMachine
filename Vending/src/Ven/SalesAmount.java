package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SalesAmount {
	private ProductListDao dao;
	private Scanner sc;

	public SalesAmount(ProductListDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}

	// 총매출액 출력 메소드
	// DAO 에서 데이터 리스트를 받고 출력 처리
	void totalList() {
		// Connection 객체 생성 -> 트렌젝션 처리
		Connection conn = null;

		// 2. 연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			List<ProductList>list = dao.getProductList(conn);

			System.out.println("총 매출 리스트");
			System.out.println("------------------------------------");
			System.out.println("ItemCode \t 상품이름 \t 상품가격 \t ItemQTY");
			System.out.println("------------------------------------");

			for (ProductList pList : list) {
				System.out.printf("%d \t %s \t %d \t %d \n", 
						pList.getItemcode(), pList.getName(), pList.getPrice(), pList.getItemQty());

			}
			System.out.println("------------------------------------");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
