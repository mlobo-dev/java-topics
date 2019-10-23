package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ViewController {
	
	@FXML
	private Button btTeste;
	
	@FXML
	public void onBtTestAction() {
		System.out.println("Hello World");
	}
}
