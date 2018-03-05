package modul_4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class ReverseNumber extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Reverser heltall");
		ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
		
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		TextField input = new TextField();
		input.setPromptText("Oppgi ett heltall");
		TextField reverse = new TextField();
		reverse.setStyle("-fx-opacity: 1.0;");
		reverse.setPromptText("Reversert tall vises her");
		reverse.setDisable(true);

		reverse.setText("Reversert tall");
		
		gridPane.add(new Label("Heltall: "), 0, 0);
		gridPane.add(input, 1, 0);
		gridPane.add(new Label("Reversert: "), 0, 1);
		gridPane.add(reverse, 1, 1);
		
		dialog.getDialogPane().setContent(gridPane);
		
		Platform.runLater(() -> input.requestFocus());
		
		input.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			reverse.setText(new StringBuilder(input.getText()).reverse().toString());
		}

	});
		
		dialog.showAndWait();
		
	}

}
