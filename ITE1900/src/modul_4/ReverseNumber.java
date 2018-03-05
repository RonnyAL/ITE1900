package modul_4;

import javafx.application.*;
import javafx.beans.value.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class ReverseNumber extends Application {

	public static final String INT_REGEX = "-?([1-9][0-9]*)?";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Reverser heltall");
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		TextField input = new TextField();
		input.setPromptText("Oppgi et heltall");

		TextField reverse = new TextField();
		reverse.setStyle("-fx-opacity: 1.0;");
		reverse.setPromptText("Reversert tall vises her");
		reverse.setDisable(true);

		gridPane.add(new Label("Heltall: "), 0, 0);
		gridPane.add(input, 1, 0);
		gridPane.add(new Label("Reversert: "), 0, 1);
		gridPane.add(reverse, 1, 1);

		dialog.getDialogPane().setContent(gridPane);

		Platform.runLater(() -> input.requestFocus());

		input.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println(newValue);

				// Denne sørger for at "0-" blir "-". Fant ingen bedre løsning.
				if (newValue.equals("0-")) {
					input.setText("-");
					return;
				}

				if (!newValue.matches(INT_REGEX)) {
					try {
						input.setText(String.valueOf(Integer.parseInt(oldValue)));
					} catch (NumberFormatException e) {
						if (newValue.equals("0")) {
							input.setText("0");
						} else {
							input.setText(oldValue);
						}
					}

				}
				reverse.setText(new StringBuilder(input.getText()).reverse().toString());
			}

		});

		dialog.showAndWait();

	}

}