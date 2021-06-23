package Ven;

import java.util.InputMismatchException;
import java.util.Scanner;


public class MainTest {
public static void main(String[] args) {
	ProductManager manager = new ProductManager(ProductListDao.getInstance());
	BuyOption bo = new BuyOption();
	Scanner sc = new Scanner(System.in);	
	System.out.println("================================================");
	System.out.println("================ 자판기 프로그램 ===================");
	System.out.println("================================================");
	System.out.println();
	while(true) {
try {
	
	
	System.out.println("—————————————————— 모드 선택 —————————————————————");
	System.out.println("1. 음료 구매   2. 관리자    3. 프로그램 종료");
	System.out.println("————————————————————————————————————————————————");
		int choice = sc.nextInt();
		
		switch (choice) {
		case 1:
		bo.VendingMachine();
			break;
		case 2:
			manager.Manager(choice);			
			manager.saleShowinfo();
		case 3:
			System.out.println("프로그램을 종료합니다.");
			break;
		}

}catch (InputMismatchException  e) {	
    System.out.println("잘못된입력입니다. 다시입력해주세요");
      sc = new Scanner(System.in);
}

	}	

	   
	}
}
