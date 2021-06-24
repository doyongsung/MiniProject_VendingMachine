package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CoinSearch {

	private CoinDAO dao;
	private Scanner sc1;

	public CoinSearch(CoinDAO dao) {
		this.dao = dao;
		sc1 = new Scanner(System.in);
	}

	void CoinChange(int num) {

		Scanner scanner = new Scanner(System.in);

		// Connection 객체 생성 -> 트렌젝션 처리
		Connection conn = null;

		// 2. 연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			System.out.println("동전 갯수를 수정합니다.");

			int editData = scanner.nextInt();

			CoinList coinlist = new CoinList(num,editData);

			int result = dao.editDept(conn, coinlist); // 수정

			if (result > 0) {
				System.out.println("수정되었습니다.");
			} else {
				System.out.println("수정실패!!!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 전체 리스트 출력 메소드
	// DAO 에서 데이터 리스트를 받고 출력 처리
	void CoinList() {
		// Connection 객체 생성 -> 트렌젝션 처리
		Connection conn = null;
		Scanner scanner = new Scanner(System.in);
		CoinSearch coinsearch = new CoinSearch(dao.getInstance());

		// 2.연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";
		int b = 0;
		while (b < 1) {

			;
			try {
				conn = DriverManager.getConnection(jdbcUrl, user, pw);

				List<CoinList> list = dao.getCoinLists(conn);

				System.out.println("========== 동전 리스트 ==========");
				System.out.println("번호 \t 단위 \t 갯수 \t 총액");

				for (CoinList cl : list) {
					System.out.printf("%d \t %s \t %d \t %d \n", cl.getMoenyKey(), cl.getMoneyName(),
							cl.getMoneyCount(), cl.getMoenyAll());

				}

				System.out.println("==============================");

				System.out.println("1. 오백원 관리");
				System.out.println("2. 백원 관리");
				System.out.println("3. 프로그램 종료");
				System.out.println("입력 >>");
				int num = scanner.nextInt();

				switch (num) {
				case 1:
					coinsearch.CoinChange(num);
					break;
				case 2:
					coinsearch.CoinChange(num);
					break;
				case 3:
					b = 3;
					break;

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
