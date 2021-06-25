package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SalesAmount {
	private TotalSalseDao dao;
	private Scanner sc;

	public SalesAmount(TotalSalseDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}

	// 총매출액 출력 메소드
	// DAO 에서 데이터 리스트를 받고 출력 처리
	void totalList() {
		// Connection 객체 생성 -> 트렌젝션 처리
		Connection conn = null;

		// 2. 연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			List<TotalList> list = dao.gettTotalList(conn);

			System.out.println("판매 매출 리스트");
			System.out.println("————————————————————————————————————————————————");
			System.out.println("음료번호 \t 음료이름 \t 판매수량 \t 총판매금액 ");
			System.out.println("————————————————————————————————————————————————");

			for (TotalList tList : list) {
				System.out.printf("%d \t %s \t %d \t %d \t \n", tList.getItemcode(), tList.getName(),tList.getBuyQty(), tList.getTotalPrice());

			}
			System.out.println("================================================");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}