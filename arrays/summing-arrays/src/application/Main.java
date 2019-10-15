package application;

public class Main {

	public static void main(String[] args) {
		double[] array = {2,4,6,8};
		double sumArray = 0;
		
		for(int i=0; i<array.length; i++) {
			sumArray += array[i];
			System.out.println(sumArray);
		}

	}

}
