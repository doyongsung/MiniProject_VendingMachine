//package Ven;
//
//import java.util.Scanner;
//
//public class BuyOption {
//	void VendingMachine() {
//		ProductManager manager = new ProductManager(ProductListDao.getInstance());
//
//		Scanner sc = new Scanner(System.in);
//
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//
//			while (true) {
//				System.out.println("---------- 아주시원한 자판기 ----------");
//			
//				System.out.println("--------------------------------");
//				System.out.println("원하시는 음료 번호를 입력해주세요.");
//				System.out.println("-----------------------------------");
//
//				int num = Integer.parseInt(sc.nextLine());
//
//				switch (num) {
//				case 1:
//					manager.itemList();
//					break;
//				case 2:
//					manager.inputData();
//					break;
//				case 3:
//					manager.editItem();
//					break;
//				case 4:
//					break;
//				}
//			}
//
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}