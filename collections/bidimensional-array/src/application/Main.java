package application;

public class Main {
	public static void main(String[] args) {
		int[][] array = new int[5][5];

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				array[i][j] = (i+j)*2;
				System.out.println(array[i][j]);
			}
		}
	}
}
