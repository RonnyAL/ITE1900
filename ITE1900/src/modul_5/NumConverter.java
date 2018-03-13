package modul_5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class NumConverter extends Application {

	static String output;

	public static void main(String[] args) {

		launch(args);

	}

	private static String dec2Bin(int value) {

		if (value < 2) {
			output = "" + value;
			return output;
		} else {
			if (value != 0) {
				dec2Bin(value / 2);
				output += value % 2;
			}
			return output;
		}
	}

	private static String dec2Hex(int value) {
		char hexa[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		int temp;

		if (value != 0) {
			temp = value % 16;
			output = hexa[temp] + output;
			value = value / 16;
			dec2Hex(value);
		}
		return output;
	}

	private static int bin2Dec(String string) {
		int len = string.length();
		if (len == 0)
			return 0;
		String now = string.substring(0, 1);
		String later = string.substring(1);
		return Integer.parseInt(now) * (int) Math.pow(2, len - 1) + bin2Dec(later);
	}

	private static int hex2Dec(String string) {
		int len = string.length();
		char curDig;
		long dec = 0L;
		int d = 0, power = 0;
		for (int i = len - 1; i >= 0; i--) {
			curDig = string.charAt(i);
			if (curDig >= '0' && curDig <= '9')
				d = curDig - 48;
			if (curDig >= 'A' && curDig <= 'F')
				d = curDig - 55;

			dec = dec + d * (long) Math.pow(16, power);
			power++;
		}
		return (int) dec;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 500, 130);
		
		Tab tabD2B = new Tab("Decimal to binary");
		Tab tabD2H = new Tab("Decimal to hex");
		Tab tabB2D = new Tab("Binary to decimal");
		Tab tabH2D = new Tab("Hex to decimal");
		
		TabPane tabPane = new TabPane();
		tabPane.getTabs().addAll(tabD2B, tabD2H, tabB2D, tabH2D);
		
		for(Tab t : tabPane.getTabs()) {
			t.setClosable(false);
			t.setStyle("-fx-pref-width: " + scene.getWidth() / tabPane.getTabs().size());
		}
		
		
		
		tabPane.setPrefWidth(500);
		tabPane.setMinWidth(400);
		tabPane.setMaxWidth(1000);

		TextField input = new TextField();
		TextField output = new TextField();
		input.setPromptText("Input value...");
		input.setScaleX(0.95);
		output.setPromptText("Output value...");
		output.setDisable(true);
		output.setStyle("-fx-opacity: 1.0;");
		output.setScaleX(0.95);
		
		output.textProperty().bind(input.textProperty());
		
		VBox content = new VBox(tabPane, input, output);
		content.setSpacing(15);
		
		pane.getChildren().add(content);


		
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Converter");
		primaryStage.show();

	}

}