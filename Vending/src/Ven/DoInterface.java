package Ven;

public interface DoInterface {

		default void Product1() {
		System.out.println("———————————————————관리자 모드—————————————————————");
		System.out.println();
		System.out.println(" 1. 음료 재고 목록 조회");
		System.out.println(" 2. 자판기 내 잔액 조회");
		System.out.println(" 3. 음료 판매량 조회");
		System.out.println(" 4. 음료 발주");
		System.out.println(" 5. 뒤로 가기");
		System.out.println();
		System.out.println("————————————————————————————————————————————————");
		}
		
		default void Product2() {
			ProductManager manager = new ProductManager(ProductListDao.getInstance());
			System.out.println();
			System.out.println("———————————————————관리자 모드—————————————————————");
			manager.itemList();
			System.out.println(" 1.음료 추가");
			System.out.println(" 2.음료 수정");
			System.out.println(" 3.음료 삭제");
			System.out.println(" 4.뒤로 가기");
			System.out.println(" 5.프로그램 종료");
			System.out.println("————————————————————————————————————————————————");
		}
		
		default void List() {
			System.out.println("——————————————————— 음료 리스트 ———————————————————");
			System.out.println("————————————————————————————————————————————————");
			System.out.println("음료번호 \t 음료이름 \t\t 음료가격 \t\t 재고");
			System.out.println("————————————————————————————————————————————————");
		}
		
		default void Data() {
			System.out.println("음료 정보를 입력합니다.");
			System.out.println("음료이름 음료가격 재고 음료번호 순으로 입력해주세요");
			System.out.println("ex) 음료수 2000 20 ");
		}
		
		default void updata() {
			System.out.println("음료 정보를 수정합니다.");
			System.out.println("음료번호 음료이름 음료가격 재고 순으로 입력해주세요");
		}
}
