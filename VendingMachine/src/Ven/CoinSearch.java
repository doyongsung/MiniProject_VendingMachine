
package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CoinSearch {

	Scanner scanner = new Scanner(System.in);
	Connection conn = null;
	ProductManager manager = new ProductManager(ProductListDao.getInstance());
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pw = "tiger";

	private CoinDAO dao;

	public CoinSearch(CoinDAO dao) {
		this.dao = dao;

	}

	void CoinChange(int num) {

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			System.out.println("동전을 정리합니다");

			int editData = scanner.nextInt();

			if (editData > 0) {
				CoinList coinlist = new CoinList(num, editData);

				int result = dao.editCoin(conn, coinlist);
				if (result > 0) {
					System.out.println("동전 정리");
				} else {
					System.out.println("동전 정리 실패");
				}
			} else {
				System.out.println("올바른 정리를 부탁합니다.");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// 넘겨 받은 totalPrice를 매개변수 tPrice로 받는다.
	void getChange(int tPrice) {

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("투입된 금액 동전 분류중..");

			// 선언한 배열
			int[] coinlist = { 500, 100 };

			// 선언한 배열 coinlist의 길이 만큼 반복
			for (int i = 0; i < coinlist.length; i++) {

				// 배열의 i번째 값 나누기 매개변수 tPrice로 나눈 값 즉, 아직 tPrice가 존재 할때 존재 할때 동작
				if ((tPrice / coinlist[i]) > 0) {

					// Coinlist 에 i번째 배열(동전의 가치)과 나눈 값(동전의 개수)을 저장
					CoinList coinlist1 = new CoinList(coinlist[i], tPrice / coinlist[i]);

					// dao.Upcoin 으로 인자 전달
					int result = dao.UpCoin(conn, coinlist1);

					// result가 올바르게 반환 되었는지 확인
					if (result > 0) {
					} else {
						System.out.println("분류 실패");
					}

					// tPrice의 나머지 값, 잔돈 구하기
					tPrice %= coinlist[i];

				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// 코인 리스트 출력
	void CoinList() {

		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";
		int b = 0;
		while (b < 1) {

			;
			try {
				conn = DriverManager.getConnection(jdbcUrl, user, pw);

				
				//dao.getCoinLists 의 결과값을 list에 저장
				List<CoinList> list = dao.getCoinLists(conn);

				System.out.println();
				System.out.println("——————————————————— 동전 리스트 ———————————————————");
				System.out.println("번호 \t 단위 \t\t 갯수 \t\t 총액");
				System.out.println("————————————————————————————————————————————————");

				//list를 호출
				for (CoinList cl : list) {
					System.out.printf("%d \t %s \t\t %d \t\t %d \n", 
							cl.getMoenyKey(), cl.getMoneyName(),
							cl.getMoneyCount(), cl.getMoenyAll());
				}

				System.out.println("————————————————————————————————————————————————");

				System.out.println("1. 오백원 관리");
				System.out.println("2. 백원 관리");
				System.out.println("3. 뒤로 가기");
				System.out.println("입력 >>");
				int num = scanner.nextInt();

				switch (num) {
				case 1:
					CoinChange(num);
					break;
				case 2:
					CoinChange(num);
					break;
				case 3:

					manager.SaleSelect();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
	}
}