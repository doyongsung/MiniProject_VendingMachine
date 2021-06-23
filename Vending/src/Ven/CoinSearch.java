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

	// 전체 리스트 출력 메소드
	// DAO 에서 데이터 리스트를 받고 출력 처리
	void CoinList() {
		// Connection 객체 생성 -> 트렌젝션 처리
		Connection conn = null;

		// 2.연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			List<CoinList> list = dao.getCoinLists(conn);

			System.out.println("========== 동전 리스트 ==========");
			System.out.println("번호 \t 오백원 \t 백원 \t 총액");
			for (CoinList cl : list) {
				System.out.printf("%d \t %d \t %d \t %d \n", cl.getMoenyKey(), cl.getOback(), cl.getBack(),
						cl.getMoenyAll());
			}
			
			System.out.println("==============================");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
