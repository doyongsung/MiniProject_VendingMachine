package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
<<<<<<< Updated upstream
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> Stashed changes
import java.util.Scanner;
//
public class BuyManager {

	private ProductListDao dao;
	private Scanner sc;
	

	int buyCode;
	int buyQty;
	int totalPrice;


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
	
		

		

	// 구매> 음료 리스트
	void buyitemList() {
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
	
	
	
	
	
	
	// 구매>
	void buy() {
		Connection conn = null;
		Scanner sc = new Scanner(System.in);
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			
			for(;;) {
				
				System.out.println("---------------------------------");
				System.out.print("주문> 음료 선택 : ");
				buyCode = sc.nextInt();
				System.out.print("주문> 구매 갯수 : ");
				buyQty = sc.nextInt();
				System.out.println();
				
				ProductList numlist = new ProductList(buyCode);
	
				List<ProductList> list = dao.getBuylist(conn, numlist);
	
				System.out.println("음료이름 \t 음료가격 \t");
				System.out.println("---------------------------------");
				
				
				for (ProductList pl : list) {
					totalPrice = pl.getPrice() * buyQty;
					System.out.printf("%s \t %d \t \n", pl.getName(), totalPrice);
				}
				
				System.out.println("---------------------------------");
				System.out.println("1. 추가구매 \n2. 프로그램 종료");
				int num = sc.nextInt();
				inputBuyData();
				if(num==1) {
					buyitemList();
				} else {

					break;
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	//구매내역 저장 
	void inputBuyData() {
		
		Connection conn = null;
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			
			BuyList bList = new BuyList(buyQty, totalPrice, buyCode);
			
			int result = dao.insertBuyInfo(conn, bList);
			
			if(result > 0) {
				System.out.println("구매내용 저장");
			} else {
				System.out.println("구매내용 저장 실패");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}