package application;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int decision = 1;

		do {
			System.out.println("you wanna quit program? 0:quit  1:continue");
			decision = in.nextInt();
			System.out.println("continue.....");

		} while (decision != 0);

		in.close();
		System.out.println("The end...");
	}

}
