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
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			List<ProductList> list = dao.getProductList(conn);

			System.out.println("————————————————————————————————————————————————");
			System.out.println(" 음료번호 \t\t 음료이름 \t\t 음료가격 ");
			System.out.println("————————————————————————————————————————————————");

			// 써야되는 부분
			for (ProductList pl : list) {
				System.out.printf("   %d\t\t %s \t\t  %d \n", pl.getItemcode(), pl.getName(), pl.getPrice());
			}

		} catch (SQLException e) {
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

			// 1. for 문 밖 ArrayList 선언
			ArrayList<ProductList> buylist = new ArrayList<ProductList>();

			Order order = new Order(ProductListDao.getInstance());

			for (;;) {
				System.out.println("————————————————————————————————————————————————");
				System.out.print("주문> 음료 선택 : ");
				buyCode = sc.nextInt();
				System.out.print("주문> 구매 갯수 : ");
				buyQty = sc.nextInt();
				System.out.println();

				if (getItemQty(buyCode) > buyQty) {

					ProductList numlist = new ProductList(buyCode);
					List<ProductList> list = dao.getBuylist(conn, numlist);

					System.out.println("구매한 음료 \t 구매 개수 \t 총 가격 \t");
					System.out.println("————————————————————————————————————————————————");

					for (ProductList pl : list) {
						totalPrice = pl.getPrice() * buyQty;
						System.out.printf(" %s \t\t  %d \t\t  %d \t\t \n", pl.getName(), buyQty, totalPrice);

						// 2. 구매시 이름, 개수, 총 가격을 buylist에 저장
						buylist.add(new ProductList(pl.getName(), buyQty, totalPrice));
					}

					System.out.println("————————————————————————————————————————————————");
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

						System.out.println("———————————————————— 구매내역 ————————————————————");
						System.out.println("구매한 음료 \t 구매 개수 \t 투입 금액 \t");
						System.out.println("————————————————————————————————————————————————");

						for (ProductList item : buylist) {
							System.out.printf(" %s \t\t  %d \t\t  %d \t\t \n", item.getName(), item.getPrice(),item.getItemQty());

							// 반복문이 동작하며 저장된 가격과 개수를 계속 더하며 총 가격과 총 개수를 구한다.
							tPrice = tPrice + +item.getItemQty();
							tItemQty = tItemQty + +item.getPrice();

							ProductList ProductList = new ProductList(buyCode, tItemQty);
							int result = dao.subtractProductList(conn, ProductList);

							if (result > 0) {

							} else {
								System.out.println("재고 조절 실패");
							}
						}

						System.out.println("————————————————————————————————————————————————");

						// 총 개수와 가격을 곱한 totalPrice를 인자로 동전변환 메소드를 호출한다.
						coin.getChange(totalPrice);
						order.subtract(buyCode, buyQty);
						System.out.println("———————————————————— 구매완료 ————————————————————");
						System.exit(0);
						break;
					}

				} else {
					System.out.println("재고 부족");
					System.out.println("1.추가 구매  \n2.구매 취소 \n3.구매 완료");
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
						System.out.println("————————————————————————————————————————————————");
						coin.getChange(totalPrice);
						order.subtract(buyCode, buyQty);
						System.out.println("———————————————————— 구매완료 ————————————————————");
						System.exit(0);
						break;
					}
				}
			}
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
	}

	// 구매> 재고수량 체크
	int getItemQty(int buyCode) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			ProductList pList = new ProductList(buyCode);
			result = dao.getItemQty(conn, pList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}