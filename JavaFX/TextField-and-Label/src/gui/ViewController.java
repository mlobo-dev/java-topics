package gui;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ViewController {

	@FXML
	private TextField txtNumber1;

	@FXML
	private TextField txtNumber2;

	@FXML
	private Label lblResult;

	@FXML
	private Button btnSum;

	@FXML
	public void onBtSumAction() {
		
		try {
			double n1 = Double.parseDouble(txtNumber1.getText());
			double n2 = Double.parseDouble(txtNumber2.getText());
			
			lblResult.setText("" + (n1+n2));
			
		}catch(NumberFormatException e) {
			Alerts.showAlert("Wrong Number", "Error", e.getMessage(), AlertType.ERROR);
		}
		

	}
}
