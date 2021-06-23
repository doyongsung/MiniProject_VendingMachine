//package Ven;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Scanner;
//
//public class Order {
//	private ProductListDao dao;
//	private Scanner sc;
//	ProductManager mg = new ProductManager(ProductListDao.getInstance());
//	
//	public Order(ProductListDao dao) {
//		this.dao = dao;
//		sc = new Scanner(System.in);
//	}
//
//	
//	
//	// 1. 재고목록(제품명, 수량) 출력
//	void stockageList() {
//		Connection conn = null;
//		
//		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
//		String user = "hr";
//		String pw = "tiger";
//		
//		try {
//			conn = DriverManager.getConnection(jdbcUrl, user, pw);
//			List<ProductList> list = dao.getProductList(conn);
//			
//			//재고목록 출력
//			System.out.println("====================================");
//			System.out.println("재고 목록");
//			System.out.println("====================================");
//			System.out.println("음료번호 \t 음료이름 \t 수량 ");
//			System.out.println("====================================");
//			
//			for(ProductList pList : list) {
//				System.out.printf("%d \t %s \t %d \n",
//						pList.getItemcode(),
//						pList.getName(),
//						pList.getItemQty());
//			}
//			System.out.println("====================================");			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	
//	
//	// 2. 주문받기 : 이름, 수량
//	void order() {
//		Connection conn = null;
//		
//		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
//		String user = "hr";
//		String pw = "tiger";
//		
//		try {
//			conn = DriverManager.getConnection(jdbcUrl, user, pw);
//			
//			//주문
//			System.out.println("====================================");
//			System.out.println("발주 > \t 음료이름 수량 입력");
//			System.out.println("====================================");
//			String orderData = sc.nextLine();
//			String[] oData = orderData.split(" ");
//			System.out.println("====================================");
//			
//			ProductList pList = new ProductList(
//					Integer.parseInt(orderData[0]),
//					orderData[1],
//					Integer.parseInt(orderData[2]),
//					Integer.parseInt(orderData[3]));
//			
//			int result = dao.editProductList(conn, pList);
//		 	
//			if(result > 0) {
//				System.out.println("주문 완료");
//			} else {
//				System.out.println("주문 실패");
//			}
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	 
//	
//	
//	// 3. 주문 내용이 저장된 재고 목록 출력
//	// 4. 주문 추가 / 주문 종료 선택
//	
//	
//	
//	
//	
//
//}
