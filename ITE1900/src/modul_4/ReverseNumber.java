package modul_4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class ReverseNumber extends Application {

	public static int reversedInt;

	// Implementerer maks lengde på input for å hindre for store integers.
	public static int maxLength = 10;

	public static final String INT_REGEX = "([1-9][0-9]*)?";

	public static void main(String[] args) {
		launch(args);
	}

	public void reverseIntegers(int value) {
		reversedInt *= 10;

		if (value < 10) {
			reversedInt += value;
			return;
		} else {
			reversedInt += value % 10;
			reverseIntegers(value / 10);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Reverser heltall");
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		TextField inputTF = new TextField();
		inputTF.setPromptText("Oppgi et heltall");

		TextField reverseTF = new TextField();
		reverseTF.setDisable(true);
		reverseTF.setStyle("-fx-opacity: 1.0;");
		reverseTF.setPromptText("Reversert tall vises her");

		gridPane.add(new Label("Heltall: "), 0, 0);
		gridPane.add(inputTF, 1, 0);
		gridPane.add(new Label("Reversert: "), 0, 1);
		gridPane.add(reverseTF, 1, 1);

		dialog.getDialogPane().setContent(gridPane);

		Platform.runLater(() -> inputTF.requestFocus());

		inputTF.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (inputTF.getLength() >= maxLength) {
					inputTF.setText(oldValue);
				}

				// All tekst ble fjernet.
				if (newValue.isEmpty()) {
					reverseTF.setText("");
					return;
				}

				if (!newValue.matches(INT_REGEX)) {
					try {
						inputTF.setText(String.valueOf(Integer.parseInt(oldValue)));
					} catch (NumberFormatException e) {
						if (newValue.equals("0")) {
							inputTF.setText("0");
						} else {
							inputTF.setText(oldValue);
						}
					}

				}

				if (newValue != "" && !newValue.isEmpty()) {
					try {
						reversedInt = 0;
						reverseIntegers(Integer.parseInt(inputTF.getText()));
					} catch (NumberFormatException e) {
						reverseTF.setText(inputTF.getText());
					}
					reverseTF.setText(String.valueOf(reversedInt));
				}

			}

		});

		dialog.showAndWait();

	}

}