package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuyManager {
	private ProductListDao dao;
	private Scanner sc;

	int buyCode;
	int buyQty;
	int totalPrice;
	int itemQty;
	int result;

	// 연결
	Connection conn = null;
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pw = "tiger";

	public BuyManager(ProductListDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}

	// 구매> 음료 리스트 출력
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

			// 써야되는 부분

			for (ProductList pl : list) {
				System.out.printf("%d \t %s \t %d \n", pl.getItemcode(), pl.getName(), pl.getPrice());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 구매> 구매진행
	void buy() {
		Scanner sc = new Scanner(System.in);
		ProductManager pm = new ProductManager(ProductListDao.getInstance());
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			ArrayList<ProductList> buylist = new ArrayList<ProductList>();
			for (;;) {

				System.out.println("---------------------------------");
				System.out.print("주문> 음료 선택 : ");
				buyCode = sc.nextInt();
				System.out.print("주문> 구매 갯수 : ");
				buyQty = sc.nextInt();
				System.out.println();
				pm.buyList(buyCode, buyQty);

				if (getItemQty(buyCode) > buyQty) {

					ProductList numlist = new ProductList(buyCode);
					List<ProductList> list = dao.getBuylist(conn, numlist);

					System.out.println("음료이름 \t 음료가격 \t");
					System.out.println("---------------------------------");

					for (ProductList pl : list) {
						totalPrice = pl.getPrice() * buyQty;

						System.out.printf("%s \t %d \t \n", pl.getName(), totalPrice);
						buylist.add(new ProductList(pl.getName(), totalPrice));

					}

					System.out.println("---------------------------------");
					System.out.println("1. 추가구매 \n2. 이전 메뉴 \n3. 프로그램 종료");
					int num = sc.nextInt();
					inputBuyData();
					switch (num) {
					case 2:
						MainTest.main(null);
						break;
					case 3:
						for (ProductList item : buylist) {
							System.out.printf("%s %d", item.getName(), item.getPrice());
							System.out.println("");
						}
						System.out.println("프로그램 종료");
						System.exit(0);
						break;
					}
					if (num == 1) {
						buyitemList();
					} else {
						break;
					}
				} else {
					System.out.println("재고 부족");
					System.out.println("1.음료 선택 \n2.이전 메뉴 \n3.프로그램 종료");
					int num = sc.nextInt();
					switch (num) {
					case 2:
						MainTest.main(null);
						break;
					case 3:
						System.out.println("프로그램 종료");
						System.exit(0);
						break;
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 구매> 구매내역 저장
	void inputBuyData() {

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			BuyList bList = new BuyList(buyQty, totalPrice, buyCode);
			int result = dao.insertBuyInfo(conn, bList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 재고수량 체크
	int getItemQty(int buyCode) {
		int result = 0;

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			ProductList pList = new ProductList(buyCode);
			result = dao.getItemQty(conn, pList);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

}