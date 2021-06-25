package Ven;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainSelect {

	void main() {
	ProductManager manager = new ProductManager(ProductListDao.getInstance());
	SaleSelect saleselect = new SaleSelect();
	BuyOption buy = new BuyOption();
    Order order = new Order(ProductListDao.getInstance());
    BuyManager bManager = new BuyManager(ProductListDao.getInstance());
    //order.subtract();
	Scanner sc = new Scanner(System.in);
	System.out.println("================================================");
	System.out.println("================ 자판기 프로그램 =====================");
	System.out.println("================================================");
	System.out.println();
	 while (true) {
		try {

			System.out.println("—————————————————— 모드 선택 ———————————————————————");
			System.out.println("1. 음료 구매         2. 관리자         3. 프로그램 종료");
			System.out.println("————————————————————————————————————————————————");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				//buy.VendingMachine();
				
				bManager.buyitemList();
				bManager.buy();
		       break;

			case 2:
				manager.Manager(choice);
				manager.SaleSelect();
				break;
			case 3:
				System.out.println("프로그램을 정상적으로 종료합니다.");
				System.exit(0);
			}


}catch (InputMismatchException  e) {	
 System.out.println("잘못된입력입니다. 다시입력해주세요");
  sc = new Scanner(System.in);
}

}	

   
}
}