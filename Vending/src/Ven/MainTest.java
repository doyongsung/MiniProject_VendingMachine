package Ven;

import java.util.Scanner;


public class MainTest {
public static void main(String[] args) {
	ProductManager manager = new ProductManager(ProductListDao.getInstance());

	Scanner sc = new Scanner(System.in);

		
		System.out.println("1. 음료 구매     2. 관리자. 3. 프로그램 종료");
		int choice = Integer.parseInt(sc.nextLine());
		switch (choice) {
		case 1:
			
			break;
		case 2:
			manager.buyShowinfo(choice);			
			manager.saleshowinfo();
		case 3:
			System.out.println("프로그램을 종료합니다.");
			break;
		}
		
		
	
		
		
		   
	}
}
