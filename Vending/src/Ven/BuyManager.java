package Ven;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class BuyManager {
	BuyManager() {

	}
	
  
    //클래스 내부에 인스턴스
//    static private BuyManager buy = new BuyManager();
//    
//    //메소드를 통해 반환
//    public static BuyManager getInstance() {
//   	 return buy;
//    }
   
	private ProductListDao dao;
	private Scanner sc;
	
	Order order = new Order(dao);
	
	
		// 구매자 자판기 음료 리스트
		void buyitemList() {
			Connection conn = null;
			
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
			try {
				conn = DriverManager.getConnection(jdbcUrl, user, pw);
				
				//주문
				for(;;) {
					order.stockageList();
					System.out.println("————————————————————————————————————————————————");
					System.out.print("주문할 음료를 선택해주세요.");
					int orderCode = sc.nextInt();
					System.out.println();
					System.out.println();
					int orderQty = sc.nextInt();
					
					ProductList ProductList = new ProductList(orderCode, orderQty);
					int result = dao.editProductList(conn, ProductList);
				 	
					if(result > 0) {
						System.out.println("주문 완료");
						System.out.println();
						System.out.println();
						
						// 3. 주문 내용이 저장된 재고 목록 출력
						order.stockageList();

						// 4. 주문 추가 / 주문 종료 선택
						System.out.println("1. 추가 주문 \n2. 종료");
						int num = sc.nextInt();
						if(num==2) {
							System.out.println("주문을 종료합니다.");
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