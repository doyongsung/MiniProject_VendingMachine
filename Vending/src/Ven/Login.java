package Ven;

import java.util.Scanner;

public class Login {

	Scanner sc = new Scanner(System.in);

	void Manager(int choice) {
		int i = 1;

		System.out.println("관리자 프로그램으로 접속합니다.");
		while (i <= 3) {
			System.out.println("——————————————————— 로그인 ———————————————————————");
			System.out.print("id를 입력해주세요 >> ");
			String id = sc.nextLine();
			System.out.print("password를 입력해주세요 >> ");
			String password = sc.nextLine();
			boolean isRightPass = (id.equals("hr") || password.equals("tiger"));
			if (id.equals(id) && isRightPass) {
				System.out.println();
				System.out.println("로그인 성공!!");
				break;
			} else {
				System.out.println("————————————————————————————————————————————————");
				System.out.println("id와 password가 틀렸습니다. 다시 입력해주세요");
				System.out.println("————————————————————————————————————————————————");
				System.out.println();
				i++;
			}
			if (i == 4) {
				System.out.println("[3회 모두 틀렸습니다. 다시 접속해주세요.]");
				System.exit(0);
			}

		}

	}
}
