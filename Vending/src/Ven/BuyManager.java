package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BuyManager {
	private ProductListDao dao;
	private BuyDao bDao;
	private Scanner sc;
	
	public BuyManager(ProductListDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}
	
	public BuyManager(BuyDao bDao) {
		this.bDao = bDao;
		sc = new Scanner(System.in);
	}
	
		// 음료 리스트 출력 (select)
		void buyitemList() {
			Connection conn = null;

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

				for (ProductList pl : list) {
					System.out.printf("%d \t %s \t %d \n",pl.getItemcode(),pl.getName(), pl.getPrice());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		

		
		// 구매 내역 저장 (insert)
		void buyInput() {
			Connection conn = null;

			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
			try {
				conn = DriverManager.getConnection(jdbcUrl, user, pw);
				
				System.out.println("====================================");
				System.out.print("주문> \t 음료코드 입력 : ");
				int buyCode = sc.nextInt();
				System.out.println();
				System.out.println("주문> \t 구매 수량 입력 : ");
				int buyQty = sc.nextInt();
				System.out.println();
				
				BuyList bList = new BuyList(buyCode, buyQty);
				int result = 
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
		}
		
		
		
		
		// 재고 수량 수정 (update)
		
		
		
		
		
		
		
		
		
		
		
		
		
}