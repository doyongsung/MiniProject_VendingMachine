package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

	public class Buy {
		private ProductListDao dao;
		private Scanner sc;

		public Buy(ProductListDao dao) {
			this.dao = dao;
			sc = new Scanner(System.in);
		}

		// 자판기 구매 메소드
		// DAO 에서 데이터 리스트를 받고 출력 처리
		void vendingMachineList() {
			BuyManager manager = new BuyManager(ProductListDao.getInstance());
			ProductManager pManager = new ProductManager(ProductListDao.getInstance());
			
			// Connection 객체 생성 -> 트렌젝션 처리
			Connection conn = null;

			// 2. 연결
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			try {
				conn = DriverManager.getConnection(jdbcUrl, user, pw);

				List<ProductList>list = dao.getProductList(conn);

				System.out.println();
				System.out.println("---------- 아주시원한 자판기 -----------");
				manager.buyitemList();
				System.out.println("-----------------------------------");
				System.out.println("원하시는 음료 번호를 입력해주세요.");
				int num = Integer.parseInt(sc.nextLine());
				

				for (ProductList pList : list) {
					System.out.printf("%d \t %s \t %d \t %d \n", 
							pList.getItemcode(), pList.getName(), pList.getPrice(), pList.getItemQty());

				}
				System.out.println("------------------------------------");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}



