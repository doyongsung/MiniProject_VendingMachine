
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

				int result = editDept(conn, coinlist);
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

	int editDept(Connection conn, CoinList coinlist) {

		int result = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "update MONEY set mcount=? where mkey=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coinlist.getMoneyCount());
			pstmt.setInt(2, coinlist.getMoenyKey());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

		return result;
	}

	void getChange(int num) {

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("투입된 금액 동전 분류중..");

			int[] coinlist = { 500, 100 };

			for (int i = 0; i < coinlist.length; i++) {
				if ((num / coinlist[i]) > 0) {

					CoinList coinlist1 = new CoinList(coinlist[i], num / coinlist[i]);

					int result = dao.UpCoin(conn, coinlist1);

					if (result > 0) {
					} else {
						System.out.println("분류 실패");
					}
					num %= coinlist[i];

				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	void CoinList() {

		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";
		int b = 0;
		while (b < 1) {

			;
			try {
				conn = DriverManager.getConnection(jdbcUrl, user, pw);

				List<CoinList> list = dao.getCoinLists(conn);

				System.out.println();
				System.out.println("——————————————————— 동전 리스트 ———————————————————");
				System.out.println("번호 \t 단위 \t\t 갯수 \t\t 총액");
				System.out.println("————————————————————————————————————————————————");

				for (CoinList cl : list) {
					System.out.printf("%d \t %s \t\t %d \t\t %d \n", cl.getMoenyKey(), cl.getMoneyName(),
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