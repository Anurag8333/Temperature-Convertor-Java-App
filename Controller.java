package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
	public Label welcomeLabel;
	@FXML
	public ChoiceBox<String> choiceBox;
	@FXML
	public TextField userinput;
	@FXML
	public Button convertButton,infoBtn;


	private static final String c_to_f_text = "Celsius to Fahrenheit";
	private static final String f_to_c_text = "Fahrenheit to celsius";
	private boolean isc_to_f = true;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choiceBox.getItems().add(c_to_f_text);
		choiceBox.getItems().add(f_to_c_text);
		choiceBox.setValue(c_to_f_text);

		choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.equals(c_to_f_text)){
					isc_to_f = true;
				}
				else {
					isc_to_f = false;
				}
			}
		});
	convertButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			convert();
		}

		private void convert() {
			String input = userinput.getText();
			float enteredTemperature = 0.0f;
			try{
				enteredTemperature = Float.parseFloat(input); }// convert string to float
			catch (Exception e){
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("ERROR OCCURRED");
				alert.setHeaderText("Invalid Input");
				alert.setContentText("Please Enter Valid Temperature Value");
				alert.show();
				return; //no code executed
			}
			float newTemperature = 0.0f;

			if (isc_to_f){
				newTemperature = (enteredTemperature* 9/5)+32;
			}
			else {
				newTemperature = (enteredTemperature - 32)*5/9;
			}
			display(newTemperature);
		}

		private void display(float newTemperature) {
			String unit =isc_to_f?" F" :" C";
			System.out.println("The new temperature is: "+newTemperature+unit);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Result");
			alert.setContentText("The new temperature is: "+newTemperature+unit);
			alert.show();
		}
	});

		infoBtn.setOnAction(event -> {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Developer : Anurag Singh");
		alert.setTitle("DEVELOPER");
		alert.show();
		});
	}
}