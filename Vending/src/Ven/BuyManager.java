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
		int tPrice = 0;
		int tItemQty = 0;
		CoinSearch coin = new CoinSearch(CoinDAO.getInstance());
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

				if (getItemQty(buyCode) > buyQty) {

					ProductList numlist = new ProductList(buyCode);
					List<ProductList> list = dao.getBuylist(conn, numlist);

					System.out.println("구매한 음료 이름 \t 구매한 음료 개수 \t 구매한 총 가격 \t");
					System.out.println("-----------------------------------------------");

					for (ProductList pl : list) {
						totalPrice = pl.getPrice() * buyQty;

						System.out.printf("%s \t\t %d \t\t %d \t\t \n", pl.getName(), buyQty, totalPrice);
						buylist.add(new ProductList(pl.getName(), buyQty, totalPrice));

					}

					System.out.println("-----------------------------------------------");

					System.out.println("1. 추가 구매 \n2. 구매 취소 \n3. 구매 완료");
					int num = sc.nextInt();
					inputBuyData();
					switch (num) {
					case 1:
						buyitemList();
						break;
					case 2:
						MainTest.main(null);
						break;
					case 3:

						System.out.println("----------------- 구매 내역 ---------------------");
						System.out.println("구매한 음료 이름 \t 구매한 음료 개수 \t 투입한 금액 \t");
						System.out.println("-----------------------------------------------");

						for (ProductList item : buylist) {
							System.out.printf("%s \t\t %d \t\t %d \t\t \n", item.getName(), item.getPrice(),
									item.getItemQty());

							tPrice = tPrice + +item.getItemQty();
							tItemQty = tItemQty + +item.getPrice();

							ProductList ProductList = new ProductList(buyCode, tItemQty);
							int result = dao.subtractProductList(conn, ProductList);
							
							if (result > 0) {

							} else {
								System.out.println("재고 조절 실패");
							}
						}

						System.out.println("-----------------------------------------------");
						coin.getChange(tPrice);
						System.out.println("----------------- 구매 완료 ---------------------");
						System.exit(0);
						break;
					}

				} else {
					System.out.println("재고 부족");
					System.out.println("1. 추가 구매  \n2.구매 취소 \n3.구매 완료");
					int num = sc.nextInt();
					switch (num) {
					case 2:
						MainTest.main(null);
						break;
					case 3:
						for (ProductList item : buylist) {
							System.out.printf("%s %d", item.getName(), item.getPrice(), item.getPrice());
							System.out.println("");
						}
						System.out.println("-----------------------------------------------");
						coin.getChange(totalPrice);
						System.out.println("----------------- 구매 완료 ---------------------");
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