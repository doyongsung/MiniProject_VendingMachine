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

//	void SaleSelect() {
//
//		ProductManager manager = new ProductManager(ProductListDao.getInstance());
//		CoinSearch coin = new CoinSearch(CoinDAO.getInstance());
//		SalesAmount totalSale = new SalesAmount(TotalSalseDao.getInstance());
//		Order order = new Order(ProductListDao.getInstance());
//
//		Scanner sc = new Scanner(System.in);
//
//		System.out.println("------------- 관리자 모드 ------------");
//		System.out.println("1. 음료 재고 목록 조회");
//		System.out.println("2. 자판기 내 잔액 조회");
//		System.out.println("3. 음료 판매량 조회");
//		System.out.println("4. 음료 발주");
//		System.out.println("-----------------------------------");
//		System.out.println("원하시는 기능의 번호를 입력해주세요.");
//		int num = Integer.parseInt(sc.nextLine());
//
//		switch (num) {
//		case 1:
//			manager.saleShowinfo();
//			break;
//		case 2:
//			coin.CoinList();
//			break;
//		case 3:
//			totalSale.totalList();
//			break;
//		case 4:
//
//			order.stockageList();
//			order.order();
//			return;
//
//		}

//	}


}
