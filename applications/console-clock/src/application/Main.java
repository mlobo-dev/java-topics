package application;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		Thread th = new Thread(new Runnable() {
			public void run() {
				while (true) {
					Date date = Calendar.getInstance().getTime();
					DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
					String clock = formatter.format(date);
					System.out.print(clock +"\n");
					
					try {
						Thread.sleep(1000);
					} catch (Exception ex) {
					}
				}
			}
		});
		th.start();

	}

	

}
