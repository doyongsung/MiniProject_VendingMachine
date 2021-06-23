package Ven;

import java.util.Scanner;

public class SaleSelect {

	void SaleSelect() {
//		CoinSearch coinSearch = new CoinSearch(CoinDao.getInstance());
		Scanner sc = new Scanner(System.in);

		System.out.println("부서관리 프로그램");
		System.out.println("-----------------------------------");
		System.out.println("1. 부서 리스트");
		System.out.println("2. 부서 정보 입력");
		System.out.println("-----------------------------------");
		System.out.println("원하시는 기능의 번호를 입력해주세요.");
		int num = Integer.parseInt(sc.nextLine());

		switch (num) {
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:
			System.out.println("프로그램을 종료합니다.");
			return;

		}

	}

}
