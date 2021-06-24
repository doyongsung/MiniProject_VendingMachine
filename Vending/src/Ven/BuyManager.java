package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
//
public class BuyManager {

	private ProductListDao dao;
	private Scanner sc;
	
	public BuyManager(ProductListDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}
//	 //돈과 음료를 선택
//    //{콜라 : 2,000, 사이다 : 1,000, 주스 : 1,000, 생수 : 500}
//    Scanner scan = new Scanner(System.in);
//    String item;
//    int money = 0;
//    int insert = 0;
//    int result = 0;
//
//    System.out.println("음료를 골라주세요!(콜라, 사이다, 주스, 생수)");
//    item = scan.nextLine();
//    System.out.println("선택한 음료 : " + item);
//    if(item.equals("콜라")) {
//        System.out.println("콜라는 2,000원 입니다.");
//        money = 2000;
//    } else if(item.equals("사이다")) {
//        System.out.println("사이다는 1,000원 입니다.");
//        money = 1000;
//    } else if(item.equals("주스")) {
//        System.out.println("주스는 1,000원 입니다.");
//        money = 1000;
//    } else if(item.equals("생수")) {
//        System.out.println("생수는 500원 입니다.");
//        money = 500;
//    }
//     
//    System.out.println("투입할 돈을 입력해주세요!");
//    insert = Integer.parseInt(scan.nextLine());
//    if(insert > money) {
//        result = insert - money;
//        System.out.println("잔돈은 " + result + "원 입니다. " + item + "을(를) 아래에서 가져가세요.\n제가 가지겠습니다. 안녕히 가십시오.");
//    } else if(insert == money) {
//        System.out.println("잔돈은 0원 입니다. " + item + "을(를) 아래에서 가져가세요. 안녕히 가십시오.");
//    } else if(insert < money) {
//        result = money - insert;
//        System.out.println("돈이 " + result + "원 만큼 부족합니다.");
//    }
//    
	
		

		void buy() {
			ProductManager manager = new ProductManager(ProductListDao.getInstance());
			Scanner sc = new Scanner(System.in);
			Connection conn = null;
			
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";

			
			try {
				conn = DriverManager.getConnection(jdbcUrl,user,pw);
				
				System.out.println("");
				System.out.println("구매하실 음료번호를 입력해주세요");
				int input1 = sc.nextInt();
				System.out.println("구매하실 갯수를 입력해주세요");
				int input2 = sc.nextInt();
				
				ProductList pList = new ProductList(input1,input2);
				int result = dao.insertProductList(conn, pList);
				
				if(result > 0) {
					System.out.println("입력되었습니다.");
					System.out.println();
				
					manager.itemList();
					
				}else {
					System.out.println("입력실패");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}


		
			
}