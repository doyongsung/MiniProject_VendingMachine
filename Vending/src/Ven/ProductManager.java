package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductManager {
	private ProductListDao dao;
	private Scanner sc;
	
	public ProductManager(ProductListDao dao) {
		this.dao = dao;
	}
	
	//전체 리스트 출력 메소드
	// DAO 에서 데이터 리스트를 받고 출력 처리
	void itemList() {
		//Connection 객체 생성 -> 트렌젝션 처리
		Connection conn = null;
		
		// 2.연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";
		
		try {
			conn = DriverManager.getConnection(jdbcUrl,user,pw);
			
			List<ProductList> list = dao.getProductList(conn);
			
			System.out.println("음료 번호 리스트");
			System.out.println("---------------------------------");
			System.out.println("음료번호 \t 음료이름 \t 음료가격 \t 재고");
			System.out.println("---------------------------------");
			
			for(ProductList pl : list) {
				System.out.printf("%d \t %s \t %d \t %d \n", pl.getItemcode(),pl.getName(),pl.getPrice(),pl.getItemQty());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
