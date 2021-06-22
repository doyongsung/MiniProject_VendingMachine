package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductManager {
	private ProductListDao dao;
	private Scanner sc;
	
	public ProductManager(ProductListDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
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
     void inputData() {
		
		Connection conn = null;

		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			
			System.out.println("음료 정보를 입력합니다.");
			System.out.println("음료이름 음료가격 재고 음료번호 순으로 입력해주세요");
			System.out.println("ex) 음료수 2000 20 1");
			String inputData = sc.nextLine();
			String[] deptdata = inputData.split(" ");
			
			ProductList pList = new ProductList(0, deptdata[0], 1, 2);
			
			int result = dao.insertProductList(conn, pList);
			
			if(result > 0) {
				System.out.println("입력되었습니다.");
			} else {
				System.out.println("입력 실패");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void editItem() {
        Connection conn = null;
		
		// 2.연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";
		
		try {
			conn = DriverManager.getConnection(jdbcUrl,user,pw);
			
			System.out.println("음료 정보를 수정합니다.");
			System.out.println("음료번호 음료이름 음료가격 재고 순으로 입력해주세요");
			String editData = sc.nextLine();
			String[] eData = editData.split(" ");
			
			ProductList productList = new ProductList(Integer.parseInt(eData[0]), eData[1],  
					Integer.parseInt(eData[2]),Integer.parseInt(eData[3]));
			
			int result = dao.editProductList(conn, productList);
			
			if(result > 0) {
				System.out.println("수정되었습니다.");
			}else {
				System.out.println("수정실패!!!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	

}
