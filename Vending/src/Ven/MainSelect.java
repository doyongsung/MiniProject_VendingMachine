package Ven;

public class MainSelect {
<<<<<<< Updated upstream

	public static void main(String[] args) {
		// TODO Auto-generated method stub
=======
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Order o = new Order(ProductListDao.getInstance());
			o.stockageList();	//재고출력 테스트
			o.order();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
>>>>>>> Stashed changes

	}

}
