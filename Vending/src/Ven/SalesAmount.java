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
		
		ProductManager manager = new ProductManager(ProductListDao.getInstance());
		Connection conn = null;
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			List<TotalList> list = dao.gettTotalList(conn);

			System.out.println("——————————————————— 매출 리스트 ———————————————————");
			System.out.println("————————————————————————————————————————————————");
			System.out.println("음료번호 \t 음료이름 \t  판매수량 \t 총 판매금액 ");
			System.out.println("————————————————————————————————————————————————");

			for (TotalList tList : list) {
				System.out.printf("   %d \t %s \t  %d \t\t %d \n", tList.getItemcode(), tList.getName(), tList.getBuyQty(),
						tList.getTotalPrice());

			}
			
			System.out.println("————————————————————————————————————————————————");
			System.out.println("1. 뒤로가기 \n2. 종료");
			int input = sc.nextInt();
			if (1 == input) {
				manager.SaleSelect();
			} else {
				System.out.println("종료합니다");
				System.exit(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}