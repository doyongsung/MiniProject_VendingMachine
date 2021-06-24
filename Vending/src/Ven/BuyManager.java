package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BuyManager {
	private ProductListDao dao;
	private Scanner sc;

	public BuyManager(ProductListDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}

	// 구매자 자판기 음료 리스트
	void buyitemList() {
		Connection conn = null;

		// 2.연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			List<ProductList> list = dao.getProductList(conn);

			System.out.println("오늘도 저희 자판기를 찾아주셔서 감사합니다^^*");
			System.out.println("-----------------------------------");
			System.out.println("음료번호 \t 음료이름 \t 음료가격 ");
			System.out.println("-----------------------------------");

			// 써야되는 부분

			for (ProductList pl : list) {
				System.out.printf("%d \t %s \t %d \n", pl.getItemcode(), pl.getName(), pl.getPrice());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
