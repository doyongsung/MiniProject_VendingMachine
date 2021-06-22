package Ven;

import java.util.Scanner;

public class MainTest {
public static void main(String[] args) {
	
	ProductManager manager = new ProductManager(ProductListDao.getInstance());
	
	Scanner sc = new Scanner(System.in);
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		while(true) {
			System.out.println("자판기 프로그램");
			System.out.println("-------------------------------");
			System.out.println("1.음료 리스트");
			System.out.println("2.음료 정보 등록");
			System.out.println("3.음료 수정");
			System.out.println("4.음료 정보 삭제");
			System.out.println("5.프로그램 종료");
			System.out.println("-----------------------------------");
			System.out.println("원하시는 기능의 번호를 입력해주세요.");
			int num = Integer.parseInt(sc.nextLine());
		
		switch(num) {
		case 1:
			manager.itemList();
			break;
		case 2:
			manager.inputData();
			break;			
		case 3:
			manager.editItem();
			break;
		case 4:
			break;
		case 5:
			System.out.println("프로그램을 종료합니다.");
			return;
		}
		}
	
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
