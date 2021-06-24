package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductManager {
	private ProductListDao dao;
	private Scanner sc;

	public ProductManager(ProductListDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}

	// 전체 리스트 출력 메소드
	// DAO 에서 데이터 리스트를 받고 출력 처리
	void itemList() {
		// Connection 객체 생성 -> 트렌젝션 처리
		Connection conn = null;

		// 2.연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			List<ProductList> list = dao.getProductList(conn);

			System.out.println("음료 번호 리스트");
			System.out.println("---------------------------------");
			System.out.println("음료번호 음료이름 음료가격 재고");
			System.out.println("---------------------------------");

			for (ProductList pl : list) {
				System.out.printf("%d \t %s \t %d \t %d \n", pl.getItemcode(), pl.getName(), pl.getPrice(),
						pl.getItemQty());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("잘못된정보입니다. 다시입력해주세요");
		}

	}

	void inputData() {

		Connection conn = null;

		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("음료 정보를 입력합니다.");
			System.out.println("음료이름 음료가격 재고 순으로 입력해주세요");
			System.out.println("ex) 음료수 2000 20");
			String inputData = sc.nextLine();
			String[] deptdata = inputData.split(" ");

			ProductList pList = new ProductList(0, deptdata[0], Integer.parseInt(deptdata[1]),
					Integer.parseInt(deptdata[2]));

			int result = dao.insertProductList(conn, pList);

			if (result > 0) {
				System.out.println("입력되었습니다.");
			} else {
				System.out.println("입력 실패");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("잘못된정보입니다. 다시입력해주세요");
		}

	}

	void editItem() {
		Connection conn = null;

		// 2.연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("음료 정보를 수정합니다.");
			System.out.println("음료번호 음료이름 음료가격 재고 순으로 입력해주세요");
			String editData = sc.nextLine();
			String[] eData = editData.split(" ");

			ProductList productList = new ProductList(Integer.parseInt(eData[0]), eData[1], Integer.parseInt(eData[2]),
					Integer.parseInt(eData[3]));

			int result = dao.editProductList(conn, productList);

			if (result > 0) {
				System.out.println("수정되었습니다.");
			} else {
				System.out.println("수정실패!!!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("잘못된정보입니다. 다시입력해주세요");
		}

	}

	void saleShowinfo() {
		ProductManager manager = new ProductManager(ProductListDao.getInstance());

		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			int a = 0;
			while (a <= 1) {
				System.out.println("");
				System.out.println("---------- 음료 재고 목록 조회 ---------");
				System.out.println("1.음료 리스트"); // 바로 출력
				System.out.println("2.음료 정보 등록");
				System.out.println("3.음료 수정");
				System.out.println("4.음료 정보 삭제");
				System.out.println("5.프로그램 종료");
				System.out.println("-----------------------------------");
				System.out.println("원하시는 기능의 번호를 입력해주세요.");
				int num = Integer.parseInt(sc.nextLine());

				switch (num) {
				case 1:
					manager.itemList();
					break;
				case 2:
					manager.inputData();
					break;
				case 3:
					manager.update();
					break;
				case 4:
					manager.delproduct();
					break;
				case 5:
					a = 2;
					break;
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("잘못된정보입니다. 다시입력해주세요");
		}
	}

	void Manager(int choice) {
		int i = 1;

		System.out.println("관리자 프로그램으로 접속합니다.");
		while (i <= 3) {
			System.out.println("-----------------------------------------------");
			System.out.println("로그인");
			System.out.print("id를 입력해주세요 >> ");
			String id = sc.nextLine();
			System.out.print("password를 입력해주세요 >> ");
			String password = sc.nextLine();
			boolean isRightPass = (id.equals("hr") || password.equals("tiger"));
			if (id.equals(id) && isRightPass) {
				System.out.println();
				System.out.println("로그인 성공입니다.");
				break;
			} else {
				System.out.println("-----------------------------------------------");
				System.out.println("id와 password가 틀렸습니다. 다시 입력해주세요");
				System.out.println("-----------------------------------------------");
				System.out.println();
				i++;
			}
			if (i == 4) {
				System.out.println("[3회 모두 틀렸습니다. 다시 접속해주세요.]");
				System.exit(0);
			}

		}

	}

	void update() {
	       Connection conn = null;
	         
	         // 2.연결
	         String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	         String user = "hr";
	         String pw = "tiger";
	         
	         try {
	            conn = DriverManager.getConnection(jdbcUrl,user,pw);
	            
	            System.out.println("음료 정보를 수정합니다.");
	            System.out.println("음료번호 음료이름 음료가격 재고 순으로 입력해주세요");
	            String editData = sc.nextLine();
	            
	            String[] eData = editData.split(" ");
	            
	            ProductList productList = new ProductList(Integer.parseInt(eData[0]), eData[1],  
	                  Integer.parseInt(eData[2]),Integer.parseInt(eData[3]));
	            
	            int result = dao.updateProductList(conn, productList);
	            
	            if(result > 0) {
	               System.out.println("수정되었습니다.");
	            }else {
	               System.out.println("수정실패!!!");
	            }
	            
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }catch (NumberFormatException e) {
	            System.out.println("잘못된정보입니다. 다시입력해주세요");
	         }
	   }
	
	// 삭제
	void delproduct() {
		// Connection 객체 생성 -> 트렌젝션 처리
		Connection conn = null;

		// 2. 연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			itemList();
			System.out.println("삭제를 원하시는 음료 번호를 입력해주세요.");
			int productList = Integer.parseInt(sc.nextLine());

			int result = dao.deleteProduct(conn, productList);

			if (result > 0) {
				System.out.println("삭제되었습니다.");
			} else {
				System.out.println("해당 부서의 정보가 없습니다.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("잘못된정보입니다. 다시입력해주세요");
		}
	}

//전체 리스트 출력 메소드
	// DAO 에서 데이터 리스트를 받고 출력 처리
	void buyList(int num) {
		// Connection 객체 생성 -> 트렌젝션 처리
		Connection conn = null;
		Scanner sc = new Scanner(System.in);
		// 2.연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("주문 하실 음료의 수량 입력해 >>");
			int num2 = sc.nextInt();

			ProductList numlist = new ProductList(num);

			List<ProductList> list = dao.getBuylist(conn, numlist);

			System.out.println("음료이름 \t 음료가격 \t");
			System.out.println("---------------------------------");

			for (ProductList pl : list) {
				System.out.printf("%s \t %d \t \n", pl.getName(), (pl.getPrice() * num2));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("잘못된정보입니다. 다시입력해주세요");
		}

	}

}