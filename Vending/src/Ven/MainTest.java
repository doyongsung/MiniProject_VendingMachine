package Ven;

import java.util.Scanner;


public class MainTest {
public static void main(String[] args) {

	SalesAmount amount = new SalesAmount(ProductListDao.getInstance());
		Scanner sc = new Scanner(System.in);
		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			while (true) {
			System.out.println("자판기 판매자 기능");
			System.out.println("------------------------------------");
			System.out.println("1. 총매출");
		
			int num = Integer.parseInt(sc.nextLine());
			
			switch(num) {
			case 1 :
				amount.totalList();
				break;

			case 5 :
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			
	
			}
		

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
}
}
